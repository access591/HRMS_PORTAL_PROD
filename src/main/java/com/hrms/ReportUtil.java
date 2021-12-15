package com.hrms;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hrms.model.CommonUtil;
import com.hrms.service.EmployeeService;
import com.hrms.service.LeaveDetailService;
import com.hrms.service.LeaveGrantRegisterService;
import com.hrms.service.LeaveRequestService;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@Component
public class ReportUtil {

	@Autowired
	LeaveRequestService leaveRequestService;
	
	@Autowired EmployeeService employeeService;
	@Autowired LeaveGrantRegisterService leaveGrantService;
 
	
	
	
	
	
	@Autowired
	LeaveDetailService leaveDetailService;
	
	
	private String sourceFileName = null;
	
	

	// Employee gradation information pdf  report

	public void allEmployeeReport(HttpServletRequest request, HttpServletResponse response, String reportFileName,
			List<?> sourceData) {
		
		sourceFileName = request.getSession().getServletContext()
				.getRealPath("resources/" + reportFileName + ".jrxml");
		

		try {

			JasperCompileManager.compileReportToFile(sourceFileName);
			sourceFileName = request.getSession().getServletContext()
					.getRealPath("/resources/" + reportFileName + ".jasper");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(sourceData);

			HashMap<String, Object> map = new HashMap<>();

			map.put("ItemDataSource", beanColDataSource);

			JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(sourceFileName);
			JasperPrint jasperPrint = JasperFillManager.fillReport(report, map, new JREmptyDataSource());

			if (jasperPrint != null) {
				byte[] pdfReport = JasperExportManager.exportReportToPdf(jasperPrint);
				response.reset();
				response.setContentType("application/pdf");
				response.setHeader("Cache-Control", "no-store");
				response.setHeader("Cache-Control", "private");
				response.setHeader("Pragma", "no-store");
				response.setContentLength(pdfReport.length);
				try {
					response.getOutputStream().write(pdfReport);
					response.getOutputStream().flush();
					response.getOutputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		} catch (JRException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	//	BIRT AND ANNIVERASARY REPORT
	public void birthAnniversaryReport(HttpServletRequest request, HttpServletResponse response, String reportFileName,
			List<CommonUtil> sourceData) {
		
		sourceFileName = request.getSession().getServletContext()
				.getRealPath("resources/" + reportFileName + ".jrxml");
		
		try {

			JasperCompileManager.compileReportToFile(sourceFileName);
			sourceFileName = request.getSession().getServletContext()
					.getRealPath("/resources/" + reportFileName + ".jasper");
			
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(sourceData);

			HashMap<String, Object> map = new HashMap<>();

			map.put("Parameter1", beanColDataSource);

			JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(sourceFileName);
			JasperPrint jasperPrint = JasperFillManager.fillReport(report, map, new JREmptyDataSource());

			if (jasperPrint != null) {
				byte[] pdfReport = JasperExportManager.exportReportToPdf(jasperPrint);
				response.reset();
				response.setContentType("application/pdf");
				response.setHeader("Cache-Control", "no-store");
				response.setHeader("Cache-Control", "private");
				response.setHeader("Pragma", "no-store");
				response.setContentLength(pdfReport.length);
				try {
					response.getOutputStream().write(pdfReport);
					response.getOutputStream().flush();
					response.getOutputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		} catch (JRException e) {
			e.printStackTrace();
		}
		

	}
	
	
	
	
	
//EMPLOYEE JOINING LETTTER
	
	public void employeeJoiningLetter(HttpServletRequest request, HttpServletResponse response, String reportFileName,
			List<CommonUtil> sourceData) {
		
		sourceFileName = request.getSession().getServletContext()
				.getRealPath("resources/" + reportFileName + ".jrxml");
		

		try {

			JasperReport jasperReport = JasperCompileManager.compileReport(sourceFileName);
			JRDataSource dataSource = new JREmptyDataSource();
			Map<String,Object> parameter = new HashMap<>();
			parameter.put("name",sourceData.get(0).getEmpName());
			parameter.put("dept", sourceData.get(0).getDeptName());
			parameter.put("desig", sourceData.get(0).getDesgName());
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter,dataSource);
			JasperExportManager.exportReportToPdfFile(jasperPrint,sourceFileName);

			if (jasperPrint != null) {
				byte[] pdfReport = JasperExportManager.exportReportToPdf(jasperPrint);
				response.reset();
				response.setContentType("application/pdf");
				response.setHeader("Cache-Control", "no-store");
				response.setHeader("Cache-Control", "private");
				response.setHeader("Pragma", "no-store");
				response.setContentLength(pdfReport.length);
				try {
					response.getOutputStream().write(pdfReport);
					response.getOutputStream().flush();
					response.getOutputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		} catch (JRException e) {
			e.printStackTrace();
		}
		

	}
	
	
	
	
	
	
		
		
		
	

}
