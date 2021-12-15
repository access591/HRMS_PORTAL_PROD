package com.hrms.controller;

import java.io.IOException;
import java.sql.Date;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrms.ImageUtil;
import com.hrms.model.AttendenceRegister;
import com.hrms.model.Department;
import com.hrms.model.EmpMonOvertime;
import com.hrms.model.Employee;
import com.hrms.model.MenuModule;
import com.hrms.model.OvertimeRegister;
import com.hrms.reports.AbsentiReport;
import com.hrms.reports.AttendenceReport;
import com.hrms.service.AttendenceRegisterService;
import com.hrms.service.DepartmentService;
import com.hrms.service.EmpMontOvertimeRegister;
import com.hrms.service.EmployeeService;
import com.hrms.service.ModuleService;
import com.hrms.service.OvertimeRegisterService;

@Controller
public class AttandanceReportController {

	@Autowired
	DepartmentService departmentService;
	@Autowired
	private ModuleService moduleService;

	@Autowired
	EmployeeService employeeService;
	@Autowired
	AttendenceReport attendenceReport;
	@Autowired
	AttendenceRegisterService attendenceRegisterService;
	@Autowired
	OvertimeRegisterService overtimeRegisterService;
	@Autowired
	EmpMontOvertimeRegister empMontOvertimeRegister;
	@Autowired
	AbsentiReport absentiReport;

	@ModelAttribute
	public void commonData(Model model, HttpSession session) {

		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}

		List<Department> listDepartment = departmentService.getAllDepartments();
		if (listDepartment != null) {
			model.addAttribute("listDepartment", listDepartment);
		}
		session.setAttribute("username", userCode);

	}

