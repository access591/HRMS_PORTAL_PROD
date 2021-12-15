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

import com.hrms.model.MenuModule;
import com.hrms.model.Order;
import com.hrms.model.PathDirectory;
import com.hrms.service.ModuleService;
import com.hrms.service.OrderService;
import com.hrms.service.PathDirectoryServices;

@Controller
public class OrderController {
	@Autowired ModuleService  moduleService;
	@Autowired OrderService orderService;
	@Autowired private PathDirectoryServices pathDirectoryServices;
	@InitBinder("order")
    public void customizeBinding (WebDataBinder binder) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        dateFormatter.setLenient(false);
                binder.registerCustomEditor(Date.class, "dou",new CustomDateEditor(dateFormatter, true));
				binder.registerCustomEditor(Date.class, "orderDoi", new CustomDateEditor(dateFormatter, true));

    }
	
	@GetMapping("/order")
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
	  	List<Order> listOrder = orderService.getAllOrder();
			model.addAttribute("listOrder", listOrder);
		return "order";
	}
	
	
	@PostMapping("/saveOrder")
	public String savePolicy(@ModelAttribute("order") Order order, Model model,RedirectAttributes redirectAttributes, HttpSession session, MultipartFile file,HttpServletRequest request) throws FileNotFoundException
	{
	
	    MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> multipartFile = multipartRequest.getFiles("fileUpload");

        if (null != multipartFile && !multipartFile.isEmpty()) {
            for (MultipartFile fileS : multipartFile) {
                try {
					if (fileS.getBytes().length > 0) {
						
						order.setFile(fileS.getOriginalFilename());
						String pathType="Orders";
						PathDirectory p = pathDirectoryServices.findPathByName(pathType);
						if(p==null)
						{
							 redirectAttributes.addFlashAttribute("message", "Please Make Directory First! ");
							 redirectAttributes.addFlashAttribute("alertClass", "alert-success");
							return"redirect:/order";	
						}
					
						
						String folderPath = p.getUpldLocPath();
						//String uploadDir = System.getProperty("user.dir") + folderPath;
						String uploadDir =folderPath;
						
						File uploadRootDir = new File(uploadDir); // Create directory if it notexists.
						  if (!uploadRootDir.exists()) {
						         redirectAttributes.addFlashAttribute("message", "Please make Directory Local Folder First! ");
								 redirectAttributes.addFlashAttribute("alertClass", "alert-success");
								return"redirect:/order";}
						
						String path = Paths.get(uploadDir + order.getFile()).toString();
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
						stream.write(fileS.getBytes());
						stream.close();
						 order.setFile(p.getPathFile()+fileS.getOriginalFilename());
						 order.setInsBy((String) session.getAttribute("USER_NAME"));
					     orderService.addOrder(order);
					     redirectAttributes.addFlashAttribute("message", "Record has been saved Successfully! ");
						 redirectAttributes.addFlashAttribute("alertClass", "alert-success");
						return"redirect:/order";
					
					}
				} catch (IOException e) {
					
					e.printStackTrace();
				}

            }
        }
        order.setInsBy((String) session.getAttribute("USER_NAME"));
         orderService.addOrder(order);
        redirectAttributes.addFlashAttribute("message", "Record has been saved Successfully! ");
		 redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return"redirect:/order";
        
        
	}
	
	
	@GetMapping(value = {"/editOrder/{id}"})
	public String editPolicy(@PathVariable("id")long id,  Model model,HttpSession session)
	 { 
		Order orderEdit = orderService.findOrderById(id);
		model.addAttribute("orderEdit", orderEdit);
		session.setAttribute("orderFile",orderEdit.getFile());
	    return "editOrder";
	}	
	
	@PostMapping("/updateOrder")
	public String updatePolicy(@ModelAttribute("order")Order order, Model model,RedirectAttributes redirectAttributes,HttpSession session,@RequestParam("fileUpload") MultipartFile multipartFile) {
		   String orderFile =(String) session.getAttribute("orderFile");
		   try 
		   {

				String pathType="Orders";
				PathDirectory p = pathDirectoryServices.findPathByName(pathType);
				String folderPath = p.getUpldLocPath();
				//String uploadDir = System.getProperty("user.dir") + folderPath;
				String uploadDir =folderPath;
				String spl[]=orderFile.split("/");
				String w=spl[3];
				System.out.println("Value of w"+w);
				File file = new File(uploadDir + w);

			
				String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename()); 
			   
			   if (fileName.trim().length() > 0) {
					file.delete();
					order.setFile(fileName);
					
					
					String path = Paths.get(uploadDir + order.getFile()).toString();
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
					stream.write(multipartFile.getBytes());
					stream.close();
					order.setUpdBy((String) session.getAttribute("USER_NAME"));
					   order.setFile(p.getPathFile()+fileName);
					 this.orderService.updateOrder(order);   
					  redirectAttributes.addFlashAttribute("message", "Record  has been  updated successfully! ");
					   redirectAttributes.addFlashAttribute("alertClass", "alert-success");

				} else if (orderFile != null) {
					  order.setFile(orderFile);
					  order.setUpdBy((String) session.getAttribute("USER_NAME"));
					   this.orderService.updateOrder(order); 
					  redirectAttributes.addFlashAttribute("message", "Record has been  updated successfully! ");
					   redirectAttributes.addFlashAttribute("alertClass", "alert-success");
				}
		} 
		   	catch (Exception e) 
		   	{
		System.out.println("exception "+e);
		}  
		   
		   
			   order.setUpdBy((String) session.getAttribute("USER_NAME"));
			   this.orderService.updateOrder(order); 
			  redirectAttributes.addFlashAttribute("message", "Record has been  updated successfully! ");
			   redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		   
		   return"redirect:/order";
	}
	

	@GetMapping(value = { "/deleteOrder/{id}"})
	public String deleteRules(@PathVariable("id") long id, Model model,
			HttpSession session,RedirectAttributes redirectAttributes) {
		Order yy = orderService.findOrderById(id);
		try {
			this.orderService.removeOrder(id);
			String pathType="Orders";
			PathDirectory p = pathDirectoryServices.findPathByName(pathType);
			String folderPath = p.getUpldLocPath();
		//	String uploadDir = System.getProperty("user.dir") + folderPath;
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
		this.orderService.removeOrder(id);
		redirectAttributes.addFlashAttribute("message", "Record  has been deleted successfully! ");
	    redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return "redirect:/order";
	}
	
	
	

}
