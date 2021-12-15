package com.hrms.reports;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hrms.model.AttendenceRegister;
import com.hrms.model.EmpMonOvertime;
import com.hrms.service.AttendenceRegisterService;
import com.hrms.util.OvertimeMontReportUtil;

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
public class AttendenceReport {

	private static final String JRXML = ".jrxml";
	private static final String JASPER = ".jasper";
	private static final String RESOURCES = "resources/";
	private static final String RESOURCES2 = "/resources/";
	private static final String CACHE_CONTROL_1 = "no-store";
	private static final String CACHE_CONTROL_2 = "private";
	private static final String PRAGMA = "no-store";
	private static final String CONTENT_TYPE = "application/pdf";

	public void attendenceMontlyReport(HttpServletResponse response, HttpServletRequest request, List<?> sourceData,
			Date fromDate, Date toDate, String empCode, String deptCode) throws IOException {

		String reportFileName = "AttendanceRegMonthly"; // Parameter1

		String deptName1 = "Department Name : " + deptCode;
		String date = "From: " + fromDate + " To: " + toDate;
		String sourceFileName = request.getSession().getServletContext()
				.getRealPath(RESOURCES + reportFileName + JRXML);

		try {

			JasperCompileManager.compileReportToFile(sourceFileName);
			sourceFileName = request.getSession().getServletContext().getRealPath(RESOURCES2 + reportFileName + JASPER);
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(sourceData);

			HashMap<String, Object> map = new HashMap<>();

			map.put("Parameter1", beanColDataSource);

			map.put("deptName", deptName1);
			map.put("date", date);
			map.put("empCode", empCode);

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

	public void createAttendenceReportDatewise(HttpServletResponse response, HttpServletRequest request,
			List<?> sourceData, Date fromDate, Date toDate) throws IOException {

		String reportFileName = "AttendenceReportDatewise"; // Parameter1

		String mainYear = "2020-21";
		String totalDate = "From :" + fromDate + " To :" + toDate;

		String sourceFileName = request.getSession().getServletContext()
				.getRealPath(RESOURCES + reportFileName + JRXML);

		try {

			JasperCompileManager.compileReportToFile(sourceFileName);
			sourceFileName = request.getSession().getServletContext().getRealPath(RESOURCES2 + reportFileName + JASPER);
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(sourceData);

			Map<String, Object> parameters = new HashMap<>();

			parameters.put("Parameter1", beanColDataSource);

			parameters.put("mainYear", mainYear);
			parameters.put("totalDate", totalDate);

			JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(sourceFileName);
			JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());

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

	public void createOvertimeRegDatewiseReport(HttpServletResponse response, HttpServletRequest request,
			List<?> sourceData) throws IOException {

		String reportFileName = "OvertimeDateWise"; // Parameter1

		String sourceFileName = request.getSession().getServletContext()
				.getRealPath(RESOURCES + reportFileName + JRXML);

		try {

			JasperCompileManager.compileReportToFile(sourceFileName);
			sourceFileName = request.getSession().getServletContext().getRealPath(RESOURCES2 + reportFileName + JASPER);
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(sourceData);

			Map<String, Object> parameters = new HashMap<>();

			parameters.put("Parameter1", beanColDataSource);

			JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(sourceFileName);
			JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());

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

	public void createOvertimeMonthlyReport(HttpServletResponse response, HttpServletRequest request,
			List<EmpMonOvertime> sourceData) throws IOException {

		String reportFileName = "OvertimeMonthly"; // Parameter1

		List<OvertimeMontReportUtil> listData = setValue(sourceData);

		String sourceFileName = request.getSession().getServletContext()
				.getRealPath(RESOURCES + reportFileName + JRXML);

		try {

			JasperCompileManager.compileReportToFile(sourceFileName);
			sourceFileName = request.getSession().getServletContext().getRealPath(RESOURCES2 + reportFileName + JASPER);
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(listData);

			HashMap<String, Object> map = new HashMap<>();

			map.put("Parameter1", beanColDataSource);

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

	// set value for overtime mont repo
	@Autowired
	AttendenceRegisterService attendenceRegisterService;

	public List<OvertimeMontReportUtil> setValue(List<EmpMonOvertime> sourceData) {

		List<OvertimeMontReportUtil> listOvertimeMontReportUtil = new ArrayList<>();

		for (int i = 0; i < sourceData.size(); i++) {

			OvertimeMontReportUtil over = new OvertimeMontReportUtil();
			over.setSrNo(String.valueOf(i));
			over.setoTimeMonth(sourceData.get(i).getoTimeMonth());
			over.setEmpName(sourceData.get(i).getEmployee().getEmpName());
			over.setDateOfJoining(sourceData.get(i).getEmployee().getDoj());

			try {
				AttendenceRegister attendence = attendenceRegisterService
						.findAttendenceRegisterByEmpCode(sourceData.get(i).getEmployee().getEmpCode());
				over.setDeptName(attendence.getDepartment().getDeptName());

				over.setOverFlowHrs(attendence.getOverFlowHrs());
			} catch (Exception e) {
				e.printStackTrace();
			}
			listOvertimeMontReportUtil.add(over);

		}
		return listOvertimeMontReportUtil;

	}

}
