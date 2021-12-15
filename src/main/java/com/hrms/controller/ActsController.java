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

import com.hrms.model.Acts;
import com.hrms.model.MenuModule;
import com.hrms.model.PathDirectory;
import com.hrms.service.ActsService;
import com.hrms.service.ModuleService;
import com.hrms.service.PathDirectoryServices;


@Controller
public class ActsController {
	
	@Autowired ModuleService  moduleService;
	@Autowired ActsService actsService;
	@Autowired 
	private PathDirectoryServices pathDirectoryServices;
	
	@InitBinder("acts")
    public void customizeBinding (WebDataBinder binder) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        dateFormatter.setLenient(false);
                binder.registerCustomEditor(Date.class, "dou",new CustomDateEditor(dateFormatter, true));
				binder.registerCustomEditor(Date.class, "actDoi", new CustomDateEditor(dateFormatter, true));

    }
	
	@GetMapping("/acts")
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
	  	List<Acts> listActs= actsService.getAllActs();
			model.addAttribute("listActs",listActs);
		return "acts";
	}
	
	
	@PostMapping("/saveActs")
	public String savePolicy(@ModelAttribute("acts") Acts acts, Model model,RedirectAttributes redirectAttributes, HttpSession session, MultipartFile file,HttpServletRequest request) throws FileNotFoundException
	{
	    MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> multipartFile = multipartRequest.getFiles("fileUpload");

        if (null!=multipartFile && !multipartFile.isEmpty()) {
            for (MultipartFile fileS : multipartFile) {
                try {
					if (fileS.getBytes().length > 0) {
						acts.setFile(fileS.getOriginalFilename());
						//String folderPath = "\\src\\main\\resources\\static\\pdfs\\Acts\\";
						String pathType="Acts";
						PathDirectory p = pathDirectoryServices.findPathByName(pathType);
						if(p==null)
						{
						redirectAttributes.addFlashAttribute("message", "Please make Directory !");
						redirectAttributes.addFlashAttribute("alertClass", "alert-success");
							return"redirect:/acts";	
						}
						 
						String folderPath = p.getUpldLocPath();
						//String uploadDir = System.getProperty("user.dir") + folderPath;
						String uploadDir =folderPath;
						  File uploadRootDir = new File(uploadDir); // Create directory if it notexists.
						  if (!uploadRootDir.exists()) 
						  {
							     redirectAttributes.addFlashAttribute("message", "Please make Directory! ");
								 redirectAttributes.addFlashAttribute("alertClass", "alert-success");
								return"redirect:/acts"; 
						  }
						
						String path = Paths.get(uploadDir + acts.getFile()).toString();
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
						stream.write(fileS.getBytes());
						stream.close();
						   acts.setInsBy((String) session.getAttribute("USER_NAME"));
						   acts.setFile(p.getPathFile()+fileS.getOriginalFilename());
						   actsService.addActs(acts);
						   redirectAttributes.addFlashAttribute("message", "Record   has been saved  successfully! ");
						   redirectAttributes.addFlashAttribute("alertClass", "alert-success");
						return"redirect:/acts";
					
					}
				} catch (IOException e) {
					
					e.printStackTrace();
				}

            }
        }
           acts.setInsBy((String) session.getAttribute("USER_NAME"));
		   actsService.addActs(acts);
		   redirectAttributes.addFlashAttribute("message", "Record  has been saved  successfully! ");
		   redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		   return"redirect:/acts";
        
        
	}
	
	
	@GetMapping(value = {"/editActs/{id}"})
	public String editActs(@PathVariable("id")long id,  Model model,HttpSession session)
	 { 
		Acts actsEdit = actsService.findActsById(id);
		model.addAttribute("actsEdit", actsEdit);
		session.setAttribute("actFile",actsEdit.getFile());
	    return "editActs";
	}	
	
	@PostMapping("/updateActs")
	public String updateActs(@ModelAttribute("acts")Acts acts, Model model,RedirectAttributes redirectAttributes,HttpSession session,@RequestParam("fileUpload")MultipartFile multipartFile) {
		   String actFile =(String) session.getAttribute("actFile");
		   String fileName2 = StringUtils.cleanPath(multipartFile.getOriginalFilename()); 
		   try 
		   {
				String pathType = "Acts";
				PathDirectory p = pathDirectoryServices.findPathByName(pathType);

				String folderPath = p.getUpldLocPath();
				//String uploadDir = System.getProperty("user.dir") + folderPath;
				String uploadDir =folderPath;
				String spl[] = actFile.split("/");
				String w = spl[3];
				System.out.println("Value of w" + w);
				File file = new File(uploadDir + w);
				// File file = new File(uploadDir +actFile);
			
				String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename()); 
			   
				if (fileName.trim().length() > 0) {
					file.delete();
					acts.setFile(fileName);
					String path = Paths.get(uploadDir + acts.getFile()).toString();
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
					stream.write(multipartFile.getBytes());
					stream.close();

					acts.setUpdBy((String) session.getAttribute("USER_NAME"));
					acts.setFile(p.getPathFile() + fileName);
					this.actsService.updateActs(acts);
					  redirectAttributes.addFlashAttribute("message", "Record  has been updated successfully! ");
					   redirectAttributes.addFlashAttribute("alertClass", "alert-success");
					   return"redirect:/acts";
				} else if (actFile != null) {
					acts.setFile(actFile);
					acts.setUpdBy((String) session.getAttribute("USER_NAME"));
					 this.actsService.updateActs(acts);
					  redirectAttributes.addFlashAttribute("message", "Record  has been updated successfully! ");
					   redirectAttributes.addFlashAttribute("alertClass", "alert-success");
					   return"redirect:/acts";
				}
		} 
		catch (Exception e) {
			System.out.println("exception " + e);
		}

		try {

			if (fileName2 != null) {

					String pathType = "Acts";
					PathDirectory p = pathDirectoryServices.findPathByName(pathType);
				
					String folderPath = p.getUpldLocPath();
					//String uploadDir = System.getProperty("user.dir") + folderPath;
					String uploadDir =folderPath;
					  File uploadRootDir = new File(uploadDir); // Create directory if it notexists.
					  if (!uploadRootDir.exists()) 
					  {
						     redirectAttributes.addFlashAttribute("message", "Please make Directory! ");
							 redirectAttributes.addFlashAttribute("alertClass", "alert-success");
							return"redirect:/acts"; 
					  }
					  
						acts.setFile(fileName2);
						String path = Paths.get(uploadDir + acts.getFile()).toString();
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
						stream.write(multipartFile.getBytes());
						stream.close();

					acts.setUpdBy((String) session.getAttribute("USER_NAME"));
					acts.setFile(p.getPathFile() + fileName2);
					this.actsService.updateActs(acts);
					redirectAttributes.addFlashAttribute("message", "Record  has been updated successfully! ");
					redirectAttributes.addFlashAttribute("alertClass", "alert-success");
					return "redirect:/acts";
				  
				}
			  
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		   
		acts.setUpdBy((String) session.getAttribute("USER_NAME"));
		this.actsService.updateActs(acts);
		redirectAttributes.addFlashAttribute("message", "Record  has been updated successfully! ");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return "redirect:/acts";
	  }
	
	@GetMapping(value = { "/deleteActs/{id}" })
	public String deleteActs(@PathVariable("id") long id, Model model,HttpSession session,RedirectAttributes redirectAttributes) {
		Acts yy = actsService.findActsById(id);
		try {

			this.actsService.removeActs(id);
			String pathType="Acts";
			PathDirectory p = pathDirectoryServices.findPathByName(pathType);
			 
			String folderPath = p.getUpldLocPath();
			//String uploadDir = System.getProperty("user.dir") + folderPath;
			String uploadDir =folderPath;
			String spl[]=yy.getFile().split("/");
			String w=spl[3];
			File file = new File(uploadDir + w);			
			
		
			if (file.delete()) {
				System.out.println(file.getName() + " is deleted!"); 
			} 
			else {
				System.out.println("Delete operation is failed.");
			}
			
			session.setAttribute("username", session.getAttribute("username"));
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			
		}
		this.actsService.removeActs(id);
		redirectAttributes.addFlashAttribute("message", "Record  has been Deleted Successfully! ");
	    redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return "redirect:/acts";
	}
}
