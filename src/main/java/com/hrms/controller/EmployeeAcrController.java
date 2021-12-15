package com.hrms.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hrms.ImageUtil;
import com.hrms.model.Department;
import com.hrms.model.Employee;
import com.hrms.model.EmployeeAcr;
import com.hrms.model.MenuModule;
import com.hrms.service.DepartmentService;
import com.hrms.service.EmployeeAcrService;
import com.hrms.service.EmployeeService;
import com.hrms.service.ModuleService;


@Controller
public class EmployeeAcrController {
	@Autowired EmployeeService employeeService;
	@Autowired DepartmentService departmentService;
	@Autowired private ModuleService moduleService;
	@Autowired EmployeeAcrService employeeAcrService;
	
	
	
	@InitBinder("employeeAcrEdit")
    public void customizeBinding (WebDataBinder binder) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        dateFormatter.setLenient(false);
                binder.registerCustomEditor(Date.class, "fromDate",new CustomDateEditor(dateFormatter, true));
                binder.registerCustomEditor(Date.class, "toDate",new CustomDateEditor(dateFormatter, true));
    }
	
	
	
	
	
	@GetMapping("/employeeAcr")
	public String employeeAcr(Model model,HttpSession session)
	{
		if(session.getAttribute("username")==null) {
			return "redirect:" + "./";
		}
		
		List<EmployeeAcr> listEmployeeAcr=employeeAcrService.getAllEmployeeAcr();
		model.addAttribute("listEmpAcr", listEmployeeAcr);
		List<Department> listDepartment = departmentService.getAllDepartments();
		model.addAttribute("listDepartment", listDepartment);
		String userCode= (String)session.getAttribute("username");
		session.setAttribute("imgUtil", new ImageUtil());
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
	
	return "employeeAcr";
}
	
	
	
	//@CrossOrigin
	@ResponseBody
    @GetMapping("/viewEmployeeAcrBydepartment/{id}")
    public List<Employee>  getLocalConvyenceById(@PathVariable(value = "id") String id,Model model,HttpSession session) {
		Department d=departmentService.findDepartmentById(id);
		List<Employee> e = employeeService.findByDepartmentCode(d.getDepartmentCode());
		  List<Employee> lisEmpUtil = new ArrayList<>();
		  for (int i = 0; i < e.size(); i++) 
		  {
			  String empCode = e.get(i).getEmpCode();
			  Employee lc = new Employee();
			  Employee employee = employeeService.findEmployeeById(empCode);
			  lc.setEmpCode(employee.getEmpCode());
			  lc.setEmpName(employee.getEmpName());
			  lisEmpUtil.add(lc);
		  }
		  return lisEmpUtil;
       
    }
	
	@ResponseBody
    @GetMapping("/viewEmployeeAcrByEmployee/{id}")
    public Employee  viewOverTimeRegisterByEmployee(@PathVariable(value = "id") String id,Model model,HttpSession session) {
		
		Employee employee = employeeService.findEmployeeById(id);
		Employee listEmp = new Employee();
			  
			 
			 
			  listEmp.setEmpCode(employee.getEmpCode());
			  listEmp.setEmpName(employee.getEmpName());
			  
		
		  return listEmp;
       
    }
	
	@PostMapping("/saveEmployeeAcr")
	public String saveEmployeeAcr(@ModelAttribute("EmployeeAcr")EmployeeAcr employeeAcr , Model model, HttpSession session, MultipartFile file,HttpServletRequest request,RedirectAttributes redirectAttributes)
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
			            List<MultipartFile> multipartFileList = multipartRequest.getFiles("empAcrUploadDoc" + i);
			
			            if (null != multipartFileList && !multipartFileList.isEmpty()) {
			
			                for (MultipartFile fileS : multipartFileList) {
			                    String fileName = fileS.getOriginalFilename().trim();
			                    if (fileS.getBytes().length > 0) {
			                    	
			                    	employeeAcr.setEmpAcrUploadDoc(uuid.toString().substring(0, 12)+"_"+fileS.getOriginalFilename());
			                    	String folderPath = "\\src\\main\\resources\\static\\employeedoc\\";
			            			String uploadDir = System.getProperty("user.dir") + folderPath;
			            			String path = Paths.get(uploadDir + employeeAcr.getEmpAcrUploadDoc()).toString();
			            			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
			            			stream.write(fileS.getBytes());
			            			stream.close();
			                    	
			                    
			                    }
			
			                }
			            }
			        } catch (Exception ex) {
			            ex.printStackTrace();
			        }
				
					
			if(request.getParameter("year" + i) != null) {
				employeeAcr.setYear(request.getParameter("year" + i));
			} else {
				employeeAcr.setYear("" + i);
			}
					if(request.getParameter("fromDate" + i) != null) {
						String sDate1 = request.getParameter("fromDate" + i);
						Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
						employeeAcr.setFromDate(date1);
						
					}
					if(request.getParameter("toDate" + i) != null) {
						
						String sDate1 = request.getParameter("toDate" + i);
						Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
						employeeAcr.setToDate(date1);
					}
					
					if(request.getParameter("remarks" + i) != null) {
						employeeAcr.setRemarks(request.getParameter("remarks" + i));
					} else {
						employeeAcr.setRemarks("" + i);
					}
					
					if(request.getParameter("grading" + i) != null) {
						employeeAcr.setGrading(request.getParameter("grading" + i));
					} else {
						employeeAcr.setGrading("" + i);
					}
					if(request.getParameter("empCode2" + i) != null) {
						emp.setEmpCode(request.getParameter("empCode2" + i));
						employeeAcr.setEmpCode(emp);
					} 
					employeeAcr.setInsBy((String) session.getAttribute("USER_NAME"));
					insertStatusMR= employeeAcrService.addEmployeeAcr(employeeAcr);	
					
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
		return "redirect:/employeeAcr";
		
	}
	
	@GetMapping(value = {"/editEmployeeAcr/{id}"})
	public String editEmployeeAcr(@PathVariable("id")long id,  Model model,HttpSession session)
	 { 	if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		
		List<Employee> lrt = employeeService.getAllEmployees();
		model.addAttribute("listEmployee", lrt);
		session.setAttribute("imgUtil", new ImageUtil());

		EmployeeAcr employeeAcrEdit =employeeAcrService.findByIdEmployeeAcr(id);
		model.addAttribute("employeeAcrEdit", employeeAcrEdit);
	     String mydoc=employeeAcrEdit.getEmpAcrUploadDoc();
		session.setAttribute("empAcrDoc",mydoc);
	   
	    return "editEmployeeAcr";
	}
	@PostMapping("/updateEmployeeAcr")
	public String updateEmployeeAcr(@ModelAttribute("employeeAcrEdit") EmployeeAcr employeeAcr, Model model,
			HttpSession session, MultipartFile file, HttpServletRequest request,RedirectAttributes redirectAttributes) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = multipartRequest.getFile("file");
		String empAcrDucument = (String) session.getAttribute("empAcrDoc");
		System.out.println("file name --- " + multipartFile.getOriginalFilename());

		try {
			UUID uuid = UUID.randomUUID();
			String folderPath = "\\src\\main\\resources\\static\\employeedoc\\";
			String uploadDir = System.getProperty("user.dir") + folderPath;
			File filef = new File(uploadDir + empAcrDucument);
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

			if (fileName.trim().length() > 0) {
				filef.delete();
				System.out.println(file.getName() + " is deleted!");
				employeeAcr.setEmpAcrUploadDoc(
						uuid.toString().substring(0, 12) + "_" + multipartFile.getOriginalFilename());
				String path = Paths.get(uploadDir + employeeAcr.getEmpAcrUploadDoc()).toString();
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
				stream.write(multipartFile.getBytes());
				stream.close();
				this.employeeAcrService.updateEmployeePromotion(employeeAcr);

			} else if (empAcrDucument != null) {

				employeeAcr.setEmpAcrUploadDoc(empAcrDucument);

				this.employeeAcrService.updateEmployeePromotion(employeeAcr);
			}
			employeeAcr.setUpdBy((String) session.getAttribute("USER_NAME"));
			this.employeeAcrService.updateEmployeePromotion(employeeAcr);

		} catch (Exception e) {
			e.printStackTrace();
		}
		employeeAcr.setUpdBy((String) session.getAttribute("USER_NAME"));
		this.employeeAcrService.updateEmployeePromotion(employeeAcr);
		   redirectAttributes.addFlashAttribute("message", "Record  has been updated successfully! ");
		   redirectAttributes.addFlashAttribute("alertClass", "alert-success"); 
		return "redirect:/employeeAcr";
	}
	
	
	@GetMapping(value = { "/deleteEmployeeAcr/{id}" })
	public String deleteEmployeeAcr(@PathVariable("id") long id,
			Model model, HttpSession session,RedirectAttributes redirectAttributes) {
		
		EmployeeAcr employeeAcr =employeeAcrService.findByIdEmployeeAcr(id);
		try {

			this.employeeAcrService.removeEmployeeAcr(id);
			String folderPath = "\\src\\main\\resources\\static\\employeedoc\\";
			String uploadDir = System.getProperty("user.dir") + folderPath;
			File file = new File(uploadDir + employeeAcr.getEmpAcrUploadDoc());

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
		return "redirect:/employeeAcr";
	}
	
	
	
	@GetMapping(value = { "/viewEmployeeAcr/{id}" })
	public String viewEmployeeAcr(@PathVariable("id") long id, Model model, HttpSession session) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		
		
		EmployeeAcr employeeAcrView = employeeAcrService.findByIdEmployeeAcr(id);
		model.addAttribute("employeeAcrView", employeeAcrView);
		  session.setAttribute("imgUtil", new ImageUtil());
		return "viewEmployeeAcr";
	}
	
}

