package com.hrms.controller;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.io.File;
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

import com.hrms.ImageUtil;
import com.hrms.model.AttendenceRegister;
import com.hrms.model.Department;
import com.hrms.model.Designation;
import com.hrms.model.Employee;
import com.hrms.model.EmployeePromotion;
import com.hrms.model.MenuModule;
import com.hrms.model.TourPlan;
import com.hrms.service.DepartmentService;
import com.hrms.service.EmployeePromotionService;
import com.hrms.service.EmployeeService;
import com.hrms.service.ModuleService;


@Controller
public class EmployeePromotionController {
	
	@Autowired EmployeeService employeeService;
	@Autowired DepartmentService departmentService;
	@Autowired private ModuleService moduleService;
	@Autowired EmployeePromotionService employeePromotionService;
	
	@InitBinder("employeePromotionEdit")
    public void customizeBinding (WebDataBinder binder) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        dateFormatter.setLenient(false);
                binder.registerCustomEditor(Date.class, "promotionDate",new CustomDateEditor(dateFormatter, true));
    }

	
	
	
	@GetMapping("/employeePromotion")
	public String employeePromotion(Model model,HttpSession session)
	{
		if(session.getAttribute("username")==null) {
			return "redirect:" + "./";
		}
		
		List<EmployeePromotion> listEmployeePromotion=employeePromotionService.getAllEmployeePromotion();
		model.addAttribute("listEmpPromo", listEmployeePromotion);
		List<Department> listDepartment = departmentService.getAllDepartments();
		model.addAttribute("listDepartment", listDepartment);
		String userCode= (String)session.getAttribute("username");
		session.setAttribute("imgUtil", new ImageUtil());
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		
		
		return "employeePromotion";
	}
	
	
	@PostMapping("/saveEmployeePromotion")
	public String saveEmployeePromotion(@ModelAttribute("saveEmployeePromotion")EmployeePromotion employeePromotion , Model model, HttpSession session, MultipartFile file,HttpServletRequest request,RedirectAttributes redirectAttributes)
	{
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		
		Employee emp=new Employee();
		
		 int flag = 0;
	   		int counter = 1;
			try {
				UUID uuid=UUID.randomUUID();
			
				
				
				boolean insertStatusMR = false;
				counter = Integer.parseInt(request.getParameter("_cr"));
				System.out.println("counter::::::::::::::::::::" + counter);
				for (int i =0; i < counter; i++) 
				{
					
			try {
			            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			            List<MultipartFile> multipartFileList = multipartRequest.getFiles("empUploadDoc" + i);
			
			            if (null != multipartFileList && !multipartFileList.isEmpty()) {
			
			                for (MultipartFile fileS : multipartFileList) {
			                    String fileName = fileS.getOriginalFilename().trim();
			                    if (fileS.getBytes().length > 0) {
			                    	
			                    	employeePromotion.setEmpUploadDoc(uuid.toString().substring(0, 12)+"_"+fileS.getOriginalFilename());
			                    	String folderPath = "\\src\\main\\resources\\static\\employeedoc\\";
			            			String uploadDir = System.getProperty("user.dir") + folderPath;
			            			String path = Paths.get(uploadDir + employeePromotion.getEmpUploadDoc()).toString();
			            			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
			            			stream.write(fileS.getBytes());
			            			stream.close();
			                    	
			                    
			                    }
			
			                }
			            }
			        } catch (Exception ex) {
			            ex.printStackTrace();
			        }
				
					
					if(request.getParameter("newPost" + i) != null) {
						employeePromotion.setNewPost(request.getParameter("newPost" + i));
					} else {
						employeePromotion.setNewPost("" + i);
					}
					
					if(request.getParameter("oldPost" + i) != null) {
						employeePromotion.setOldPost(request.getParameter("oldPost" + i));
					} else {
						employeePromotion.setOldPost("" + i);
					}
				
					if(request.getParameter("promotionDate" + i) != null) {
						
						String sDate1 = request.getParameter("promotionDate" + i);
						Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
						employeePromotion.setPromotionDate(date1);
						
					}
					
					if(request.getParameter("remarks" + i) != null) {
						employeePromotion.setRemarks(request.getParameter("remarks" + i));
					} else {
						employeePromotion.setRemarks("" + i);
					}
					
					
					if(request.getParameter("empCode2" + i) != null) {
						emp.setEmpCode(request.getParameter("empCode2" + i));
						employeePromotion.setEmpCode(emp);
					} 
					
					employeePromotion.setInsBy((String) session.getAttribute("USER_NAME"));
					insertStatusMR= employeePromotionService.addEmployeePromotion(employeePromotion);	
					
					if (insertStatusMR) {
						System.out.println("Counter" + flag);
						flag++;

					}
					
				}
				
				if (flag > 0) {
				
					redirectAttributes.addFlashAttribute("message", "Record  has been saved successfully!");
					redirectAttributes.addFlashAttribute("alertClass", "alert-success");	
				} else {
					System.out.println("Enter into  failure part :");
					
				}

			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
			session.setAttribute("username", session.getAttribute("username"));
			
		return "redirect:/employeePromotion";
		
		
	}
	
	@GetMapping(value = {"/editEmployeePromotion/{id}"})
	public String editTourPlan(@PathVariable("id")long id,  Model model,HttpSession session)
	 { 	if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		
		
		List<Employee> lrt = employeeService.getAllEmployees();
		model.addAttribute("listEmployee", lrt);
		session.setAttribute("imgUtil", new ImageUtil());

		EmployeePromotion employeePromotionEdit =employeePromotionService.findByIdEmployeePromotion(id);
		model.addAttribute("employeePromotionEdit", employeePromotionEdit);
	     String mydoc=employeePromotionEdit.getEmpUploadDoc();
		session.setAttribute("empDoc",mydoc);
	   
	    return "editEmployeePromotion";
	}
	@PostMapping("/updateEmployeePromotion")
	public String updateEmployeePromotion(@ModelAttribute("employeePromotionEdit") EmployeePromotion employeePromotion, Model model, HttpSession session, MultipartFile file,HttpServletRequest request,RedirectAttributes redirectAttributes)
	{
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		
		 MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		 MultipartFile multipartFile = multipartRequest.getFile("file");
		 String empProDuc= (String)session.getAttribute("empDoc");
		System.out.println("file name --- "+multipartFile.getOriginalFilename());
		
		
		try {
			UUID uuid = UUID.randomUUID();
			String folderPath = "\\src\\main\\resources\\static\\employeedoc\\";
			String uploadDir = System.getProperty("user.dir") + folderPath;
			File filef = new File(uploadDir + empProDuc);
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			

			if (fileName.trim().length() > 0) {
				filef.delete(); 
				System.out.println(file.getName() + " is deleted!"); 
				employeePromotion.setEmpUploadDoc(uuid.toString().substring(0, 12) + "_" + multipartFile.getOriginalFilename());
				String path = Paths.get(uploadDir +employeePromotion.getEmpUploadDoc()).toString();
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
				stream.write(multipartFile.getBytes());
				stream.close();
				this.employeePromotionService.updateEmployeePromotion(employeePromotion);

				
				  } else if (empProDuc != null) 
				  { 
					  
					  employeePromotion.setEmpUploadDoc(empProDuc);
				  
					  employeePromotion.setUpdBy((String) session.getAttribute("USER_NAME"));
				  this.employeePromotionService.updateEmployeePromotion(employeePromotion); }
				 

			this.employeePromotionService.updateEmployeePromotion(employeePromotion);

			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		  employeePromotion.setUpdBy((String) session.getAttribute("USER_NAME"));
		this.employeePromotionService.updateEmployeePromotion(employeePromotion);
		   redirectAttributes.addFlashAttribute("message", "Record  has been updated successfully! ");
		   redirectAttributes.addFlashAttribute("alertClass", "alert-success");  
		return "redirect:/employeePromotion";
	}
	
	
	@GetMapping(value = { "/deleteEmployeePromotion/{id}" })
	public String deleteEmployeePromotion(@PathVariable("id") long id, Model model,
			HttpSession session,RedirectAttributes redirectAttributes) {
		EmployeePromotion employeePromotion=employeePromotionService.findByIdEmployeePromotion(id);
		try {

			this.employeePromotionService.removeEmployeePromotion(id);
			String folderPath = "\\src\\main\\resources\\static\\employeedoc\\";
			String uploadDir = System.getProperty("user.dir") + folderPath;
			File file = new File(uploadDir + employeePromotion.getEmpUploadDoc());

			if (file.delete()) {
				System.out.println(file.getName() + " is deleted!"); 
			} else {
				System.out.println("Delete operation is failed.");

			}

			session.setAttribute("username", session.getAttribute("username"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		 session.setAttribute("imgUtil", new ImageUtil());
			redirectAttributes.addFlashAttribute("message", "Record  has been deleted successfully!");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return "redirect:/employeePromotion";
	}
	

	
	
	@GetMapping(value = { "/viewEmployeePromotion/{id}" })
	public String viewEmployeePromotion(@PathVariable("id") long id, Model model, HttpSession session) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		
		
		EmployeePromotion employeePromotionView = employeePromotionService.findByIdEmployeePromotion(id);
		model.addAttribute("employeePromotionView", employeePromotionView);
		  session.setAttribute("imgUtil", new ImageUtil());
		return "viewEmployeePromotion";
	}
	
	
	
}
