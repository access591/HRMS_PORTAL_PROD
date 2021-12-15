package com.hrms.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;


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

import com.hrms.ImageUtil;
import com.hrms.model.MenuModule;
import com.hrms.model.PathDirectory;
import com.hrms.model.Slider;
import com.hrms.service.ModuleService;
import com.hrms.service.PathDirectoryServices;
import com.hrms.service.SliderService;
@Controller
public class SliderController {
	@Autowired
	private ModuleService moduleService;
	@Autowired SliderService sliderService;
	@Autowired 
	private PathDirectoryServices pathDirectoryServices;
	@GetMapping("/slider")
	public String slider(Model model, HttpSession session)
	{
		
		
		String userCode = (String) session.getAttribute("username");
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}

		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		List<Slider> listSlider = sliderService.getAllSliders();
		model.addAttribute("listSlider", listSlider);
		
		return "sliderPage";
	}
	

	
	@PostMapping("/saveSlider")
	public String saveRules(@ModelAttribute("slider") Slider slider, Model model,RedirectAttributes redirectAttributes, HttpSession session, MultipartFile file,HttpServletRequest request) throws FileNotFoundException
	{

	    MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> multipartFile = multipartRequest.getFiles("fileUpload");

        if (null != multipartFile && !multipartFile.isEmpty()) {
            for (MultipartFile fileS : multipartFile) {
                try {
					if (fileS.getBytes().length > 0) {
						slider.setImgSrc(fileS.getOriginalFilename());
						String pathType="Carousel";
						PathDirectory p = pathDirectoryServices.findPathByName(pathType);
						if(p==null)
						{
							 redirectAttributes.addFlashAttribute("message", "Please Make Directory First! ");
							 redirectAttributes.addFlashAttribute("alertClass", "alert-success");
							return"redirect:/slider";	
						}
						String folderPath = p.getUpldLocPath();
						//String uploadDir = System.getProperty("user.dir") + folderPath;
						String uploadDir =folderPath;
						File uploadRootDir = new File(uploadDir);
				        if (!uploadRootDir.exists())
						  {      redirectAttributes.addFlashAttribute("message", "Please Make Directory Local HIII! ");
								 redirectAttributes.addFlashAttribute("alertClass", "alert-success");
								return"redirect:/slider"; 
						  } 
					        					
						String path = Paths.get(uploadDir + slider.getImgSrc()).toString();
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
						stream.write(fileS.getBytes());
						stream.close();
						
						slider.setImgName(fileS.getOriginalFilename());
						slider.setInsBy((String) session.getAttribute("USER_NAME"));
						slider.setImgSrc(p.getPathFile()+fileS.getOriginalFilename());
						sliderService.addSlider(slider);
						redirectAttributes.addFlashAttribute("message", "Record has been Saved  successfully! ");
						redirectAttributes.addFlashAttribute("alertClass", "alert-success");
						return"redirect:/slider";
					
					}
				} catch (IOException e) {
					
					e.printStackTrace();
				}

            }
        }
        slider.setInsBy((String) session.getAttribute("USER_NAME"));
        sliderService.addSlider(slider);
		redirectAttributes.addFlashAttribute("message", "Record has been Saved  successfully! ");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return"redirect:/slider";
        
		
	}
	
	@GetMapping(value = {"/editSlider/{id}"})
	public String editContactUs(@PathVariable("id")long id,  Model model,HttpSession session)
	 { 
		Slider sliderEdit = sliderService.findSliderById(id);
		model.addAttribute("sliderEdit", sliderEdit);
		session.setAttribute("imgSrc",sliderEdit.getImgSrc());
	    return "editSlider";
	}	
	
	@PostMapping("/updateSlider")
	public String updateSlider(@ModelAttribute("slider")Slider slider, Model model,RedirectAttributes redirectAttributes,HttpSession session,@RequestParam("fileUpload") MultipartFile multipartFile) {
		String imgSrc =(String) session.getAttribute("imgSrc");
	 String fileName2 = StringUtils.cleanPath(multipartFile.getOriginalFilename()); 
		
		try 
		   {
					String pathType="Carousel";
					PathDirectory p = pathDirectoryServices.findPathByName(pathType);
					String folderPath = p.getUpldLocPath();
					//String uploadDir = System.getProperty("user.dir") + folderPath;
					String uploadDir =folderPath;
				
					String spl[] = imgSrc.split("/");
					String w = spl[3];
					System.out.println("Value of w" + w);
					File file = new File(uploadDir + w);
					//File file = new File(uploadDir +conImg);
				
					String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename()); 
				  
				   if (fileName.trim().length() > 0) {
						file.delete();
						slider.setImgSrc(fileName);
						String path = Paths.get(uploadDir + slider.getImgSrc()).toString();
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
						stream.write(multipartFile.getBytes());
						stream.close();
				
						slider.setImgName(fileName);
						slider.setImgSrc(p.getPathFile() + fileName);
						slider.setUpdBy((String) session.getAttribute("USER_NAME"));
						this.sliderService.updateSlider(slider);
						redirectAttributes.addFlashAttribute("message", "Record  has been Updated Successfully! ");
						redirectAttributes.addFlashAttribute("alertClass", "alert-success");
						return "redirect:/slider";
					} else if (imgSrc != null) {
						
						slider.setImgName(w);
						slider.setImgSrc(imgSrc);
						slider.setUpdBy((String) session.getAttribute("USER_NAME"));
						this.sliderService.updateSlider(slider);
						redirectAttributes.addFlashAttribute("message", "Record  has been Updated Successfully!! ");
						redirectAttributes.addFlashAttribute("alertClass", "alert-success");
						return "redirect:/slider";
					}
		   } 

				   	catch (Exception e) 
				   	{
				System.out.println("exception "+e);
				}
		
		 try {
				if (fileName2 != null) {
					String pathType = "Carousel";
					PathDirectory p = pathDirectoryServices.findPathByName(pathType);
					// String folderPath = "/src/main/resources/static"+p.getPathFile();
					String folderPath = p.getUpldLocPath();

					//String uploadDir = System.getProperty("user.dir") + folderPath;
					String uploadDir =folderPath;
					File uploadRootDir = new File(uploadDir);
					if (!uploadRootDir.exists()) {
						redirectAttributes.addFlashAttribute("message", "Please Make Directory Local Folder First! ");
						redirectAttributes.addFlashAttribute("alertClass", "alert-success");
						return "redirect:/slider";
					}

					slider.setImgSrc(fileName2);
					String path = Paths.get(uploadDir + slider.getImgSrc()).toString();
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
					stream.write(multipartFile.getBytes());
					stream.close();
					
					slider.setUpdBy((String) session.getAttribute("USER_NAME"));
					slider.setImgName(fileName2);
					slider.setImgSrc(p.getPathFile() + fileName2);
					this.sliderService.updateSlider(slider);   
				    redirectAttributes.addFlashAttribute("message", "Record  has been Updated Successfully! ");
				    redirectAttributes.addFlashAttribute("alertClass", "alert-success");
					return "redirect:/slider";
			   }
			   
		} catch (Exception e) {
			System.out.println("exception "+e);
		}
		 	slider.setUpdBy((String) session.getAttribute("USER_NAME"));
			this.sliderService.updateSlider(slider);  
		    redirectAttributes.addFlashAttribute("message", "Record  has been Updated Successfully! ");
		    redirectAttributes.addFlashAttribute("alertClass", "alert-success");
			 return"redirect:/slider";
			}
	
	@GetMapping(value = { "/deleteSlider/{id}" })
	public String deleteSlider(@PathVariable("id") long id, Model model,HttpSession session,RedirectAttributes redirectAttributes) {
		Slider yy = sliderService.findSliderById(id);
		try {

			this.sliderService.removeSlider(id);
			String pathType="Carousel";
			PathDirectory p = pathDirectoryServices.findPathByName(pathType);
			String folderPath = p.getUpldLocPath();
			//String uploadDir = System.getProperty("user.dir") + folderPath;
			String uploadDir =folderPath;
			
			
			String spl[] = yy.getImgSrc().split("/");
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
		this.sliderService.removeSlider(id);
		redirectAttributes.addFlashAttribute("message", "Record  has been Deleted Successfully! ");
	    redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return "redirect:/slider";
	}
	
	
	
	
	
}
