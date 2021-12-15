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

import com.hrms.model.LatestUpdate;
import com.hrms.model.MenuModule;
import com.hrms.model.PathDirectory;
import com.hrms.service.LatestUpdateService;
import com.hrms.service.ModuleService;
import com.hrms.service.PathDirectoryServices;

@Controller
public class LatestUpdateController {
	@Autowired
	private ModuleService moduleService;
	@Autowired  LatestUpdateService latestUpdateService;
	@Autowired 
	private PathDirectoryServices pathDirectoryServices;
	@InitBinder("latestUpdate")
    public void customizeBinding (WebDataBinder binder) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        dateFormatter.setLenient(false);
                binder.registerCustomEditor(Date.class, "doi",new CustomDateEditor(dateFormatter, true));
			

    }
	@GetMapping("/latestUpdate")
	public String latestUpdate(Model model, HttpSession session)
	{
		String userCode = (String) session.getAttribute("username");
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
	  	List<LatestUpdate> listLatestUpdate = latestUpdateService.getAllLatestUpdate();
			model.addAttribute("listLatestUpdate", listLatestUpdate);
		return "latestUpdate";
	}
	
	@PostMapping("/saveLatestUpdate")
	public String saveRules(@ModelAttribute("latestUpdate") LatestUpdate latestUpdate, Model model,RedirectAttributes redirectAttributes, HttpSession session, MultipartFile file,HttpServletRequest request) throws FileNotFoundException
	{
		
	
	    MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> multipartFile = multipartRequest.getFiles("fileUpload");

        if (null != multipartFile && !multipartFile.isEmpty()) {
            for (MultipartFile fileS : multipartFile) {
                try {
					if (fileS.getBytes().length > 0) {
						
						latestUpdate.setFile(fileS.getOriginalFilename());
						
						String pathType="LatestUpdates";
						PathDirectory p = pathDirectoryServices.findPathByName(pathType);
						if(p==null)
						{
							 redirectAttributes.addFlashAttribute("message", "Please Make Directory First! ");
							 redirectAttributes.addFlashAttribute("alertClass", "alert-success");
							return"redirect:/latestUpdate";	
						}
						//String folderPath = "/src/main/resources/static"+p.getPathFile();
					     	String folderPath = p.getUpldLocPath();
							//String uploadDir = System.getProperty("user.dir") + folderPath;
							String uploadDir =folderPath;
							
							File uploadRootDir = new File(uploadDir); // Create directory if it notexists.
						  if (!uploadRootDir.exists())
						  {      redirectAttributes.addFlashAttribute("message", "Please make Directory Local Folder First! ");
								 redirectAttributes.addFlashAttribute("alertClass", "alert-success");
								return"redirect:/latestUpdate"; 
						  }
						
						String path = Paths.get(uploadDir + latestUpdate.getFile()).toString();
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
						stream.write(fileS.getBytes());
						stream.close();
						latestUpdate.setFile(p.getPathFile()+fileS.getOriginalFilename());
						latestUpdate.setInsBy((String) session.getAttribute("USER_NAME"));
						latestUpdateService.addLatestUpdate(latestUpdate);
						   redirectAttributes.addFlashAttribute("message", "Record  has been saved  successfully! ");
						   redirectAttributes.addFlashAttribute("alertClass", "alert-success");
						return"redirect:/latestUpdate";
					
					}
				} catch (IOException e) {
					
					e.printStackTrace();
				}

            }
        }
            latestUpdate.setInsBy((String) session.getAttribute("USER_NAME"));
           latestUpdateService.addLatestUpdate(latestUpdate);
		   redirectAttributes.addFlashAttribute("message", "Record  has been saved  successfully! ");
		   redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return"redirect:/latestUpdate";
	}
	@GetMapping(value = {"/editLatestUpdate/{id}"})
	public String editNotification(@PathVariable("id")long id,  Model model,HttpSession session)
	 { 
		LatestUpdate latestUpdateEdit = latestUpdateService.findLatestUpdateById(id);
		model.addAttribute("latestUpdateEdit", latestUpdateEdit);
		session.setAttribute("luFile",latestUpdateEdit.getFile());
	    return "editLatestUpdate";
	}	
	
	@PostMapping("/updateLatestUpdate")
	public String updateLatestUpdate(@ModelAttribute("latestUpdate") LatestUpdate latestUpdate, Model model,RedirectAttributes redirectAttributes,HttpSession session,@RequestParam("fileUpload")MultipartFile multipartFile) {

		String luFile =(String) session.getAttribute("luFile");
		  String fileName2 = StringUtils.cleanPath(multipartFile.getOriginalFilename()); 
		   try 
		   {
			  
				String pathType="LatestUpdates";
				PathDirectory p = pathDirectoryServices.findPathByName(pathType);
				String folderPath = p.getUpldLocPath();
				//String folderPath = "/src/main/resources/static"+p.getPathFile();
				
				
				//String uploadDir = System.getProperty("user.dir") + folderPath;
				String uploadDir =folderPath;
				String spl[]=luFile.split("/");
				String w=spl[3];
				System.out.println("Value of w"+w);
				File file = new File(uploadDir + w);
				
				//File file = new File(uploadDir +luFile);
			
				String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename()); 
			   
			   if (fileName.trim().length() > 0) {
					file.delete();
					latestUpdate.setFile(fileName);
					
					String path = Paths.get(uploadDir + latestUpdate.getFile()).toString();
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
					stream.write(multipartFile.getBytes());
					stream.close();
					latestUpdate.setFile(p.getPathFile()+fileName);
					latestUpdate.setUpdBy((String) session.getAttribute("USER_NAME"));
					 this.latestUpdateService.updateLatestUpdate(latestUpdate);    
					  redirectAttributes.addFlashAttribute("message", "Record  has been Update successfully! ");
					   redirectAttributes.addFlashAttribute("alertClass", "alert-success");
						return"redirect:/latestUpdate"; 

				} else if (luFile != null) {
					latestUpdate.setFile(luFile);
					latestUpdate.setUpdBy((String) session.getAttribute("USER_NAME"));
					 this.latestUpdateService.updateLatestUpdate(latestUpdate);    
					 redirectAttributes.addFlashAttribute("message", "Record  has been updated successfully! ");
					 redirectAttributes.addFlashAttribute("alertClass", "alert-success");
						return"redirect:/latestUpdate"; 
				}
		} 
		   	catch (Exception e) 
		   	{
		System.out.println("exception "+e);
		}
		try {
			
			 if(fileName2!=null)
				{
			String pathType="LatestUpdates";
			PathDirectory p = pathDirectoryServices.findPathByName(pathType);
			String folderPath = p.getUpldLocPath();
		//	String uploadDir = System.getProperty("user.dir") + folderPath; 
			String uploadDir =folderPath;
			File uploadRootDir = new File(uploadDir);
			if (!uploadRootDir.exists())  {
			     redirectAttributes.addFlashAttribute("message", "Please make Directory Local Folder First! ");
				 redirectAttributes.addFlashAttribute("alertClass", "alert-success");
				return"redirect:/latestUpdate"; }
			latestUpdate.setFile(fileName2);
			String path2 = Paths.get(uploadDir + latestUpdate.getFile()).toString();
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path2)));
			stream.write(multipartFile.getBytes());
			stream.close();
			
			latestUpdate.setFile(p.getPathFile()+fileName2);
			latestUpdate.setUpdBy((String) session.getAttribute("USER_NAME"));
			this.latestUpdateService.updateLatestUpdate(latestUpdate);    
			redirectAttributes.addFlashAttribute("message", "Record  has been updated successfully! ");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");

				}
			
			
		} catch (Exception e) {
			
		}
		  
		latestUpdate.setUpdBy((String) session.getAttribute("USER_NAME"));
		 this.latestUpdateService.updateLatestUpdate(latestUpdate);    
		  redirectAttributes.addFlashAttribute("message", "Record  has been updated successfully! ");
		   redirectAttributes.addFlashAttribute("alertClass", "alert-success");	   
		   return"redirect:/latestUpdate";
	}
		
	@GetMapping(value = { "/deleteLatestUpdate/{id}" })
	public String deleteNotification(@PathVariable("id") long id,  Model model,HttpSession session,RedirectAttributes redirectAttributes) {
		LatestUpdate yy = latestUpdateService.findLatestUpdateById(id);
		try {

			this.latestUpdateService.removeLatestUpdate(id);
			String pathType="LatestUpdates";
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
		this.latestUpdateService.removeLatestUpdate(id);
		redirectAttributes.addFlashAttribute("message", "Record  has been deleted Successfully! ");
	    redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return "redirect:/latestUpdate";
	}
	
}
