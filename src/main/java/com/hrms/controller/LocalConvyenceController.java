package com.hrms.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hrms.ImageUtil;
import com.hrms.model.Department;
import com.hrms.model.Designation;
import com.hrms.model.Employee;
import com.hrms.model.LocalConvyence;
import com.hrms.model.LocalConvyenceDetail;
import com.hrms.util.LocalConvyenceUtil;
import com.hrms.model.MenuModule;

import com.hrms.service.DepartmentService;
import com.hrms.service.DesignationService;
import com.hrms.service.EmployeeService;
import com.hrms.service.LocalConvyenceDetailService;
import com.hrms.service.LocalConvyenceService;
import com.hrms.service.ModuleService;

@Controller
public class LocalConvyenceController {
	@Autowired
	private ModuleService moduleService;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	DepartmentService  departmentService;
	@Autowired
	DesignationService designationService;
	@Autowired
	LocalConvyenceService  localConvyenceService;
	@Autowired
	LocalConvyenceDetailService localConvyenceDetailService;
	
	
	@InitBinder("localConvyence")
    public void customizeBinding (WebDataBinder binder) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        dateFormatter.setLenient(false);
                binder.registerCustomEditor(Date.class, "localConvDate",new CustomDateEditor(dateFormatter, true));
       
    }
			
	@InitBinder("localConvyenceEdit")
    public void customizeBindingedit (WebDataBinder binder) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        dateFormatter.setLenient(false);
        binder.registerCustomEditor(Date.class, "localConvDate",new CustomDateEditor(dateFormatter, true));
        binder.registerCustomEditor(Date.class, "locDate",new CustomDateEditor(dateFormatter, true));
       
    }
	
	
	
	
	@GetMapping("/localConvyence")
	public String localConvyence(Model model,HttpSession session) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		LocalConvyenceUtil u=new LocalConvyenceUtil();
		  List<Employee> listEmployee = employeeService.getAllEmployees();
		  model.addAttribute("listEmployee", listEmployee);
		
		  List<LocalConvyence> listOfLoc = localConvyenceService.getAlllocalConvyence();
		  List<LocalConvyenceUtil> listLocalConvyenceUtil = new ArrayList<>();
		  for (int i = 0; i < listOfLoc.size(); i++) {
			    
			    String empCode = listOfLoc.get(i).getEmpCode().getEmpCode();
			    LocalConvyenceUtil lc = new LocalConvyenceUtil();
			    Employee employee = employeeService.findEmployeeById(empCode);
			    Department department = departmentService.findDepartmentById(employee.getDepartment().getDepartmentCode());
			    Designation designation = designationService.findDesignationById(employee.getDesignation().getDesgCode());
			    
				lc.setLocalConvId(listOfLoc.get(i).getLocalConvId());
				lc.setDeptName(department.getDeptName());
				lc.setDesgName(designation.getDesgName());
				lc.setEmpName(employee.getEmpName());
				lc.setApprovalStatus(listOfLoc.get(i).getApprovalStatus());
				listLocalConvyenceUtil.add(lc);

		  }  
		  
		  model.addAttribute("listOfLoc", listLocalConvyenceUtil);
		  
		  String userCode= (String)session.getAttribute("username");
		  session.setAttribute("imgUtil", new ImageUtil());
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
	  
	  session.setAttribute("username",session.getAttribute("username"));
	  return"localConvyence";
		}

	
	//@CrossOrigin
	@ResponseBody
    @GetMapping("/viewEmployeeDetails/{id}")
    public LocalConvyenceUtil  getLocalConvyenceById(@PathVariable(value = "id") String id) {
		Employee e = employeeService.findEmployeeById(id);
		

		
		Department d = departmentService.findDepartmentById(e.getDepartment().getDepartmentCode());
		Designation des=designationService.findDesignationById(e.getDesignation().getDesgCode());
		return  new LocalConvyenceUtil(e.getEmpName(),d.getDeptName(),des.getDesgName(),e.getPayeeCode());
		
    }

	
	@PostMapping("/saveLocalConvyence")
	public String saveLocalConvyence(@ModelAttribute("localConvyence")LocalConvyenceUtil localCon, Model model, HttpSession session,HttpServletRequest request,RedirectAttributes redirectAttributes) throws ParseException {
		
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		
		String insertedBY = (String) session.getAttribute("USER_NAME");
		LocalConvyence lc=new LocalConvyence();
		LocalConvyenceDetail ld=new LocalConvyenceDetail();
		
		
		Employee emp = new Employee();
		emp.setEmpCode(localCon.getEmpCode());
		lc.setEmpCode(emp);
		lc.setLocalConvDate(localCon.getLocalConvDate());
		lc.setInsBy(insertedBY);
		lc.setApprovalStatus("N");
		lc.setTotalClaim(localCon.getTotalClaim());
		lc.setTotalPas(localCon.getTotalPas());
		localConvyenceService.addlocalConvyence(lc);
		
		String localCVid=lc.getLocalConvId();
		Date lcDate=localCon.getLocalConvDate();
		 int flag = 0;
	   		int counter = 1;
			try {
				
				
				boolean insertStatusMR = false;
				counter = Integer.parseInt(request.getParameter("_cr"));
				System.out.println("counter::::::::::::::::::::" + counter);
				
			
				for (int i =0; i < counter; i++) 
				{
					System.out.println("for loop run time>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
					
					
					if (request.getParameter("startPlace" + i) != null) {
						ld.setStartPlace(request.getParameter("startPlace" + i));
					} else {
						ld.setStartPlace("" + i);
					}
					
					if(request.getParameter("vistPlace" + i) != null) {
						ld.setVistPlace(request.getParameter("vistPlace" + i));
					} else {
						ld.setVistPlace("" + i);
					}
					
				
					if(request.getParameter("purposeOfVist" + i) != null) {
						ld.setPurposeOfVist(request.getParameter("purposeOfVist" + i));
					} else {
						ld.setPurposeOfVist("" + i);
					}
					
					
					  if(request.getParameter("locDate" + i) != null) {
						  
						  String sDate1=request.getParameter("locDate" + i);
						  Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);  

						  ld.setLocDate(date1);
					  } 

					if(request.getParameter("modeOfTravel" + i) != null) {
						ld.setModeOfTravel(request.getParameter("modeOfTravel" + i));
					} else {
						ld.setModeOfTravel("" + i);
					}
					
					
					if(request.getParameter("distanceKm" + i) != null) {
						ld.setDistanceKm(request.getParameter("distanceKm" + i));
					} else {
						ld.setDistanceKm("" + i);
					}
					
					if(request.getParameter("ltaRate" + i) != null) {
						ld.setLtaRate(request.getParameter("ltaRate" + i));
					} else {
						ld.setLtaRate("" + i);
					}
					
					if(request.getParameter("actualAmount" + i) != null) {
						ld.setActualAmount(request.getParameter("actualAmount" + i));
					} else {
						ld.setActualAmount("" + i);
					}
					
					if(request.getParameter("claimedAmount" + i) != null) {
						ld.setClaimedAmount(request.getParameter("claimedAmount" + i));
					} else {
						ld.setClaimedAmount("" + i);
					}
					
					
					
			
				lc.setLocalConvId(localCVid);
				lc.setLocalConvDate(lcDate);
				
				ld.setLocalConvId(lc);
				ld.setEmpCode(emp);	
				ld.setLocalConvDate(lc);
					
					
					insertStatusMR= localConvyenceDetailService.addTourPlanDetail(ld);
					
					if (insertStatusMR) {
						System.out.println("Counter" + flag);
						flag++;

					}
					
				}
				
				
				if (flag > 0) {
					session.setAttribute("Message", "Data added successfully.");
					
				} else {
					System.out.println("Enter into  failure part :");
					
				}

			
			} catch (Exception e) {
				
				
				e.printStackTrace();
			}
		
			redirectAttributes.addFlashAttribute("message", "Record  has been saved successfully!");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
			session.setAttribute("username", session.getAttribute("username"));

		return "redirect:/localConvyence";
	}
	
	@GetMapping(value = {"/editLocalConvyence/{id}"})
	public String editLocalConvyence(@PathVariable("id")String id,  Model model,HttpSession session)
	 { 	if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		session.setAttribute("imgUtil", new ImageUtil());
		List<Employee> lrt = employeeService.getAllEmployees();
		model.addAttribute("listEmployee", lrt);
		
		LocalConvyence localConvyenceEdit =	localConvyenceService.findByIdLocalConvyence(id);
		  model.addAttribute("localConvyenceEdit", localConvyenceEdit);

	   
	    return "editLocalConvyence";
	}
	
	
	@PostMapping("/updateLocalConvyence")
	  public String updateLocalConvyence(@ModelAttribute("localConvyenceEdit") LocalConvyence localConvyence, Model model,HttpSession session,RedirectAttributes redirectAttributes) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		
		  try {
			  localConvyence.setApprovalStatus("N");
			  this.localConvyenceService.updateLocalConvyence(localConvyence);
			   redirectAttributes.addFlashAttribute("message", "Record  has been updated successfully! ");
			   redirectAttributes.addFlashAttribute("alertClass", "alert-success"); 
			  return "redirect:/localConvyence";
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		  return "redirect:/localConvyence";
	  }
	
	
	@GetMapping(value = { "/deleteLocalConveyance/{id}" })
	public String deleteLocalConveyance(@PathVariable("id") String id, Model model,HttpSession session,RedirectAttributes redirectAttributes) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}

		try {

			localConvyenceService.removelocalConvyence(id);
			redirectAttributes.addFlashAttribute("message", "Record  has been deleted successfully!");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");		
			session.setAttribute("username", session.getAttribute("username"));
			return "redirect:/localConvyence";
		} catch (Exception e) {
			e.printStackTrace();
		}
		redirectAttributes.addFlashAttribute("message", "Record  has been deleted successfully!");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");	
		return "redirect:/localConvyence";
	}
	
}
