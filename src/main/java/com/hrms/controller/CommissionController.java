package com.hrms.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hrms.model.Commission;
import com.hrms.model.MenuModule;
import com.hrms.model.PathDirectory;
import com.hrms.service.Commissionservice;
import com.hrms.service.ModuleService;
import com.hrms.service.PathDirectoryServices;

@Controller
public class CommissionController {
	@Autowired 
	private PathDirectoryServices pathDirectoryServices;
	@Autowired ModuleService  moduleService;
	@Autowired Commissionservice commissionservice;
	@GetMapping("/commission")
	public String acts(Model model, HttpSession session)
	{
		String userCode = (String) session.getAttribute("username");
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
	  	List<Commission> listCommission= commissionservice.getAllCommissions();
			model.addAttribute("listCommission",listCommission);
		return "commission";
	}
	
	
	
	
	@PostMapping("/saveCommission")
	public String saveRules(@ModelAttribute("commission") Commission commission, Model model,RedirectAttributes redirectAttributes, HttpSession session, MultipartFile file,HttpServletRequest request) throws FileNotFoundException
	{
		UUID uuid=UUID.randomUUID();
	    MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> multipartFile = multipartRequest.getFiles("fileUpload");

        if (null != multipartFile && !multipartFile.isEmpty()) {
            for (MultipartFile fileS : multipartFile) {
                try {
					if (fileS.getBytes().length > 0) {
						
						commission.setFile(fileS.getOriginalFilename());
						//String folderPath = "\\src\\main\\resources\\static\\pdfs\\Commissions\\";
						 
						String pathType="Commissions";
						PathDirectory p = pathDirectoryServices.findPathByName(pathType);
						if(p==null)
						{
							 redirectAttributes.addFlashAttribute("message", "Please Make Directory! ");
							 redirectAttributes.addFlashAttribute("alertClass", "alert-success");
							return"redirect:/commission";	
						}
						
						String folderPath = p.getUpldLocPath();
						       
						//String uploadDir = System.getProperty("user.dir") + folderPath;
						String uploadDir =folderPath;
						File uploadRootDir = new File(uploadDir);
					        if (!uploadRootDir.exists())
							  {      redirectAttributes.addFlashAttribute("message", "Please Make Directory! ");
									 redirectAttributes.addFlashAttribute("alertClass", "alert-success");
									return"redirect:/commission"; 
							  } 
					        
						String path = Paths.get(uploadDir + commission.getFile()).toString();
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
						stream.write(fileS.getBytes());
						stream.close();
						
						commission.setInsBy((String) session.getAttribute("USER_NAME"));
						commission.setFile(p.getPathFile()+fileS.getOriginalFilename());
						commissionservice.addCommission(commission);
						 redirectAttributes.addFlashAttribute("message", "Record  has been saved successfully!! ");
						 redirectAttributes.addFlashAttribute("alertClass", "alert-success");
						return"redirect:/commission";
					
					}
				} catch (IOException e) {
					
					e.printStackTrace();
				}

            }
        }
        commission.setInsBy((String) session.getAttribute("USER_NAME"));
		commissionservice.addCommission(commission);
		 redirectAttributes.addFlashAttribute("message", "Record  has been saved successfully! ");
		 redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return"redirect:/commission";
        
		
	}
	
	@GetMapping(value = {"/editCommission/{id}"})
	public String editCommission(@PathVariable("id")long id,  Model model,HttpSession session)
	 { 
		Commission commissionEdit = commissionservice.findCommissionById(id);
		model.addAttribute("commissionEdit", commissionEdit);
		session.setAttribute("comFile",commissionEdit.getFile());
		return "editCommission";
	}	
	
