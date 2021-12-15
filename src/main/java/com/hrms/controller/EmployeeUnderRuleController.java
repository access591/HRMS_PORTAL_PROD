package com.hrms.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.util.StringUtils;

import com.hrms.helper.FileService;
import com.hrms.model.Department;
import com.hrms.model.Employee;
import com.hrms.model.EmployeeUnderRule;
import com.hrms.model.MenuModule;
import com.hrms.model.OrderIssueTracking;
import com.hrms.service.DepartmentService;
import com.hrms.service.EmployeeService;
import com.hrms.service.EmployeeUnderRuleService;
import com.hrms.service.ModuleService;
import com.hrms.util.EmployeeUnderRuleUtil;

@Controller
public class EmployeeUnderRuleController {

	@Autowired
	DepartmentService departmentService;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	EmployeeUnderRuleService employeeUnderRuleService;
	@Autowired
	ModuleService moduleService;

	@InitBinder("employeeUnderRuleUtil")
	public void customizeBinding(WebDataBinder binder) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		dateFormatter.setLenient(false);
		binder.registerCustomEditor(Date.class, "memoDate", new CustomDateEditor(dateFormatter, true));

	}

	@GetMapping("employeeunderrule")
	public String EmployeeUnderRulePage(
			@ModelAttribute("employeeUnderRuleUtil") EmployeeUnderRuleUtil employeeUnderRuleUtil, Model model,
			HttpSession session) {

		String userCode = (String) session.getAttribute("username");
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}

		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}

		try {
			List<Department> listDepartment = departmentService.getAllDepartments();
			if (listDepartment != null) {
				model.addAttribute("departmentList", listDepartment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<EmployeeUnderRule> listEmployeeUnderRule = employeeUnderRuleService.getUniqueEmployeeUnderRule();
		if (listEmployeeUnderRule != null) {
			model.addAttribute("listEmployeeUnderRule", listEmployeeUnderRule);
		}
		return "EmployeeUnderRule";
	}

	@PostMapping("saveemployeeunderRule")
	public String saveEmployeeUnderRule(
			@ModelAttribute("employeeUnderRuleUtil") EmployeeUnderRuleUtil employeeUnderRuleUtil, Model model,
			HttpSession session, @RequestParam("empCode") String empCode,
			@RequestParam("underRuleType") String underRuleType, @RequestParam("underRuleFile") MultipartFile[] files,RedirectAttributes redirectAttributes)
			throws IOException {

		List<EmployeeUnderRule> listEmployeeUnderRule = employeeUnderRuleUtil.getEmployeeUnderRule();

		File saveFileFolder = new ClassPathResource("static/img/").getFile();

		if (listEmployeeUnderRule != null && listEmployeeUnderRule.size() > 0) {
			Employee employee = null;
			try {
				employee = employeeService.findEmployeeById(empCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			int count = 0;
			for (EmployeeUnderRule emp : listEmployeeUnderRule) {

				String fileName = StringUtils.cleanPath(files[count].getOriginalFilename());

				if (employee != null) {
					emp.setEmployee(employee);
				}

				emp.setInsBy((String) session.getAttribute("USER_NAME"));
				emp.setUnderRuleType(underRuleType);
				
				EmployeeUnderRule employeeUnderRule = employeeUnderRuleService.addEmployeeUnderRule(emp);
	
				fileName = employeeUnderRule.getEod() + "-" + employeeUnderRule.getEmployee().getEmpCode() + "-"
						+ employeeUnderRule.getMemoNo() + "-"
						+ employeeUnderRule.getMemoDate().toString().trim().substring(0, 9)+"."
						+ files[count].getOriginalFilename()
								.substring(files[count].getOriginalFilename().lastIndexOf(".") + 1);

				System.out.println("file name : =====>" + fileName);

				String uploadDir = saveFileFolder.getAbsolutePath();

				System.out.println("upload dir : =====>" + uploadDir);

				FileService.saveFile(uploadDir, fileName, files[count]);
				count++;
				employeeUnderRule.setUnderRuleFile(fileName);
				employeeUnderRuleService.updateEmployeeUnderRule(employeeUnderRule);

			}
		}
		redirectAttributes.addFlashAttribute("message", "Record  has been saved successfully!");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		
		return "redirect:employeeunderrule";
	}

	@GetMapping("editemployeeUnderrule/{empCode}/{empType}")
	public String editEmployeeUnderRule(@PathVariable("empCode") String empCode,@PathVariable("empType") String empType,
			 Model model) {

		System.out.println("under rul detail ==>"+empCode +":===>: "+empType);;
		try {
			List<Department> listDepartment = departmentService.getAllDepartments();
			if (listDepartment != null) {
				model.addAttribute("departmentList", listDepartment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

//		List<EmployeeUnderRule> listEmployeeUnderRule = employeeUnderRuleService.getAllEmployeeUnderRule();
//		if(listEmployeeUnderRule != null) {
//			model.addAttribute("listEmployeeUnderRule", listEmployeeUnderRule);
//		}

//		EmployeeUnderRuleUtil employeeUnderRuleUtil = new EmployeeUnderRuleUtil();

		List<EmployeeUnderRule> employeeUnderRule = employeeUnderRuleService.findEmployeeUnderRuleByEmpCodeAndEmpType(empCode, empType);

		try {
			Employee employee = employeeService.findEmployeeById(empCode);
			String deptCode = employee.getDepartment().getDepartmentCode();
			Department department = departmentService.findDepartmentById(deptCode);

			System.out.println("department is +======>"+department.getDeptName());
			model.addAttribute("department", department.getDeptName());
			
			List<Employee> employeeList = employeeService.findByDepartmentCode(deptCode);
			if(employeeList != null) {
				model.addAttribute("employeeList", employeeList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
//		employeeUnderRuleUtil.setEmployeeUnderRule(Arrays.asList(employeeUnderRule));
		model.addAttribute("employeeUnderRule", employeeUnderRule);

		return "editEmployeeUnderRule";
	}

	@GetMapping("deleteemployeeunderRule/{id}")
	public String deleteEmployeeUnderRule(@PathVariable("id") Long eod,RedirectAttributes redirectAttributes) {

		try {
			
			redirectAttributes.addFlashAttribute("message", "Record  has been deleted successfully!");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		}catch(Exception e) {
			e.printStackTrace();
			
			redirectAttributes.addFlashAttribute("message", "Something went wrong !!  ");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		}
		employeeUnderRuleService.deleteEmployeeUnderRule(eod);
		return "redirect:/employeeunderrule";
	}
	
	
	@PostMapping("updateemployeeunderrule")
	public String updateEmployeeUnderRule(@ModelAttribute("employeeUnderRule") EmployeeUnderRule employeeUnderRule,
			Model model,@RequestParam("eod") Long eod,
			HttpSession session,
			@RequestParam("file") MultipartFile files) throws IOException {
		
		
		
		if(!files.isEmpty()) {
			
			System.out.println("employee eod : ======>"+ eod);
			
			
			EmployeeUnderRule empl = employeeUnderRuleService.getEmployeeUnderRuleFindById(eod);
			String fileName = empl.getUnderRuleFile();
			
			File file = new File(fileName);
			File saveFileFolder = new ClassPathResource("static/img/").getFile();
			
			System.out.println("file path : "+ saveFileFolder+file.getName());
			System.out.println("========start========");
			
			String f = new File(saveFileFolder.toString()).getAbsolutePath() 
					+ File.separator + fileName;
			File file1 = new File(f);
			if(file1.exists()) {
				
				System.out.println("==========>file exists");
				if(file1.delete()) {
					
					System.out.println("if block ");
					
					String newFile = empl.getEod() + "-" + employeeUnderRule.getEmployee().getEmpCode() + "-"
							+ employeeUnderRule.getMemoNo() + "-"
							+ employeeUnderRule.getMemoDate().toString().trim().substring(0, 9)+"."
							+ files.getOriginalFilename()
									.substring(files.getOriginalFilename().lastIndexOf(".") + 1);
					
					

					String uploadDir = saveFileFolder.getAbsolutePath();

					System.out.println("upload dir : =====>" + uploadDir);

					FileService.saveFile(uploadDir, fileName, files);
					
					employeeUnderRule.setUnderRuleFile(newFile);
					//employeeUnderRule.setUnderRuleType(underRuleType);
					//employeeUnderRule.setEmployee(employee);
					
					employeeUnderRule.setUpdBy((String) session.getAttribute("USER_NAME"));
					employeeUnderRuleService.updateEmployeeUnderRule(employeeUnderRule);
					
				}else {
					System.out.println("else block");
				}
			}
		}else {
			System.out.println("else block");
		}
		//employeeUnderRuleService.updateEmployeeUnderRule(employeeUnderRule);
		return "redirect:/employeeunderrule";
	}
	
	
	
	@RequestMapping(value="download1/{id}",method = RequestMethod.GET)
	public void doDownload(@PathVariable("id") Long eod ,HttpServletRequest request, HttpServletResponse response) throws IOException {

		
		EmployeeUnderRule empl = employeeUnderRuleService.getEmployeeUnderRuleFindById(eod);
		
		ServletContext context = request.getServletContext();
		
		
		File saveFileFolder = new ClassPathResource("static/img/").getFile();// images

		Path filePath = Paths.get(saveFileFolder.getAbsolutePath() + File.separator + empl.getUnderRuleFile());

	
		String fullPath = filePath.toString();
		System.out.println("fullpath is : "+ fullPath);
		File downloadFile = new File(fullPath);
		FileInputStream inputStream = new FileInputStream(downloadFile);

		
		String mimeType = context.getMimeType(fullPath);
		if (mimeType == null) {
			
			mimeType = "application/octet-stream";
		}
		System.out.println("MIME type: " + mimeType);

		
		response.setContentType(mimeType);
		response.setContentLength((int) downloadFile.length());

		
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
		response.setHeader(headerKey, headerValue);

		
		OutputStream outStream = response.getOutputStream();

		byte[] buffer = new byte[1600];
		int bytesRead = -1;

		
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}

		inputStream.close();
		outStream.close();

	}

}
