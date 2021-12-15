package com.hrms.reports;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hrms.model.ArmsLicenses;
import com.hrms.service.ArmsLicenseService;

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
public class ArmsReport {

	@Autowired
	ArmsLicenseService armsLicenseService;

	public void createArmsLicensesReport(HttpServletResponse response, HttpServletRequest request,
			List<ArmsLicenses> sourceData, String runBy) throws IOException {

		String reportFileName = "ArmsLicenses"; // Parameter1
		String sourceFileName = request.getSession().getServletContext()
				.getRealPath("resources/" + reportFileName + ".jrxml");

		System.out.println("arms licenses report : ");
		try {

			JasperCompileManager.compileReportToFile(sourceFileName);
			sourceFileName = request.getSession().getServletContext()
					.getRealPath("/resources/" + reportFileName + ".jasper");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(sourceData);

			Map<String, Object> parameters = new HashMap<>();

			parameters.put("Parameter1", beanColDataSource);
			parameters.put("runBy", "Run By : " + runBy);
			parameters.put("runDate", "Run Date : " + new Date().toString());

			JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(sourceFileName);
			JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());

			if (jasperPrint != null) {
				byte[] pdfReport = JasperExportManager.exportReportToPdf(jasperPrint);
				response.reset();
				response.setContentType("application/pdf");
				response.setHeader("Cache-Control", "no-store");
				response.setHeader("Cache-Control", "private");
				response.setHeader("Pragma", "no-store");
				response.setContentLength(pdfReport.length);

				response.getOutputStream().write(pdfReport);
				response.getOutputStream().flush();
				response.getOutputStream().close();

			}
		} catch (JRException e) {
			e.printStackTrace();
		}

	}

	public void armsReportDataSource(String empCode, HttpServletResponse response, HttpServletRequest request)
			throws IOException {
		List<ArmsLicenses> arms = new ArrayList<>();
		ArmsLicenses armsLicenseDetail = armsLicenseService.findArmsByEmpEmpCode(empCode);
		if (armsLicenseDetail != null)
			arms.add(armsLicenseDetail);
		// createArmsLicensesReport(response, request, arms);
	}

	public ByteArrayInputStream createArmLicensesExcelReport(List<ArmsLicenses> sourceData) {
		
		String[] columns = {"No.","Name Of Person","Father Name","Address","District","State","Arm Area",
				"Date Of Issue","Date Of Valid","Type Of Arms","Type Of Position","Number Of Licenses",
				"Licenses Detail"};
		
		try {
			Workbook workBook = new XSSFWorkbook();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			Sheet sheet = workBook.createSheet("Employee Arms Licenses");
			
			Font headerFont = workBook.createFont();
			headerFont.setBold(true);
			headerFont.setColor(IndexedColors.BLUE.getIndex());
			
			CellStyle headerCellStyle = workBook.createCellStyle();
			headerCellStyle.setFont(headerFont);
			
			Row headerRow = sheet.createRow(0);
			
			for(int col=0;col<columns.length;col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(columns[col]);
				cell.setCellStyle(headerCellStyle);
				
			}
			
			int rowIndex = 1;
			
			for(ArmsLicenses arms : sourceData) {
				
				System.out.println("date testingg====>"+ arms.getDoi());
				System.out.println("date testingg====>"+ arms.getDov());
				Row row = sheet.createRow(rowIndex++);
				row.createCell(0).setCellValue(rowIndex);
				row.createCell(1).setCellValue(arms.getName());
				row.createCell(2).setCellValue(arms.getFatherName());
				row.createCell(3).setCellValue(arms.getAddressArms());
				row.createCell(4).setCellValue(arms.getCity3());
				//row.createCell(5).setCellValue(arms.getState());
				row.createCell(6).setCellValue(arms.getArmsArea());
				row.createCell(7).setCellValue(arms.getDoi().toString().substring(0, 11));
				row.createCell(8).setCellValue(arms.getDov().toString().substring(0,11));
				row.createCell(9).setCellValue(arms.getToa());
				row.createCell(10).setCellValue(arms.getTop());
				row.createCell(11).setCellValue(arms.getArmsNol());
				
				//row.createCell(12).setCellValue(arms.getDealerDetails());
				
			}
			workBook.write(out);
			workBook.close();
			return new ByteArrayInputStream(out.toByteArray());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

}
