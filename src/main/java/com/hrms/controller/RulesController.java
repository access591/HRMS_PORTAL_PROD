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
import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.hrms.model.Rules;

import com.hrms.service.ModuleService;
import com.hrms.service.PathDirectoryServices;
import com.hrms.service.RulesService;

@Controller
public class RulesController {
	@Autowired
	private ModuleService moduleService;
	@Autowired
	RulesService rulesService;
	@Autowired 
	private PathDirectoryServices pathDirectoryServices;
	
	@InitBinder("rules")
    public void customizeBinding (WebDataBinder binder) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        dateFormatter.setLenient(false);
                binder.registerCustomEditor(Date.class, "dou",new CustomDateEditor(dateFormatter, true));
				binder.registerCustomEditor(Date.class, "ruleDoi", new CustomDateEditor(dateFormatter, true));

    }
	
	@GetMapping("/rules")
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
	  	List<Rules> listRules = rulesService.getAllRules();
			model.addAttribute("listRules", listRules);
		return "rules";
	}
	
	
	
	@PostMapping("/saveRules")
	public String saveRules(@ModelAttribute("rules") Rules rules, Model model,RedirectAttributes redirectAttributes, HttpSession session, MultipartFile file,HttpServletRequest request) throws FileNotFoundException
	{
		//UUID uuid=UUID.randomUUID();
	    MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> multipartFile = multipartRequest.getFiles("fileUpload");

        if (null != multipartFile && !multipartFile.isEmpty()) {
            for (MultipartFile fileS : multipartFile) {
                try {
					if (fileS.getBytes().length > 0) {
						
						rules.setFile(fileS.getOriginalFilename());
						
						String pathType="Rules";
						PathDirectory p = pathDirectoryServices.findPathByName(pathType);
						if(p==null)
						{
							 redirectAttributes.addFlashAttribute("message", "Please make Directory First! ");
							 redirectAttributes.addFlashAttribute("alertClass", "alert-success");
							return"redirect:/rules";	
						}
						String folderPath = p.getUpldLocPath();
						System.out.println("Folder Path>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+folderPath);
					//	String uploadDir = System.getProperty("user.dir") + folderPath;
						String uploadDir =folderPath;
						File uploadRootDir = new File(uploadDir); // Create directory if it notexists.
						  
						   
						  if (!uploadRootDir.exists()) {
							  redirectAttributes.addFlashAttribute("message", "Please make Directory Local Folder First! ");
							  redirectAttributes.addFlashAttribute("alertClass", "alert-success");
							  return"redirect:/rules"; 
							  
						  }
						
						String path = Paths.get(uploadDir + rules.getFile()).toString();
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
						stream.write(fileS.getBytes());
						stream.close();
						
						rules.setInsBy((String) session.getAttribute("USER_NAME"));
						rules.setFile(p.getPathFile()+fileS.getOriginalFilename());
						rulesService.addRules(rules);
					
						 redirectAttributes.addFlashAttribute("message", "Record  has been saved Successfully! ");
						 redirectAttributes.addFlashAttribute("alertClass", "alert-success");
						
						return"redirect:/rules";
					
					}
				} catch (IOException e) {
					
					e.printStackTrace();
				}

            }
        }
        
		rules.setInsBy((String) session.getAttribute("USER_NAME"));
    	rulesService.addRules(rules);
   	   redirectAttributes.addFlashAttribute("message", "Record  has been saved Successfully! ");
	   redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return"redirect:/rules";
		
	}
	
	@GetMapping(value = {"/editRules/{id}"})
	public String editContactUs(@PathVariable("id")long id,  Model model,HttpSession session)
	 { 
		Rules rulesEdit = rulesService.findRulesById(id);
		model.addAttribute("rulesEdit", rulesEdit);
		session.setAttribute("ruleFile",rulesEdit.getFile());
	    return "editRules";
	}	
	
	@PostMapping("/updateRules")
	public String updateRules(@ModelAttribute("rules")Rules rules, Model model,RedirectAttributes redirectAttributes,HttpSession session,@RequestParam("fileUpload") MultipartFile multipartFile) {
		   try 
		   {
			String fileRul =(String) session.getAttribute("ruleFile");
			   
			//   UUID uuid = UUID.randomUUID();
				String pathType="Rules";
				PathDirectory p = pathDirectoryServices.findPathByName(pathType);
				String folderPath = p.getUpldLocPath();
				//String uploadDir = System.getProperty("user.dir") + folderPath;
				String uploadDir =folderPath;
		        System.out.println("Current working uploadDir in update Case  Java  : " + uploadDir);
		     
				String spl[]=fileRul.split("/");
				String w=spl[3];
				System.out.println("Value of w"+w);
				File file = new File(uploadDir + w);
				//File file = new File(uploadDir +fileRul);
			
				String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename()); 
			   
			   if (fileName.trim().length() > 0) {
					file.delete();
					rules.setFile(fileName);
					//String path = Paths.get(uploadDir + rules.getFile()).toString();
					String path = Paths.get(uploadDir + rules.getFile()).toString();
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
					stream.write(multipartFile.getBytes());
					stream.close();
					 rules.setUpdBy((String) session.getAttribute("USER_NAME"));
						rules.setFile(p.getPathFile()+fileName);
					 this.rulesService.updateRules(rules);  
					  redirectAttributes.addFlashAttribute("message", "Record has been Updated successfully! ");
					   redirectAttributes.addFlashAttribute("alertClass", "alert-success");

				} else if (fileRul != null) {
					rules.setFile(fileRul);
					 rules.setUpdBy((String) session.getAttribute("USER_NAME"));
					 this.rulesService.updateRules(rules); 
					  redirectAttributes.addFlashAttribute("message", "Record has been Updated successfully! ");
					   redirectAttributes.addFlashAttribute("alertClass", "alert-success");
				}
			   
			  
		} 
		   	catch (Exception e) 
		   	{
		System.out.println("exception "+e);
		}
	  	  
		   rules.setUpdBy((String) session.getAttribute("USER_NAME"));
		   this.rulesService.updateRules(rules); 
		   redirectAttributes.addFlashAttribute("message", "Record has been Updated successfully! ");
		   redirectAttributes.addFlashAttribute("alertClass", "alert-success"); 
		   return"redirect:/rules";
	}
	
	@GetMapping(value = { "/deleteRules/{id}" })
	public String deleteRules(@PathVariable("id") long id, Model model,HttpSession session,RedirectAttributes redirectAttributes) {
		Rules ruleDoc = rulesService.findRulesById(id);
		try {

			this.rulesService.removeRules(id);
			
			String pathType="Rules";
			PathDirectory p = pathDirectoryServices.findPathByName(pathType);
			String folderPath = p.getUpldLocPath();
			//String uploadDir = System.getProperty("user.dir") + folderPath;
			String uploadDir =folderPath;
	        System.out.println("Current working uploadDir Delete Case in Java>>>>>>>>>>>> : " + uploadDir);
	     
			String spl[]=ruleDoc.getFile().split("/");
			String w=spl[3];
			System.out.println("Value of w"+w);
			File file = new File(uploadDir + w);

			if (file.delete()) {
				System.out.println(file.getName() + " is deleted!"); 
			} else {
				System.out.println("Delete operation is failed.");

			}

			session.setAttribute("username", session.getAttribute("username"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		this.rulesService.removeRules(id);
		redirectAttributes.addFlashAttribute("message", "Record  has been Deleted Successfully! ");
	    redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return "redirect:/rules";
	}
	
	
	
	
}
