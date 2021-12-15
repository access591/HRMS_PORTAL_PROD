package com.hrms.reports;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hrms.service.LeaveGrantRegisterService;

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
public class LeaveReport {

	private static final String JRXML = ".jrxml";
	private static final String JASPER = ".jasper";
	private static final String RESOURCES = "resources/";
	private static final String RESOURCES2 = "/resources/";
	private static final String CACHE_CONTROL_1 = "no-store";
	private static final String CACHE_CONTROL_2 = "private";
	private static final String PRAGMA = "no-store";
	private static final String CONTENT_TYPE = "application/pdf";
	
	@Autowired
	LeaveGrantRegisterService leaveGrantService;

//leave management report / leave request report

	public void leaveRequestReport(HttpServletResponse response, HttpServletRequest request, String reportFileName,
			List<?> listLeave, String empCode) throws IOException {

		String sourceFileName = request.getSession().getServletContext()
				.getRealPath(RESOURCES + reportFileName + JRXML);

		try {

			JasperCompileManager.compileReportToFile(sourceFileName);
			sourceFileName = request.getSession().getServletContext()
					.getRealPath(RESOURCES2 + reportFileName + JASPER);

			JRBeanCollectionDataSource leaveRequest = new JRBeanCollectionDataSource(listLeave);

			HashMap<String, Object> map = new HashMap<>();

			map.put("leaveRequest", leaveRequest);

			map.put("empName", empCode);
			map.put("deptName", "Software");
			map.put("desig", "Devloper");

			JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(sourceFileName);
			JasperPrint jasperPrint = JasperFillManager.fillReport(report, map, new JREmptyDataSource());

			if (jasperPrint != null) {
				byte[] pdfReport = JasperExportManager.exportReportToPdf(jasperPrint);
				response.reset();
				response.setContentType(CONTENT_TYPE);
				response.setHeader("Cache-Control", CACHE_CONTROL_1);
				response.setHeader("Cache-Control", CACHE_CONTROL_2);
				response.setHeader("Pragma", PRAGMA);

				response.setContentLength(pdfReport.length);

				response.getOutputStream().write(pdfReport);
				response.getOutputStream().flush();
				response.getOutputStream().close();

			}
		} catch (JRException e) {
			e.printStackTrace();
		}

		

	}

	// LEAVE TRASACTION PDF REPORT
	public void leaveTransactionPdfReportByEmp(HttpServletRequest request, HttpServletResponse response,
			List<?> sourceData, String activeUser) throws IOException {
		String reportFileName = "LeaveTransaction";
		System.out.println("leave transaction report...");
		String sourceFileName = request.getSession().getServletContext()
				.getRealPath(RESOURCES + reportFileName + JRXML);
		System.out.println("resources : = " + sourceFileName);

		try {

			JasperCompileManager.compileReportToFile(sourceFileName);
			sourceFileName = request.getSession().getServletContext()
					.getRealPath(RESOURCES2 + reportFileName + JASPER);
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(sourceData);

			HashMap<String, Object> map = new HashMap<>();

			map.put("Parameter", beanColDataSource);// Parameter
			map.put("activeUser", "Run By : " + activeUser);
			map.put("runDate", "Run Date : " + new Date());

			JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(sourceFileName);
			JasperPrint jasperPrint = JasperFillManager.fillReport(report, map, new JREmptyDataSource());

			if (jasperPrint != null) {
				byte[] pdfReport = JasperExportManager.exportReportToPdf(jasperPrint);
				response.reset();
				response.setContentType(CONTENT_TYPE);
				response.setHeader("Cache-Control", CACHE_CONTROL_1);
				response.setHeader("Cache-Control", CACHE_CONTROL_2);
				response.setHeader("Pragma", PRAGMA);
				response.setContentLength(pdfReport.length);

				response.getOutputStream().write(pdfReport);
				response.getOutputStream().flush();
				response.getOutputStream().close();

			}
		} catch (JRException e) {
			e.printStackTrace();
		}
		

	}

	// leave detail reports / leave register
	public void leaveRegisterReport(HttpServletRequest request, HttpServletResponse response, List<?> listLeveDetail)
			throws IOException {

	
		String reportFileName = "LeaveDetailManagement";
		String sourceFileName = request.getSession().getServletContext()
				.getRealPath(RESOURCES + reportFileName + JRXML);

		System.out.println("hii in leave register util" + sourceFileName);

		try {

			JasperCompileManager.compileReportToFile(sourceFileName);
			sourceFileName = request.getSession().getServletContext()
					.getRealPath(RESOURCES2 + reportFileName + JASPER);
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(listLeveDetail);

			HashMap<String, Object> map = new HashMap<>();

			map.put("ItemDataSource", beanColDataSource);
			System.out.println("hii leave detail reports");

			JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(sourceFileName);
			JasperPrint jasperPrint = JasperFillManager.fillReport(report, map, new JREmptyDataSource());

			if (jasperPrint != null) {
				byte[] pdfReport = JasperExportManager.exportReportToPdf(jasperPrint);
				response.reset();
				response.setContentType(CONTENT_TYPE);
				response.setHeader("Cache-Control", CACHE_CONTROL_1);
				response.setHeader("Cache-Control", CACHE_CONTROL_2);
				response.setHeader("Pragma", PRAGMA);
				response.setContentLength(pdfReport.length);

				response.getOutputStream().write(pdfReport);
				response.getOutputStream().flush();
				response.getOutputStream().close();

				System.out.println("exception occured");

			}
		} catch (JRException e) {
			e.printStackTrace();
			System.out.println("exception occured");
		}
		

	}

}