// Attendance Register Mothly Report 

	@GetMapping("attendanceRegMothlyReport")
	public String attendanceRegisterMonthly(Model model, HttpSession session) {

		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}

		session.setAttribute("imgUtil", new ImageUtil());
		return "AttendanceRegMothlyReport";
	}

	// pending
	@PostMapping("createAttendenceMonthly")
	public String createAttendenceMonthly(@RequestParam("deptCode") String deptCode,
			@RequestParam("empCode") String empCode, @RequestParam("fromDate") Date fromDate,
			@RequestParam("toDate") Date toDate, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws IOException {

		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}

		System.out.println("department code is : " + deptCode);
		System.out.println("employee code is : " + empCode);

		// get record behalf of deptcode and empCode
		if (deptCode.equals("ALL")) {
			System.out.println("All record");
			List<AttendenceRegister> listAttendenceRegister = attendenceRegisterService
					.findAllAttendenceBetweenDate(fromDate, toDate);
			attendenceReport.attendenceMontlyReport(response, request, listAttendenceRegister, fromDate, toDate,
					empCode, deptCode);

		} else if (!deptCode.equals("ALL")) {

			if (empCode.equals("") || empCode == null) {
				System.out.println("find data by department ");
				List<AttendenceRegister> listAttendenceRegister = attendenceRegisterService
						.findAttendenceByDeptBetweenDate(deptCode, fromDate, toDate);
				attendenceReport.attendenceMontlyReport(response, request, listAttendenceRegister, fromDate, toDate,
						empCode, deptCode);
			} else {
				System.out.println("find data by emp ");
				List<AttendenceRegister> listAttendenceRegister = attendenceRegisterService
						.findAttendenceByEmpCodeBetweenDate(empCode, fromDate, toDate);
				attendenceReport.attendenceMontlyReport(response, request, listAttendenceRegister, fromDate, toDate,
						empCode, deptCode);
			}
		}

		else {
			return "redirect:AttendanceRegMothlyReport";
		}

		return "null";
	}

	// Attendance Register Date wise Report

	@GetMapping("attendanceRegDateReport")
	public String attendanceRegisterDate(Model model, HttpSession session) {

		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}

		return "AttendanceRegDayReport";
	}

	@PostMapping("createAttendenceDatewise")
	public String createAttendenceRegDateWiseReport(@RequestParam("empCode") String empCode,
			@RequestParam("deptCode") String deptCode, @RequestParam("fromDate") Date fromDate,
			@RequestParam("toDate") Date toDate, HttpServletResponse response, HttpServletRequest request,
			HttpSession session) throws IOException {

		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}

		System.out.println("empCode : " + empCode);
		System.out.println("from date : " + fromDate);

		if (deptCode.equals("") && empCode.equals("")) {
			System.out.println("All record : ");

			List<AttendenceRegister> listAttendenceRegister = attendenceRegisterService
					.findAllAttendenceBetweenDate(fromDate, toDate);
			attendenceReport.createAttendenceReportDatewise(response, request, listAttendenceRegister, fromDate,
					toDate);
		}

		else if (!deptCode.equals("") && empCode.equals("")) {
			System.out.println("all record by department");
			List<AttendenceRegister> listAttendenceRegister = attendenceRegisterService
					.findAttendenceByDeptBetweenDate(deptCode, fromDate, toDate);
			attendenceReport.createAttendenceReportDatewise(response, request, listAttendenceRegister, fromDate,
					toDate);

		} else {
			System.out.println("find by employee");
			List<AttendenceRegister> listAttendenceRegister = attendenceRegisterService
					.findAttendenceByEmpCodeBetweenDate(empCode, fromDate, toDate);
			attendenceReport.createAttendenceReportDatewise(response, request, listAttendenceRegister, fromDate,
					toDate);
		}

		return null;
	}

	// Overtime Register Mothly Report

	@GetMapping("overtimeRegMonthlyReport")
	public String overtimeRegisterMonthlyPage(Model model, HttpSession session) {

		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}

		return "OvertimeRegMothlyReport";
	}

	@PostMapping("createOvertimeMonthly")
	public String createOvertimeMonthlyReport(@RequestParam("month") String month,
			@RequestParam("deptCode") String deptCode, Model model, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}

		System.out.println("mont : " + month);
		System.out.println("department code : " + deptCode);

		if (month.equals("0") && deptCode.equals("ALL")) {
			System.out.println("all rcord ");
			List<EmpMonOvertime> listEmpMontOvertime = empMontOvertimeRegister.findAllEmpMontOvertime();
			attendenceReport.createOvertimeMonthlyReport(response, request, listEmpMontOvertime);

		} else if (month.equals("0") && !deptCode.equals("ALL")) {

			System.out.println("get all record by dept code");
			List<EmpMonOvertime> listEmpMontOvertime = empMontOvertimeRegister.findAllByDeptCode(deptCode);
			attendenceReport.createOvertimeMonthlyReport(response, request, listEmpMontOvertime);
		} else if (!month.equals("0") && deptCode.equals("ALL")) {
			System.out.println("get all department record by month");
			List<EmpMonOvertime> listEmpMontOvertime = empMontOvertimeRegister
					.findAllDeptByMonth(Integer.parseInt(month));
			attendenceReport.createOvertimeMonthlyReport(response, request, listEmpMontOvertime);
		} else {
			System.out.println("get record by dept and month wise");
			List<EmpMonOvertime> listEmpMontOvertime = empMontOvertimeRegister
					.findOvertimeMonthByDeptCodeAndMonth(deptCode, Integer.parseInt(month));
			attendenceReport.createOvertimeMonthlyReport(response, request, listEmpMontOvertime);
		}

		return "";
	}

	// Overtime Register Date wise Report

	@GetMapping("overtimeRegDayReport")
	public String overtimeRegisterDatewise(Model model, HttpSession session) {

		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}

		return "OvertimeRegDateReport";
	}

	@PostMapping("createOvertimeRegDate")
	public String createOvertimeRegDatewiseReport(@RequestParam("empCode") String empCode,
			@RequestParam("deptCode") String deptCode, @RequestParam("fromDate") Date fromDate,
			@RequestParam("toDate") Date toDate, HttpServletResponse response, HttpServletRequest request
			) throws IOException {

		
		System.out.println("from date : " + fromDate);
		System.out.println("to date : " + toDate);

		if (deptCode.equals("") && empCode.equals("")) {
			System.out.println("all record");
			
		} else if (!deptCode.equals("") && empCode.equals("")) {
			System.out.println("all record by department");
			return "redirect:/overtimeRegDayReport";
		} else {
			System.out.println("get record by empCode");
			List<OvertimeRegister> listOvertimeRegister = overtimeRegisterService
					.findOverTimeRegisterByEmpCodeBetweenDate(empCode, fromDate, toDate);
			attendenceReport.createOvertimeRegDatewiseReport(response, request, listOvertimeRegister);
		}

		return "redirect:/overtimeRegDayReport";
	}

	// ABSENTISM EMPLOYEE WISE REPORT CONTROLLER

	@GetMapping("absentismEmployeePage")
	public String absentismEmployeeWiseReportPage(Model model, HttpSession session) {

		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}

		return "absentismEmployee";
	}

	@PostMapping("createAbsentEmployeeReport")
	public String createAbsentEmployeeWiseReport(@RequestParam("deptCode") String deptCode, HttpServletRequest request,
			HttpServletResponse response, @RequestParam("empCode") String empCode,
			@RequestParam("fromDate") Date fromDate, @RequestParam("toDate") Date toDate, HttpSession session)
			throws IOException {

		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}

		System.out.println("employee code : " + empCode);
		System.out.println("department code : " + deptCode);
		System.out.println("from date : " + fromDate);
		System.out.println("to date : " + toDate);

		if (deptCode != null && empCode.equals("ALL")) {
			System.out.println("all employee record by department");
			List<AttendenceRegister> listAttendenceRegister = attendenceRegisterService
					.findAttendenceStatusByDeptCode(deptCode, fromDate, toDate);
			absentiReport.createAbsentiReport(response, request, listAttendenceRegister, fromDate, toDate, deptCode);
		} else if (deptCode != null && !empCode.equals("ALL")) {
			System.out.println("single record by emp ");
			List<AttendenceRegister> listAttendenceRegister = attendenceRegisterService
					.findAttendenceByEmpStatusAbsent(empCode);
			absentiReport.createAbsentiReport(response, request, listAttendenceRegister, fromDate, toDate, deptCode);

		}

		return "redirect:/absentismEmployeePage";
	}

	@ResponseBody
	@GetMapping("getEmployeeByDeptCode/{deptCode}")
	public List<Employee> getEmployeeByDeptCode(@PathVariable("deptCode") String deptCode) {

		System.out.println("dept code : " + deptCode);
		try {

			List<Employee> list = employeeService.findByDepartmentCode(deptCode);
			System.out.println("list size : " + list.size());
			return employeeService.findByDepartmentCode(deptCode);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