	@PostMapping("/updateCommission")
	public String updateCommission(@ModelAttribute("commission")Commission commission, Model model,RedirectAttributes redirectAttributes,HttpSession session,@RequestParam("fileUpload")MultipartFile multipartFile) {
		   
		   String comFile =(String) session.getAttribute("comFile");
		String fileName2 = StringUtils.cleanPath(multipartFile.getOriginalFilename()); 
		   try 
		   {
	
				String pathType="Commissions";
				PathDirectory p = pathDirectoryServices.findPathByName(pathType);
				       
				String folderPath = p.getUpldLocPath();
				//String uploadDir = System.getProperty("user.dir") + folderPath;
				String uploadDir =folderPath;
				String spl[]=comFile.split("/");
				String w=spl[3];
				System.out.println("Value of w"+w);
				File file = new File(uploadDir + w);
				String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename()); 
			   
			   if (fileName.trim().length() > 0) {
					file.delete();
					commission.setFile(fileName);
					String path = Paths.get(uploadDir + commission.getFile()).toString();
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
					stream.write(multipartFile.getBytes());
					stream.close();
					commission.setUpdBy((String) session.getAttribute("USER_NAME"));
					commission.setFile(p.getPathFile()+fileName);
					
					   this.commissionservice.updateCommission(commission); 
					   redirectAttributes.addFlashAttribute("message", "Record  has been updated successfully! ");
					   redirectAttributes.addFlashAttribute("alertClass", "alert-success");
					   return"redirect:/commission";

				} else if (comFile!= null) {
					commission.setFile(comFile);
					commission.setUpdBy((String) session.getAttribute("USER_NAME"));
					this.commissionservice.updateCommission(commission); 
					  redirectAttributes.addFlashAttribute("message", "Record  has been  updated successfully! ");
					   redirectAttributes.addFlashAttribute("alertClass", "alert-success");
					   return"redirect:/commission";
				}
		} 
		   	catch (Exception e) 
		   	{
		System.out.println("exception "+e);
		}
		   
		  try {
			  if(fileName2!=null)
				{
				  String pathType="Commissions";
					PathDirectory p = pathDirectoryServices.findPathByName(pathType);
					       
					String folderPath = p.getUpldLocPath();
					//String uploadDir = System.getProperty("user.dir") + folderPath;
					String uploadDir =folderPath;
					File uploadRootDir = new File(uploadDir);
					if (!uploadRootDir.exists())  {
					     redirectAttributes.addFlashAttribute("message", "Please make Directory Local Folder First! ");
						 redirectAttributes.addFlashAttribute("alertClass", "alert-success");
						 return"redirect:/commission"; }
					
					
					     commission.setFile(fileName2);
					     String path = Paths.get(uploadDir + commission.getFile()).toString();
						 BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
						 stream.write(multipartFile.getBytes());
						 stream.close();
						 commission.setUpdBy((String) session.getAttribute("USER_NAME"));
						 commission.setFile(p.getPathFile()+fileName2);
						 this.commissionservice.updateCommission(commission); 
						 redirectAttributes.addFlashAttribute("message", "Record  has been updated successfully! ");
						 redirectAttributes.addFlashAttribute("alertClass", "alert-success");
						   return"redirect:/commission";
				}
			  
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		   
		   commission.setUpdBy((String) session.getAttribute("USER_NAME"));
			this.commissionservice.updateCommission(commission); 
			 redirectAttributes.addFlashAttribute("message", "Record  has been  updated successfully!");
			 redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		   return"redirect:/commission";
	}
	
	
	@GetMapping(value = { "/deleteCommission/{id}"})
	public String deleteCommission(@PathVariable("id") long id,  Model model,HttpSession session,RedirectAttributes redirectAttributes) {
		Commission yy = commissionservice.findCommissionById(id);
		try {

			this.commissionservice.removeCommission(id);
			String pathType="Commissions";
			PathDirectory p = pathDirectoryServices.findPathByName(pathType);
			
			       
			String folderPath = p.getUpldLocPath();
			//String uploadDir = System.getProperty("user.dir") + folderPath;
			String uploadDir =folderPath;
			String spl[]=yy.getFile().split("/");
			String w=spl[3];
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
		this.commissionservice.removeCommission(id);
		redirectAttributes.addFlashAttribute("message", "Record  has been deleted Successfully! ");
	    redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return "redirect:/commission";
	}
		
	
	
}
