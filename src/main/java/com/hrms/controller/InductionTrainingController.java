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
import com.hrms.model.InductionTraining;
import com.hrms.model.InductionTrainingDetail;
import com.hrms.model.MenuModule;
import com.hrms.service.DepartmentService;
import com.hrms.service.DesignationService;
import com.hrms.service.EmployeeService;
import com.hrms.service.InductionTrainingDetailService;
import com.hrms.service.InductionTrainingService;
import com.hrms.service.ModuleService;
import com.hrms.util.InductionTrainingUtil;

@Controller
public class InductionTrainingController {
	@Autowired
	private ModuleService moduleService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	DesignationService designationService;
	@Autowired
	DepartmentService departmentService;
	@Autowired
	InductionTrainingService inductionTrainingService;
	@Autowired
	InductionTrainingDetailService inductionTrainingDetailService;
	
	
	@InitBinder("editInductionTraining")
    public void customizeBinding (WebDataBinder binder) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        dateFormatter.setLenient(false);
        binder.registerCustomEditor(Date.class, "trainingDate",new CustomDateEditor(dateFormatter, true));
       
    }
	@InitBinder("inductionTraining")
    public void customizeBindi (WebDataBinder binder) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        dateFormatter.setLenient(false);
        binder.registerCustomEditor(Date.class, "trainingDate",new CustomDateEditor(dateFormatter, true));
       
    }
	
	
	
	@GetMapping("/inductionTraining")
	public String inductionTraining(Model model, HttpSession session) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		List<Department> listDepartment = departmentService.getAllDepartments();
		model.addAttribute("listDepartment", listDepartment);
		session.setAttribute("imgUtil", new ImageUtil());
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		List<InductionTraining>listInduction=inductionTrainingService.getAllInductioTraining();
		List<InductionTrainingUtil> listInductionTrainingUtil = new ArrayList<> ();
		
		  for (int i = 0; i < listInduction.size(); i++) {
		String empCode = listInduction.get(i).getEmpCode().getEmpCode(); 
		InductionTrainingUtil iuy= new InductionTrainingUtil();
		Employee employee = employeeService.findEmployeeById(empCode);
		Department department = departmentService.findDepartmentById(employee.getDepartment().getDepartmentCode());
		Designation designation = designationService.findDesignationById(employee.getDesignation().getDesgCode());
		
		iuy.setId(listInduction.get(i).getIdMast());
		 iuy.setDeptName(department.getDeptName());
		 iuy.setDesgName(designation.getDesgName());
		 iuy.setEmpName(employee.getEmpName());
		 listInductionTrainingUtil.add(iuy);
		    model.addAttribute("listInduct",listInductionTrainingUtil);
			  
		  }
		
		
		return "inductionTraining";

	}
	
	@ResponseBody
    @GetMapping("/viewInductionBydepartment/{id}")
    public List<InductionTrainingUtil>  getLocalConvyenceById(@PathVariable(value = "id") String id,Model model,HttpSession session) {
		Department d=departmentService.findDepartmentById(id);
		List<Employee> e = employeeService.findByDepartmentCode(d.getDepartmentCode());
		  List<InductionTrainingUtil> lisOvertimeRegisterUtil = new ArrayList<>();
		  for (int i = 0; i < e.size(); i++) 
		  {
			  String empCode = e.get(i).getEmpCode();
			  InductionTrainingUtil lc = new InductionTrainingUtil();
			  Employee employee = employeeService.findEmployeeById(empCode);
			  lc.setEmpCode(employee.getEmpCode());
			  lc.setEmpName(employee.getEmpName());
			  lisOvertimeRegisterUtil.add(lc);
		  }
		  return lisOvertimeRegisterUtil;
       
    }
	
	
	@ResponseBody
    @GetMapping("/viewinductionByEmployee/{id}")
    public InductionTrainingUtil  viewOverTimeRegisterByEmployee(@PathVariable(value = "id") String id,Model model,HttpSession session) {
		
		Employee employee = employeeService.findEmployeeById(id);
		String empCode = employee.getEmpCode();
		System.out.println(">>>>>>>>xxxxxx>>>>>>>>>>>>>>>>>>" + empCode);
		InductionTrainingUtil listEmp = new InductionTrainingUtil();
			  
			 
			
			 Department  dept=departmentService.findDepartmentById(employee.getDepartment().getDepartmentCode());
			 listEmp.setDepartmentCode(dept.getDepartmentCode());
			 listEmp.setDeptName(dept.getDeptName());
			  listEmp.setEmpCode(employee.getEmpCode());
			  listEmp.setEmpName(employee.getEmpName());
			  
		
		  return listEmp;
	}
	
	
	@PostMapping("/saveInductionTraining")
	public String saveInductionTraining(@ModelAttribute("inductionTraining")InductionTrainingUtil localCon, Model model, HttpSession session,HttpServletRequest request,RedirectAttributes redirectAttributes) throws ParseException {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		String insertedBY = (String) session.getAttribute("USER_NAME");
		InductionTraining induct=new InductionTraining();
		InductionTrainingDetail inductDetails=new InductionTrainingDetail();
		Department dept=new Department(); 
		Employee emp = new Employee();
		dept.setDepartmentCode(localCon.getDepartmentCode());
		emp.setEmpCode(localCon.getEmpCode());
		induct.setEmpCode(emp);
		induct.setDeptCode(dept);
		induct.setInsBy(insertedBY);
		inductionTrainingService.addInductionTraining(induct);
		   long inductid=induct.getIdMast();
		 int flag = 0;
	   		int counter = 1;
			try {
				
				
				boolean insertStatusMR = false;
				counter = Integer.parseInt(request.getParameter("_cr"));
				System.out.println("counter::::::::::::::::::::" + counter);
				
			
				for (int i =0; i < counter; i++) 
				{
					System.out.println("for loop run time>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
					if (request.getParameter("contPerson" + i) != null) {
						inductDetails.setContPerson(request.getParameter("contPerson" + i));
					} else {
						inductDetails.setContPerson("" + i);
					}

					if (request.getParameter("trainingDate" + i) != null) {

						String sDate1 = request.getParameter("trainingDate" + i);
						Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);

						inductDetails.setTrainingDate(date1);
					}

					if (request.getParameter("duration" + i) != null) {
						inductDetails.setDuration(request.getParameter("duration" + i));
					}

					else {
						inductDetails.setDuration("" + i);
					}
					System.out.println("inductid::::::::::::::::::::::::::::::::::::::::::"+inductid);
					induct.setIdMast(inductid);
					inductDetails.setIdMast(induct);
					inductDetails.setInsBy(insertedBY);  
					inductDetails.setDeptCode(dept);
					inductDetails.setEmpCode(emp);
					insertStatusMR = inductionTrainingDetailService.addInductionTrainingDetail(inductDetails);

					if (insertStatusMR) {
						System.out.println("Counter" + flag);
						flag++;

					}
					
				}
				
				
				if (flag > 0) {
					session.setAttribute("Message", "Data added successfully.");
					 redirectAttributes.addFlashAttribute("message", "Record  has been saved successfully!");
					  redirectAttributes.addFlashAttribute("alertClass", "alert-success");
					
				} else {
					System.out.println("Enter into  failure part :");
					
				}

			
			} catch (Exception e) {
				
				
				e.printStackTrace();
			}

		
		session.setAttribute("username", session.getAttribute("username"));

		return "redirect:/inductionTraining";
	}
	
	
	@GetMapping(value = {"/editInductionTraining/{id}"})
	public String editInductionTraining(@PathVariable("id")long id,  Model model,HttpSession session)
	 { 	if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
	 	List<Employee> listEmployee = employeeService.getAllEmployees();
		model.addAttribute("listEmployee", listEmployee);
	 	List<Department> listDepartment = departmentService.getAllDepartments();
		model.addAttribute("listDepartment", listDepartment);
		  session.setAttribute("imgUtil", new ImageUtil());
		  InductionTraining inductionTrainingEdit =	inductionTrainingService.findByIdInductionTraining(id);
		  model.addAttribute("inductionTrainingEdit", inductionTrainingEdit);

	   
	    return "editInductionTraining";
	}
	
	@PostMapping("/updateInductionTraining")
	  public String updateInductionTraining(@ModelAttribute("editInductionTraining") InductionTraining inductionTraining, Model model,HttpSession session,RedirectAttributes redirectAttributes) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		
		  try {
		
			this.inductionTrainingService.updateInductionTraining(inductionTraining);
			   redirectAttributes.addFlashAttribute("message", "Record  has been updated successfully! ");
			   redirectAttributes.addFlashAttribute("alertClass", "alert-success");  
			  return "redirect:/inductionTraining";
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		  return "redirect:/inductionTraining";
	  }
	
	
	
	@GetMapping(value = { "/deleteInductionTraining/{id}" })
	public String deleteLocalConveyance(@PathVariable("id") long id, Model model,HttpSession session,RedirectAttributes redirectAttributes) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		
		try {
			
			inductionTrainingService.removeInductionTr(id);
			session.setAttribute("username", session.getAttribute("username"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		redirectAttributes.addFlashAttribute("message", "Record  has been deleted successfully!");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return "redirect:/inductionTraining";
	}
	
}
