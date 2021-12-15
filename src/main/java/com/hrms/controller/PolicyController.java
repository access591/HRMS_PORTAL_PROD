package com.hrms.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hrms.model.MenuModule;
import com.hrms.model.PathDirectory;
import com.hrms.model.Policy;

import com.hrms.service.ModuleService;
import com.hrms.service.PathDirectoryServices;
import com.hrms.service.PolicyService;

@Controller
public class PolicyController {
	@Autowired
	private ModuleService moduleService;
	@Autowired PolicyService  policyService;
	@Autowired 
	private PathDirectoryServices pathDirectoryServices;
	
	@InitBinder("policy")
    public void customizeBinding (WebDataBinder binder) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        dateFormatter.setLenient(false);
                binder.registerCustomEditor(Date.class, "dou",new CustomDateEditor(dateFormatter, true));
				binder.registerCustomEditor(Date.class, "policyDoi", new CustomDateEditor(dateFormatter, true));

    }
	
	
	
	@GetMapping("/policy")
	public String rules(Model model, HttpSession session)
	{
		String userCode = (String) session.getAttribute("username");
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
	  	List<Policy> listPolicy = policyService.getAllPolicy();
			model.addAttribute("listPolicy", listPolicy);
		return "policy";
	}
	
	@PostMapping("/savePolicy")
	public String savePolicy(@ModelAttribute("policy") Policy policy, Model model,RedirectAttributes redirectAttributes, HttpSession session, MultipartFile file,HttpServletRequest request) throws FileNotFoundException
	{
	//	UUID uuid=UUID.randomUUID();
	    MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> multipartFile = multipartRequest.getFiles("fileUpload");

        if (null != multipartFile && !multipartFile.isEmpty()) {
            for (MultipartFile fileS : multipartFile) {
                try {
					if (fileS.getBytes().length > 0) {
						
						policy.setFile(fileS.getOriginalFilename());
						//String folderPath = "\\src\\main\\resources\\static\\pdfs\\Policy\\";
						
						String pathType="Policy";
						PathDirectory p = pathDirectoryServices.findPathByName(pathType);
						if(p==null)
						{
							 redirectAttributes.addFlashAttribute("message", "Please Make Directory First! ");
							 redirectAttributes.addFlashAttribute("alertClass", "alert-success");
							return"redirect:/policy";	
						}
						//String folderPath = "/src/main/resources/static"+p.getPathFile();
						String folderPath = p.getUpldLocPath();
						
						
						//String uploadDir = System.getProperty("user.dir") + folderPath;
						String uploadDir =folderPath;
						  File uploadRootDir = new File(uploadDir); // Create directory if it notexists.
						  if (!uploadRootDir.exists())
						  {      redirectAttributes.addFlashAttribute("message", "Please make Directory Local Folder First! ");
								 redirectAttributes.addFlashAttribute("alertClass", "alert-success");
								return"redirect:/policy"; 
						  }
						String path = Paths.get(uploadDir + policy.getFile()).toString();
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
						stream.write(fileS.getBytes());
						stream.close();
						policy.setInsBy((String) session.getAttribute("USER_NAME"));
						policy.setFile(p.getPathFile()+fileS.getOriginalFilename());
						policyService.addPolicy(policy);
						 redirectAttributes.addFlashAttribute("message", "Record has been saved Successfully! ");
						 redirectAttributes.addFlashAttribute("alertClass", "alert-success");
						return"redirect:/policy";
					
					}
				} catch (IOException e) {
					
					e.printStackTrace();
				}

            }
        }
        
        policy.setInsBy((String) session.getAttribute("USER_NAME"));
    	policyService.addPolicy(policy);
    	 redirectAttributes.addFlashAttribute("message", "Record has been saved Successfully! ");
		 redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return"redirect:/policy";
        
        
	}
	
	@GetMapping(value = {"/editPolicy/{id}"})
	public String editPolicy(@PathVariable("id")long id,  Model model,HttpSession session)
	 { 
		Policy policyEdit = policyService.findPolicyById(id);
		model.addAttribute("policyEdit", policyEdit);
		session.setAttribute("policyFile",policyEdit.getFile());
	    return "editPolicy";
	}	
	
	@PostMapping("/updatePolicy")
	public String updatePolicy(@ModelAttribute("policy")Policy policy, Model model,RedirectAttributes redirectAttributes,HttpSession session,@RequestParam("fileUpload") MultipartFile multipartFile) {
		   
		   String filePol =(String) session.getAttribute("policyFile");
		   try 
		   {
			   //UUID uuid = UUID.randomUUID();
				String pathType="Policy";
				PathDirectory p = pathDirectoryServices.findPathByName(pathType);
				//String folderPath = "/src/main/resources/static"+p.getPathFile();
				String folderPath = p.getUpldLocPath();
				//String uploadDir = System.getProperty("user.dir") + folderPath;
				String uploadDir =folderPath;
				String spl[]=filePol.split("/");
				String w=spl[3];
				System.out.println("Value of w"+w);
				File file = new File(uploadDir + w);
				
				//File file = new File(uploadDir +filePol);
			
				String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename()); 
			   
			   if (fileName.trim().length() > 0) {
					file.delete();
					policy.setFile(fileName);
					String path = Paths.get(uploadDir + policy.getFile()).toString();
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
					stream.write(multipartFile.getBytes());
					stream.close();
					policy.setUpdBy((String) session.getAttribute("USER_NAME"));
					   policy.setFile(p.getPathFile()+fileName);
					   this.policyService.updatePolicy(policy);   
					  redirectAttributes.addFlashAttribute("message", "Record has been Updated successfully! ");
					   redirectAttributes.addFlashAttribute("alertClass", "alert-success");

				} else if (filePol != null) {
					
					
					 policy.setFile(filePol);
					  policy.setUpdBy((String) session.getAttribute("USER_NAME"));
					   this.policyService.updatePolicy(policy);   
					  redirectAttributes.addFlashAttribute("message", "Record has been Updated successfully! ");
					   redirectAttributes.addFlashAttribute("alertClass", "alert-success");
				}
		} 
		   	catch (Exception e) 
		   	{
		System.out.println("exception "+e);
		}
		   
		   policy.setUpdBy((String) session.getAttribute("USER_NAME"));
		   this.policyService.updatePolicy(policy);   
		   redirectAttributes.addFlashAttribute("message", "Record has been Updated successfully! ");
		   redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		   
		   return"redirect:/policy";
	}
	
	@GetMapping(value = { "/deletePolicy/{id}" })
	public String deleteRules(@PathVariable("id") long id, Model model,
			HttpSession session,RedirectAttributes redirectAttributes) {
		Policy yy = policyService.findPolicyById(id);
		try {

			this.policyService.removePolicy(id);
			//String folderPath = "\\src\\main\\resources\\static\\pdfs\\Policy\\";
			String pathType="Policy";
			PathDirectory p = pathDirectoryServices.findPathByName(pathType);
			//String folderPath = "/src/main/resources/static"+p.getPathFile();
			String folderPath = p.getUpldLocPath();
			//String uploadDir = System.getProperty("user.dir") + folderPath;
			String uploadDir =folderPath;
			String spl[]=yy.getFile().split("/");
			String w=spl[3];
			System.out.println("Value of w"+w);
			File file = new File(uploadDir + w);
			
			
			//File file = new File(uploadDir + yy.getFile());

			if (file.delete()) {
				System.out.println(file.getName() + " is deleted!"); 
			} else {
				System.out.println("Delete operation is failed.");

			}

			session.setAttribute("username", session.getAttribute("username"));

		} catch (Exception e) {
			e.printStackTrace();
			
		}
		this.policyService.removePolicy(id);
		redirectAttributes.addFlashAttribute("message", "Record  has been Deleted Successfully! ");
	    redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return "redirect:/policy";
	}
	
	
	
	

}
