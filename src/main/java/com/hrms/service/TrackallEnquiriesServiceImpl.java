package com.hrms.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.TrackallEnquiries;
import com.hrms.repository.TrackallEnquiriesDao;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@Service
public class TrackallEnquiriesServiceImpl implements TrackallEnquiriesService {
	@Autowired TrackallEnquiriesDao trackallEnquiriesDao ;

	@Override
	public void addTrackallEnquiries(TrackallEnquiries trackallEnquiries) {
	
		this.trackallEnquiriesDao.saveOrUpdate(trackallEnquiries);
		
	}

	@Override
	public TrackallEnquiries findByIdTrackallEnq(long id) {
		
		return this.trackallEnquiriesDao.findById(id);
	}

	@Override
	public List<TrackallEnquiries> getAllTrackallEnquiries() {
		
		return this.trackallEnquiriesDao.findAll();
	}

	@Override
	public void removeTrackallEnquiries(Long id) {
		this.trackallEnquiriesDao.delete(id);	
		
	}

	@Override
	public void updateTrackallEnquiries(TrackallEnquiries trackallEnquiries) {
		this.trackallEnquiriesDao.saveOrUpdate(trackallEnquiries);
		
	}

	@Override
	public void trackallEnquirieGenratepdf(HttpServletRequest request, HttpServletResponse response,
			String reportFileName, List<TrackallEnquiries> dataList) {
		 String sourceFileName =request.getSession().getServletContext().getRealPath("resources/" + reportFileName + ".jrxml");
		   
		     String generatedBy ="";
	        String generatedDates ="";
	        generatedBy = request.getSession().getAttribute("USER_NAME").toString();
	        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
	        LocalDateTime now = LocalDateTime.now();
	        generatedDates = dtf.format(now);
		    
				try {
					System.out.println("Start compiling!!! ...");
					JasperCompileManager.compileReportToFile(sourceFileName);
					System.out.println("Done compiling!!! ...");
					sourceFileName = request.getSession().getServletContext().getRealPath("/resources/" + reportFileName + ".jasper");
					System.out.println("Jasper File Created!!! ...");
				
					JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataList);
					
					Map<String, Object> parameters = new HashMap<>();
					parameters.put("ItemDataSource", beanColDataSource);
					parameters.put("GeneratedBy", generatedBy);
			        parameters.put("GeneratedDates", generatedDates);
					
					
					JasperReport report = (JasperReport) JRLoader
							.loadObjectFromFile(sourceFileName);
					JasperPrint jasperPrint = JasperFillManager.fillReport(report,
							parameters, new JREmptyDataSource());
					
					
					if (jasperPrint != null) {
						byte[] pdfReport = JasperExportManager
								.exportReportToPdf(jasperPrint);
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
