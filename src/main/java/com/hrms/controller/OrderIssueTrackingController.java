package com.hrms.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.repository.query.Param;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hrms.helper.Message1;
import com.hrms.model.Department;
import com.hrms.model.Employee;
import com.hrms.model.MenuModule;
import com.hrms.model.OrderIssueTracking;
import com.hrms.reports.OrderTrackingReport;
import com.hrms.service.DepartmentService;
import com.hrms.service.EmployeeService;
import com.hrms.service.ModuleService;
import com.hrms.service.OrderIssueTrackingService;

@Controller
public class OrderIssueTrackingController {

	@Autowired
	ModuleService moduleService;
	@Autowired
	OrderIssueTrackingService orderIssueTrackingService;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	DepartmentService departmentService;

	@InitBinder("orderIssueTracking")
	public void customizeBinding(WebDataBinder binder) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		dateFormatter.setLenient(false);

		binder.registerCustomEditor(Date.class, "orderTrackingDate", new CustomDateEditor(dateFormatter, true));

	}

	@GetMapping("orderissuetracking")
	public String orderIssueTrackingPage(@ModelAttribute("orderIssueTracking") OrderIssueTracking orderIssueTracking,
			Model model, HttpSession session) {

		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}

		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}

		List<Employee> employeeList = employeeService.getAllEmployees();
		if (employeeList != null) {
			model.addAttribute("employeeList", employeeList);
		}
		List<OrderIssueTracking> listOrderIssueTracking = orderIssueTrackingService.getAllOrderIssueTracking();
		if (listOrderIssueTracking != null) {
			model.addAttribute("listOrderIssueTracking", listOrderIssueTracking);
		}

		session.setAttribute("username", userCode);
		return "orderIssueTracking";

	}

	@PostMapping("saveOrderIssueTracking")
	public String saveOrderIssueTracking(@ModelAttribute("orderIssueTracking") OrderIssueTracking orderIssueTracking,
			Model model, HttpSession session, @RequestParam("orderFile") MultipartFile orderFile,
			RedirectAttributes redirectAttributes) throws IOException {

		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}

		if (orderFile.isEmpty()) {
			System.out.println("order file is empty");
			orderIssueTracking.setOrderFileName("");
		} else {

			orderIssueTracking.setOrderFileName(orderFile.getOriginalFilename());

			File saveFileFolder = new ClassPathResource("static/img/").getFile();// images

			Path path = Paths.get(saveFileFolder.getAbsolutePath() + File.separator + orderFile.getOriginalFilename());
			Files.copy(orderFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

		}
		try {

			orderIssueTrackingService.saveOrderIssueTracking(orderIssueTracking);
			
			redirectAttributes.addFlashAttribute("message", "Record  has been saved successfully!");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "Record  has been saved successfully! ");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		}

		return "redirect:orderissuetracking";
	}

	@GetMapping("editOrderTracking/{id}")
	public String editOrderIssueTracking(@PathVariable("id") String orderIssueTrackingId,
			@ModelAttribute("orderIssueTracking") OrderIssueTracking orderIssueTracking, Model model,
			HttpSession session) {

		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}

		List<Department> departmentList = departmentService.getAllDepartments();
		if (departmentList != null) {
			model.addAttribute("departmentList", departmentList);
		}

		if (orderIssueTrackingService.findOrderIssueTrackingById(Long.parseLong(orderIssueTrackingId)) != null) {
			model.addAttribute("orderIssueTracking",
					orderIssueTrackingService.findOrderIssueTrackingById(Long.parseLong(orderIssueTrackingId)));
		}
		List<Employee> employeeList = employeeService.getAllEmployees();
		if (employeeList != null) {
			model.addAttribute("employeeList", employeeList);
		}

		return "editOrderIssueTracking";
	}

	@PostMapping("updateOrderTracking")
	public String updateOrderIssueTracking(@ModelAttribute("orderIssueTracking") OrderIssueTracking orderIssueTracking,
			@RequestParam("orderFile") MultipartFile orderFile, HttpSession session,RedirectAttributes redirectAttributes) {

		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}

		try {
			orderIssueTracking.setOrderFileName(orderFile.getOriginalFilename());
			orderIssueTrackingService.updateOrderIssueTracking(orderIssueTracking);
			redirectAttributes.addFlashAttribute("message", "Record  has been updated successfully! ");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "Something went wrong !!  ");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");

		}

		return "redirect:orderissuetracking";
	}

	@GetMapping(value = { "deleteOrderTracking/{id}" })
	public String deleteOrderIssueTracking(@PathVariable("id") String orderTrackingId, Model model,
			HttpSession session,RedirectAttributes redirectAttributes) {
		System.out.println("=====================>");

		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}

		try {
			orderIssueTrackingService.removeOrderIssueTracking(Long.parseLong(orderTrackingId));
			redirectAttributes.addFlashAttribute("message", "Record  has been deleted successfully!");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "Something went wrong !!  ");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		}

		session.setAttribute("username", session.getAttribute("username"));
		return "redirect:/orderissuetracking";
	}

	@GetMapping("ordertrackreport")
	public String viewOrderTrackReport(Model model, HttpSession session) {

		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}

		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		List<Employee> employeeList = employeeService.getAllEmployees();
		if (employeeList != null) {
			model.addAttribute("employeeList", employeeList);
		}

		return "orderIssuedReport";
	}

	@Autowired
	OrderTrackingReport orderTrackReport;

	@PostMapping("createordertrackreport")
	public String createOrderTrackReport(@RequestParam("empCode") String empCode, HttpServletResponse response,
			HttpServletRequest request, HttpSession session) {

		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}

		List<OrderIssueTracking> orderTracking = new ArrayList<>();
		if (empCode.equals("ALL")) {
			orderTracking = orderIssueTrackingService.getAllOrderIssueTracking();
			System.out.println("budget list : ====>" + orderTracking.get(0).getOrderFileName());
			orderTrackReport.createOrderTrackReport(response, request, orderTracking, "All");
		} else {
			OrderIssueTracking order = orderIssueTrackingService.findOrderIssueTrackingByIssuedby(empCode);
			orderTracking.add(order);
			orderTrackReport.createOrderTrackReport(response, request, orderTracking, empCode);
		}

		return null;
	}

	@RequestMapping(value="download/{id}",method = RequestMethod.GET)
	public void doDownload(@PathVariable("id") Long orderId ,HttpServletRequest request, HttpServletResponse response) throws IOException {

		
		OrderIssueTracking order = orderIssueTrackingService.findOrderIssueTrackingById(orderId);
		
		ServletContext context = request.getServletContext();
		
		
		File saveFileFolder = new ClassPathResource("static/img/").getFile();// images

		Path filePath = Paths.get(saveFileFolder.getAbsolutePath() + File.separator + order.getOrderFileName());

	
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
