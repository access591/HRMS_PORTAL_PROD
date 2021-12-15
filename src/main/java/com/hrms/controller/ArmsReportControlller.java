package com.hrms.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hrms.model.ArmsLicenses;
import com.hrms.model.UserEntity;
import com.hrms.reports.ArmsReport;
import com.hrms.service.ArmsLicenseService;
import com.hrms.service.UserService;

@Controller
public class ArmsReportControlller {

	@Autowired
	ArmsReport armsReport;
	@Autowired
	ArmsLicenseService armsLicenseService;
	@Autowired
	UserService userService;

	@GetMapping("armsreport")
	public String armsLicensesReport(Model model, HttpServletResponse response, HttpServletRequest request)
			throws IOException {

		List<ArmsLicenses> armsLicenseList = armsLicenseService.allArmsLicenseDetails();

		if (model != null) {
			model.addAttribute("armsLicenseList", armsLicenseList);
		}

		return "armsReport";

	}

	@GetMapping("createarmsreport")
	public ResponseEntity<InputStreamResource> createArmsReport(@RequestParam("empCode") String empCode,
			@RequestParam("reportType") String reportType, HttpSession session, HttpServletResponse response,
			HttpServletRequest request) throws IOException {

		System.out.println("Arms report : " + empCode);
		System.out.println("Report type : " + reportType);
		

		String currentUser = (String) session.getAttribute("username");
		UserEntity user = userService.findUserById(currentUser);

		if (reportType.equals("P")) {
			
			
			
			
			System.out.println("Pdf file !!!");
			if (empCode.equals("ALL")) {
				System.out.println("if block ");
				List<ArmsLicenses> sourceData = armsLicenseService.allArmsLicenseDetails();

				System.out.println("source data : " + sourceData.size());
				armsReport.createArmsLicensesReport(response, request, sourceData, user.getUserName());

			} else {

				System.out.println("else block");
				List<ArmsLicenses> sourceData = new ArrayList<>();
				ArmsLicenses src = armsLicenseService.findArmsLicenseById(empCode);
				sourceData.add(src);
				armsReport.createArmsLicensesReport(response, request, sourceData, user.getUserName());
			}
			
			
			return null;
			
		} else {
			
			
			System.out.println("Excel file !!!");
			List<ArmsLicenses> sourceData = new ArrayList<>();
			if (empCode.equals("ALL")) {
				System.out.println("if block ");
				sourceData = armsLicenseService.allArmsLicenseDetails();

				System.out.println("source data : " + sourceData.size());

			} else {

				System.out.println("else block");
				//sourceData = new ArrayList<>();
				ArmsLicenses src = armsLicenseService.findArmsLicenseById(empCode);
				sourceData.add(src);

			}
			ByteArrayInputStream in = armsReport.createArmLicensesExcelReport(sourceData);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "attachment ; filename=EmployeeArmsLicenses.xlsx");

			return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
		}

		

	}

}
