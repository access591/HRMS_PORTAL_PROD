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
import com.hrms.model.Notification;
import com.hrms.model.PathDirectory;
import com.hrms.service.ModuleService;
import com.hrms.service.NotificationService;
import com.hrms.service.PathDirectoryServices;


@Controller
public class NotificationController {
	@Autowired ModuleService  moduleService;
	@Autowired NotificationService notificationService;
	@Autowired private PathDirectoryServices pathDirectoryServices;
	@InitBinder("notification")
    public void customizeBinding (WebDataBinder binder) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        dateFormatter.setLenient(false);
                binder.registerCustomEditor(Date.class, "dou",new CustomDateEditor(dateFormatter, true));
				binder.registerCustomEditor(Date.class, "notificationDoi", new CustomDateEditor(dateFormatter, true));

    }
	
	@GetMapping("/notification")
	public String order(Model model, HttpSession session)
	{
		String userCode = (String) session.getAttribute("username");
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
	  	List<Notification> listNotification = notificationService.getAllNotifications();
			model.addAttribute("listNotification",listNotification);
		return "notification";
	}
	
	
	
	@PostMapping("/saveNotification")
	public String savePolicy(@ModelAttribute("notification") Notification notification, Model model,RedirectAttributes redirectAttributes, HttpSession session, MultipartFile file,HttpServletRequest request) throws FileNotFoundException
	{
	
	    MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> multipartFile = multipartRequest.getFiles("fileUpload");

        if (null != multipartFile && !multipartFile.isEmpty()) {
            for (MultipartFile fileS : multipartFile) {
                try {
					if (fileS.getBytes().length > 0) {
						
						notification.setFile(fileS.getOriginalFilename());
					
						String pathType="Notifications";
						PathDirectory p = pathDirectoryServices.findPathByName(pathType);
						if(p==null)
						{
							 redirectAttributes.addFlashAttribute("message", "Please Make Directory First! ");
							 redirectAttributes.addFlashAttribute("alertClass", "alert-success");
							return"redirect:/notification";	
						}
						//String folderPath ="/src/main/resources/static"+p.getPathFile();
						String folderPath = p.getUpldLocPath();
						
						
						//String uploadDir = System.getProperty("user.dir") + folderPath;
						String uploadDir =folderPath;
						File uploadRootDir = new File(uploadDir); // Create directory if it notexists.
						  if (!uploadRootDir.exists())  {
							     redirectAttributes.addFlashAttribute("message", "Please make Directory Local Folder First! ");
								 redirectAttributes.addFlashAttribute("alertClass", "alert-success");
								return"redirect:/notification"; }
					
						String path = Paths.get(uploadDir + notification.getFile()).toString();
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
						stream.write(fileS.getBytes());
						stream.close();
						notification.setInsBy((String) session.getAttribute("USER_NAME"));
						
						notification.setFile(p.getPathFile()+fileS.getOriginalFilename());
						notificationService.addNotification(notification);
						redirectAttributes.addFlashAttribute("message", "Record  has been  saved successfully! ");
						redirectAttributes.addFlashAttribute("alertClass", "alert-success");
						return"redirect:/notification";
					
					}
				} catch (IOException e) {
					
					e.printStackTrace();
				}

            }
        }
        notification.setInsBy((String) session.getAttribute("USER_NAME"));
        notificationService.addNotification(notification);
        redirectAttributes.addFlashAttribute("message", "Record  has been saved successfully! ");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return"redirect:/notification";
        
        
	}
	
	
	@GetMapping(value = {"/editNotification/{id}"})
	public String editNotification(@PathVariable("id")long id,  Model model,HttpSession session)
	 { 
		Notification notificationEdit = notificationService.findNotificationById(id);
		model.addAttribute("notificationEdit", notificationEdit);
		session.setAttribute("nfcFile",notificationEdit.getFile());
	    return "editNotification";
	}	
	
	@PostMapping("/updateNotification")
	public String updateNotification(@ModelAttribute("notification") Notification notification, Model model,RedirectAttributes redirectAttributes,HttpSession session,@RequestParam("fileUpload") MultipartFile multipartFile) {
		 
		  String nfcFile =(String) session.getAttribute("nfcFile");
		   String fileName2 = StringUtils.cleanPath(multipartFile.getOriginalFilename()); 
		   try 
		   {
		
				String pathType="Notifications";
				PathDirectory p = pathDirectoryServices.findPathByName(pathType);

				String folderPath = p.getUpldLocPath();
				//String uploadDir = System.getProperty("user.dir") + folderPath;
				String uploadDir =folderPath;
				String spl[]=nfcFile.split("/");
				String w=spl[3];
				System.out.println("Value of w"+w);
				File file = new File(uploadDir + w);
				
				String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename()); 
		
			   if (fileName.trim().length() > 0) {
					file.delete();
					notification.setFile(fileName);
					String path = Paths.get(uploadDir + notification.getFile()).toString();
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
					stream.write(multipartFile.getBytes());
					stream.close();
					   notification.setUpdBy((String) session.getAttribute("USER_NAME"));
					   notification.setFile(p.getPathFile()+fileName);
					   this.notificationService.updateNotification(notification);   
					  redirectAttributes.addFlashAttribute("message", "Record  has been updated successfully! ");
					   redirectAttributes.addFlashAttribute("alertClass", "alert-success");
						return"redirect:/notification";

				} else if (nfcFile != null) {
					notification.setFile(nfcFile);
					notification.setUpdBy((String) session.getAttribute("USER_NAME"));
					   this.notificationService.updateNotification(notification);   
					  redirectAttributes.addFlashAttribute("message", "Record  has been updated successfully! ");
					   redirectAttributes.addFlashAttribute("alertClass", "alert-success");
						return"redirect:/notification";
				}
		} 
		   	catch (Exception e) 
		   	{
		System.out.println("exception "+e);
		}
		  try {
			
			   if(fileName2!=null)
				{
					String pathType="Notifications";
					PathDirectory p = pathDirectoryServices.findPathByName(pathType);

					String folderPath = p.getUpldLocPath();
					//String uploadDir = System.getProperty("user.dir") + folderPath; 
					String uploadDir =folderPath;
					File uploadRootDir = new File(uploadDir); //
					if (!uploadRootDir.exists())  {
					     redirectAttributes.addFlashAttribute("message", "Please make Directory Local Folder First! ");
						 redirectAttributes.addFlashAttribute("alertClass", "alert-success");
						return"redirect:/notification"; }
					notification.setFile(fileName2);
					String path2 = Paths.get(uploadDir + notification.getFile()).toString();
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path2)));
					stream.write(multipartFile.getBytes());
					stream.close();
					   notification.setUpdBy((String) session.getAttribute("USER_NAME"));
					   notification.setFile(p.getPathFile()+fileName2);
					   this.notificationService.updateNotification(notification);   
					  redirectAttributes.addFlashAttribute("message", "Record  has been updated successfully! ");
					   redirectAttributes.addFlashAttribute("alertClass", "alert-success");
						return"redirect:/notification";
				}     
			  
		} catch (Exception e) {
			e.printStackTrace();
		} 
		   notification.setUpdBy((String) session.getAttribute("USER_NAME"));
		   this.notificationService.updateNotification(notification);   
		  redirectAttributes.addFlashAttribute("message", "Record  has been updated successfully! ");
		   redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		   return"redirect:/notification";
	}
	
	
	@GetMapping(value = { "/deleteNotification/{id}" })
	public String deleteNotification(@PathVariable("id") long id, Model model,HttpSession session,RedirectAttributes redirectAttributes) {
		Notification yy = notificationService.findNotificationById(id);
		try {

			this.notificationService.removeNotification(id);
			String pathType="Notifications";
			PathDirectory p = pathDirectoryServices.findPathByName(pathType);
			String folderPath = p.getUpldLocPath();
			//String uploadDir = System.getProperty("user.dir") + folderPath;
			String uploadDir =folderPath;
			String spl[]=yy.getFile().split("/");
			String w=spl[3];
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
		this.notificationService.removeNotification(id);
		redirectAttributes.addFlashAttribute("message", "Record  has been deleted successfully! ");
	    redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return "redirect:/notification";
	}
	
	

}
