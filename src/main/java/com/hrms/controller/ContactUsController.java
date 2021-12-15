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


import com.hrms.model.ContactUs;
import com.hrms.model.MenuModule;
import com.hrms.model.PathDirectory;
import com.hrms.service.ContactUsService;

import com.hrms.service.ModuleService;
import com.hrms.service.PathDirectoryServices;

@Controller
public class ContactUsController {
	
	@Autowired 
	private PathDirectoryServices pathDirectoryServices;
	@Autowired ModuleService  moduleService;
	@Autowired  ContactUsService contactUsService;
	
	@GetMapping("/contactUs")
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
	  	List<ContactUs> listContactUs= contactUsService.getAllContactUs();
			model.addAttribute("listContactUs",listContactUs);
		return "contactUs";
	}

	
	
	@PostMapping("/saveContactUs")
	public String saveRules(@ModelAttribute("contactUs") ContactUs contactUs, Model model,RedirectAttributes redirectAttributes, HttpSession session, MultipartFile file,HttpServletRequest request) throws FileNotFoundException
	{
		UUID uuid=UUID.randomUUID();
	    MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> multipartFile = multipartRequest.getFiles("fileUpload");

        if (null != multipartFile && !multipartFile.isEmpty()) {
            for (MultipartFile fileS : multipartFile) {
                try {
					if (fileS.getBytes().length > 0) {
						
						contactUs.setPic(fileS.getOriginalFilename());
						
					//	String folderPath = "\\src\\main\\resources\\static\\img\\Image\\";
						
						String pathType="Image";
						PathDirectory p = pathDirectoryServices.findPathByName(pathType);
						if(p==null)
						{
							 redirectAttributes.addFlashAttribute("message", "Please Make Directory First! ");
							 redirectAttributes.addFlashAttribute("alertClass", "alert-success");
							return"redirect:/contactUs";	
						}
						
						//String folderPath = "/src/main/resources/static"+p.getPathFile();
						String folderPath = p.getUpldLocPath();
						
						//String uploadDir = System.getProperty("user.dir") + folderPath;
						String uploadDir =folderPath;
						File uploadRootDir = new File(uploadDir);
				        if (!uploadRootDir.exists())
						  {      redirectAttributes.addFlashAttribute("message", "Please Make Directory Local Folder First! ");
								 redirectAttributes.addFlashAttribute("alertClass", "alert-success");
								return"redirect:/contactUs"; 
						  } 
					        					
						String path = Paths.get(uploadDir + contactUs.getPic()).toString();
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
						stream.write(fileS.getBytes());
						stream.close();
						
						contactUs.setInsBy((String) session.getAttribute("USER_NAME"));
						contactUs.setPic(p.getPathFile()+fileS.getOriginalFilename());
					   	contactUsService.addContactUs(contactUs);
						redirectAttributes.addFlashAttribute("message", "Record has been Saved  successfully! ");
						redirectAttributes.addFlashAttribute("alertClass", "alert-success");
						return"redirect:/contactUs";
					
					}
				} catch (IOException e) {
					
					e.printStackTrace();
				}

            }
        }
        contactUs.setInsBy((String) session.getAttribute("USER_NAME"));
	   	contactUsService.addContactUs(contactUs);
		redirectAttributes.addFlashAttribute("message", "Record has been Saved  successfully! ");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return"redirect:/contactUs";
        
		
	}
	
	@GetMapping(value = {"/editContactUs/{id}"})
	public String editContactUs(@PathVariable("id")long id,  Model model,HttpSession session)
	 { 
		ContactUs contactUsEdit = contactUsService.findContactUsById(id);
		model.addAttribute("contactUsEdit", contactUsEdit);
		session.setAttribute("conImg",contactUsEdit.getPic());
	    return "editContactUs";
	}	
	

	@PostMapping("/updateContactUs")
	public String updateEservices(@ModelAttribute("contactUs")  ContactUs contactUs, Model model,RedirectAttributes redirectAttributes,HttpSession session,@RequestParam("fileUpload")MultipartFile multipartFile) {
	       String fileName2 = StringUtils.cleanPath(multipartFile.getOriginalFilename()); 
		   String conImg =(String) session.getAttribute("conImg");
		   try 
		   {
		
				String pathType="Image";
				PathDirectory p = pathDirectoryServices.findPathByName(pathType);
				//String folderPath = "/src/main/resources/static"+p.getPathFile();
				String folderPath = p.getUpldLocPath();
				
				//String uploadDir = System.getProperty("user.dir") + folderPath;
				String uploadDir =folderPath;
				String spl[] = conImg.split("/");
				String w = spl[3];
				System.out.println("Value of w" + w);
				File file = new File(uploadDir + w);
				//File file = new File(uploadDir +conImg);
			
				String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename()); 
			   
			   if (fileName.trim().length() > 0) {
					file.delete();
					contactUs.setPic(fileName);
					String path = Paths.get(uploadDir + contactUs.getPic()).toString();
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
					stream.write(multipartFile.getBytes());
					stream.close();
					contactUs.setUpdBy((String) session.getAttribute("USER_NAME"));
					contactUs.setPic(p.getPathFile() + fileName);
					 this.contactUsService.updateContactUs(contactUs); 
					  redirectAttributes.addFlashAttribute("message", "Record has been Update successfully! ");
					   redirectAttributes.addFlashAttribute("alertClass", "alert-success");
						return "redirect:/contactUs";

				} else if (conImg != null) {
					contactUs.setPic(conImg);
					contactUs.setUpdBy((String) session.getAttribute("USER_NAME"));
					 this.contactUsService.updateContactUs(contactUs); 
					  redirectAttributes.addFlashAttribute("message", "Record has been Update successfully! ");
					   redirectAttributes.addFlashAttribute("alertClass", "alert-success");
						return "redirect:/contactUs";
				}
		} 
		   	catch (Exception e) 
		   	{
		System.out.println("exception "+e);
		} 
		   
		   try {
				if (fileName2 != null) {
					String pathType = "Image";
					PathDirectory p = pathDirectoryServices.findPathByName(pathType);
					// String folderPath = "/src/main/resources/static"+p.getPathFile();
					String folderPath = p.getUpldLocPath();

				//	String uploadDir = System.getProperty("user.dir") + folderPath;
					String uploadDir =folderPath;
					File uploadRootDir = new File(uploadDir);
					if (!uploadRootDir.exists()) {
						redirectAttributes.addFlashAttribute("message", "Please Make Directory Local Folder First! ");
						redirectAttributes.addFlashAttribute("alertClass", "alert-success");
						return "redirect:/contactUs";
					}

						contactUs.setPic(fileName2);
						String path = Paths.get(uploadDir + contactUs.getPic()).toString();
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
						stream.write(multipartFile.getBytes());
						stream.close();
					contactUs.setUpdBy((String) session.getAttribute("USER_NAME"));
					contactUs.setPic(p.getPathFile() + fileName2);
					this.contactUsService.updateContactUs(contactUs);
					redirectAttributes.addFlashAttribute("message", "Record has been Update successfully! ");
					redirectAttributes.addFlashAttribute("alertClass", "alert-success");
					return "redirect:/contactUs";
				   
			   }
			   
			   
		} catch (Exception e) {
			
			System.out.println("exception "+e);
			
		}
		   
		   contactUs.setUpdBy((String) session.getAttribute("USER_NAME"));
		   this.contactUsService.updateContactUs(contactUs); 
			redirectAttributes.addFlashAttribute("message", "Record has been Update successfully! ");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		   return"redirect:/contactUs";
	}
	
	@GetMapping(value = { "/deleteContactUs/{id}" })
	public String deleteCommission(@PathVariable("id") long id, Model model,
			HttpSession session,RedirectAttributes redirectAttributes) {
		ContactUs yy = contactUsService.findContactUsById(id);
		try {

			this.contactUsService.removeContactUs(id);

			String pathType = "Image";
			PathDirectory p = pathDirectoryServices.findPathByName(pathType);
			String folderPath = p.getUpldLocPath();
			//String uploadDir = System.getProperty("user.dir") + folderPath;
			String uploadDir =folderPath;
			

			String spl[] = yy.getPic().split("/");
			String w = spl[3];
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
		this.contactUsService.removeContactUs(id);
		 redirectAttributes.addFlashAttribute("message", "Record  has been Deleted Successfully! ");
		 redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return "redirect:/contactUs";
	}
			
}
