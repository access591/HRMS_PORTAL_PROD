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


import com.hrms.model.Information;
import com.hrms.model.MenuModule;
import com.hrms.model.PathDirectory;
import com.hrms.service.Informationservice;
import com.hrms.service.ModuleService;
import com.hrms.service.PathDirectoryServices;

@Controller
public class InformationController {
	@Autowired ModuleService  moduleService;
	@Autowired Informationservice informationservice;
	@Autowired 
	private PathDirectoryServices pathDirectoryServices;
	
	@GetMapping("/information")
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
	  	List<Information> listInformation= informationservice.getAllInformation();
			model.addAttribute("listInformation",listInformation);
		return "information";
	}

	
	@PostMapping("/saveInformation")
	public String saveInformation(@ModelAttribute("information") Information information, Model model,RedirectAttributes redirectAttributes, HttpSession session, MultipartFile file,HttpServletRequest request) throws FileNotFoundException
	{
		UUID uuid=UUID.randomUUID();
	    MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> multipartFile = multipartRequest.getFiles("fileUpload");

        if (null != multipartFile && !multipartFile.isEmpty()) {
            for (MultipartFile fileS : multipartFile) {
                try {
					if (fileS.getBytes().length > 0) {
						
						information.setFile(fileS.getOriginalFilename());
						
						//String folderPath = "\\src\\main\\resources\\static\\pdfs\\Work\\";
						
						String pathType="Work";
						PathDirectory p = pathDirectoryServices.findPathByName(pathType);
						if(p==null)
						{
							 redirectAttributes.addFlashAttribute("message", "Please Make Directory! ");
							 redirectAttributes.addFlashAttribute("alertClass", "alert-success");
							return"redirect:/information";	
						}
						
						//String folderPath = "/src/main/resources/static"+p.getPathFile();
						String folderPath = p.getUpldLocPath();
						//String uploadDir = System.getProperty("user.dir") + folderPath;
						String uploadDir =folderPath;
						File uploadRootDir = new File(uploadDir);
				        if (!uploadRootDir.exists())
						  {      redirectAttributes.addFlashAttribute("message", "Please Make Directory! ");
								 redirectAttributes.addFlashAttribute("alertClass", "alert-success");
								return"redirect:/information"; 
						  } 
						
						
						
						String path = Paths.get(uploadDir + information.getFile()).toString();
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
						stream.write(fileS.getBytes());
						stream.close();
						
						information.setInsBy((String) session.getAttribute("USER_NAME"));
						information.setFile(p.getPathFile()+fileS.getOriginalFilename());
						informationservice.addInformation(information);
						 redirectAttributes.addFlashAttribute("message", "Record has been saved  successfully! ");
						 redirectAttributes.addFlashAttribute("alertClass", "alert-success");
						return"redirect:/information";
					
					}
				} catch (IOException e) {
					
					e.printStackTrace();
				}

            }
        }
        
		information.setInsBy((String) session.getAttribute("USER_NAME"));
		informationservice.addInformation(information);
		 redirectAttributes.addFlashAttribute("message", "Record has been saved  successfully! ");
		 redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return"redirect:/information";
	}
	
	
	
	@GetMapping(value = {"/editInformation/{id}"})
	public String editInformation(@PathVariable("id")long id,  Model model,HttpSession session)
	 { 
		Information informationEdit = informationservice.findInformationById(id);
		model.addAttribute("informationEdit", informationEdit);
		session.setAttribute("infoFile",informationEdit.getFile());
	    return "editInformation";
	}	
	
	@PostMapping("/updateInformation")
	public String updateInformation(@ModelAttribute("information")Information information, Model model,RedirectAttributes redirectAttributes,HttpSession session,@RequestParam("fileUpload")MultipartFile multipartFile) {
		   String infoFile =(String) session.getAttribute("infoFile");
		   String fileName2 = StringUtils.cleanPath(multipartFile.getOriginalFilename()); 
		   try 
		   {
				
				String pathType = "Work";
				PathDirectory p = pathDirectoryServices.findPathByName(pathType);
			//	String folderPath = "/src/main/resources/static" + p.getPathFile();
				
				String folderPath = p.getUpldLocPath();
				//String uploadDir = System.getProperty("user.dir") + folderPath;
				String uploadDir =folderPath;
				String spl[] = infoFile.split("/");
				String w = spl[3];
				System.out.println("Value of w" + w);
				File file = new File(uploadDir + w);
				
				//File file = new File(uploadDir + infoFile);
				String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

				if (fileName.trim().length() > 0) {
					file.delete();
					information.setFile(fileName);
					String path = Paths.get(uploadDir + information.getFile()).toString();
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
					stream.write(multipartFile.getBytes());
					stream.close();
					information.setUpdBy((String) session.getAttribute("USER_NAME"));
					information.setFile(p.getPathFile() + fileName);
					this.informationservice.updateInformation(information);
					redirectAttributes.addFlashAttribute("message", "Record updated successfully! ");
					redirectAttributes.addFlashAttribute("alertClass", "alert-success");
					return "redirect:/information";

				} else if (infoFile != null) {
					information.setFile(infoFile);
					information.setUpdBy((String) session.getAttribute("USER_NAME"));
					this.informationservice.updateInformation(information);
					redirectAttributes.addFlashAttribute("message", "Record has been  updated successfully! ");
					redirectAttributes.addFlashAttribute("alertClass", "alert-success");
					return "redirect:/information";
				}
			} catch (Exception e) {
				System.out.println("exception " + e);
			}
		   
		   
		   try {
				if (fileName2 != null) {
					String pathType = "Work";
					PathDirectory p = pathDirectoryServices.findPathByName(pathType);
				//	String folderPath = "/src/main/resources/static" + p.getPathFile();
					
					String folderPath = p.getUpldLocPath();
					
					//String uploadDir = System.getProperty("user.dir") + folderPath;
					String uploadDir =folderPath;
					File uploadRootDir = new File(uploadDir); 
			        if (!uploadRootDir.exists())
								  {      redirectAttributes.addFlashAttribute("message", "Please Make Directory ! ");
										 redirectAttributes.addFlashAttribute("alertClass", "alert-success");
										return"redirect:/information"; 
								  } 
								
			                    information.setFile(fileName2);
								String path = Paths.get(uploadDir + information.getFile()).toString();
								BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
								stream.write(multipartFile.getBytes());
								stream.close();
								
								information.setUpdBy((String) session.getAttribute("USER_NAME"));
								information.setFile(p.getPathFile() + fileName2);
								this.informationservice.updateInformation(information);
								redirectAttributes.addFlashAttribute("message", "Record has been  updated successfully! ");
								redirectAttributes.addFlashAttribute("alertClass", "alert-success");
								return "redirect:/information";
				}
			   
		} catch (Exception e) {
			e.printStackTrace();
		}
			information.setUpdBy((String) session.getAttribute("USER_NAME"));
			this.informationservice.updateInformation(information);
			redirectAttributes.addFlashAttribute("message", "Record has been  updated successfully! ");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
			return "redirect:/information";
	}
	
	@GetMapping(value = { "/deleteInfo/{id}" })
	public String deleteInfo(@PathVariable("id") long id, Model model,HttpSession session,RedirectAttributes redirectAttributes) {
		Information yy = informationservice.findInformationById(id);
		try {

			this.informationservice.removeInformation(id);
			String pathType="Work";
			PathDirectory p = pathDirectoryServices.findPathByName(pathType);
			//String folderPath = "/src/main/resources/static"+p.getPathFile();
			
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
		this.informationservice.removeInformation(id);
		redirectAttributes.addFlashAttribute("message", "Record  has been deleted successfully! ");
	    redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return "redirect:/information";
	}

}
