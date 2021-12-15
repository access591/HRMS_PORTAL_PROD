package com.hrms.reports;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.hrms.model.ApplicantInfo;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Component
public class EmployeeOfferLetter {

	public void employeeOfferLetter(HttpServletRequest request, HttpServletResponse response, String reportFileName,
			ApplicantInfo applicantInfo) throws IOException {

		String offerLetter = "Sequel to your Application and Interview held on " + applicantInfo.getApplicantDate()
				+ ",We are pleased to offer you " + "the position of" + applicantInfo.getDesigCode().getDesgName()
				+ " in our organization your appointment will be w.e.f.'\n\n'"
				+ "Your consolidated Salary as agreed is Rs - Per Month (In Words) from the date of your joining. You will be"
				+ " on probation for a period of 90 days from the date of your appointment where after ,post completion of 90 days your service "
				+ " with the organization stands confirmed '\n\n'"
				+ "you are advised to join on MonthDate 2021 after instance of this Offer Letter along with copies of "
				+ " all your testmonials/Identity proof/ and photographs.";

		String name = applicantInfo.getApplicantName();
		String role = "Manager Director";
		String To = "Dear Mr/Ms " + applicantInfo.getApplicantName();
		String from = "Mr. Vikash Goel";
		Date topDate = new Date();

		String sourceFileName = request.getSession().getServletContext()
				.getRealPath("resources/" + reportFileName + ".jrxml");

		try {

			JasperCompileManager.compileReportToFile(sourceFileName);

			sourceFileName = request.getSession().getServletContext()
					.getRealPath("/resources/" + reportFileName + ".jasper");

			Map<String, Object> parameters = new HashMap<>();

			parameters.put("OfferLetter", offerLetter);
			parameters.put("topDate", topDate);
			parameters.put("name", name);
			parameters.put("To", To);
			parameters.put("from", from);
			parameters.put("role", role);

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

}
