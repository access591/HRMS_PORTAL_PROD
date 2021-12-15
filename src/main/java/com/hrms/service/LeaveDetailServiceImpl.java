package com.hrms.service;

import java.io.IOException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.hrms.model.LeaveDetail;
import com.hrms.repository.LeaveDetailDao;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@Service
public class LeaveDetailServiceImpl  implements LeaveDetailService{
	@Autowired
	LeaveDetailDao leaveDetailDao;
	
	@Autowired SessionFactory sessionFactory;
	
	@Override
	public void addLeaveDetail(LeaveDetail leaveDetail) {
		leaveDetail.setLvCode(leaveDetailDao.getMaxId("LVC"));
		this.leaveDetailDao.saveOrUpdate(leaveDetail);
		
	}

	@Override
	public List<LeaveDetail> getAllLeaveDetails() {
	
		return leaveDetailDao.findAll();
	}

	@Override
	public LeaveDetail findLeaveDetailById(String id) {
	
		return this.leaveDetailDao.findById(id);
	}

	@Override
	public void updateLeaveDetail(LeaveDetail leaveDetail) {
	this.leaveDetailDao.saveOrUpdate(leaveDetail);
		
	}

	@Override
	public void removeLeaveDetail(String id) {
	this.leaveDetailDao.delete(id);
		
	}

	@Override
	public void leaveReportGenratepdf(HttpServletRequest request, HttpServletResponse response, String reportFileName,
			List dataList) {
		
	    String sourceFileName =request.getSession().getServletContext().getRealPath("resources/" + reportFileName + ".jrxml");
	   // String path = jasperFolder+"/resources/Reports" + reportFileName+ ".jrxml");
	    
	    
			try {
				System.out.println("Start compiling!!! ...");
				JasperCompileManager.compileReportToFile(sourceFileName);
				System.out.println("Done compiling!!! ...");
				sourceFileName = request.getSession().getServletContext().getRealPath("/resources/" + reportFileName + ".jasper");
				System.out.println("Jasper File Created!!! ...");
			
				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataList);
				
				Map<String, Object> parameters = new HashMap<>();
				parameters.put("ItemDataSource", beanColDataSource);
				
				
				
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

	@Override
	public LeaveDetail findLeaveDetailByLvCode(String lvCode) {
		
		return this.leaveDetailDao.findLeaveDetailByLvCode(lvCode);
	}

	@Override
	public List<LeaveDetail> findLeaveDetailByLeaveType(String leaveType) {
		
		try {
			Session session = sessionFactory.openSession();
			Query<LeaveDetail> query = session.createQuery("from LeaveDetail ld where ld.leaveType = :leaveType",LeaveDetail.class);
			query.setParameter("leaveType", leaveType);
			
			List<LeaveDetail> list = query.getResultList();
			
			return list;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}


	
	public void generateEXCELleaveDetailService( HttpServletRequest request,HttpServletResponse response,String reportFileName,String filename,List dataList) throws IOException{
		 
	   String sourceFileName =request.getSession().getServletContext().getRealPath("resources/" + reportFileName + ".jrxml");

		System.out.println(sourceFileName);

		try {
			System.out.println("Start compiling!!! ...");
			JasperCompileManager.compileReportToFile(sourceFileName);
			System.out.println("Done compiling!!! ...");
			sourceFileName = request.getSession().getServletContext().getRealPath("/resources/" + reportFileName + ".jasper");
			System.out.println("Jasper File Created!!! ...");
		
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataList);
			
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put(JRParameter.IS_IGNORE_PAGINATION, true);
			parameters.put("ItemDataSource", beanColDataSource);
			
			
			
			JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(sourceFileName);
			JasperPrint jasperPrint = JasperFillManager.fillReport(report,parameters, new JREmptyDataSource());
			
			if (jasperPrint != null) {
							
				JRXlsxExporter exporter = new JRXlsxExporter(); // initialize exporter ]
				
			    response.reset();
			    response.setContentType("application/excel");
			    response.setHeader("Content-Disposition", "attachment; filename="+filename+".xlsx");
				
				exporter.setExporterInput(new SimpleExporterInput(jasperPrint)); // set compiled report as input
			    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));  // set output file via path with filename
			    
			    SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
			    configuration.setOnePagePerSheet(true); // setup configuration
			    configuration.setDetectCellType(true);
			    configuration.setIgnoreCellBorder(true);
	            configuration.setWrapText(true);
	            configuration.setWhitePageBackground(false);
	            configuration.setAutoFitPageHeight(true);
	            
			    exporter.setConfiguration(configuration); // set configuration
			    exporter.exportReport();  
			    
			}	
		} catch (JRException e) {
			e.printStackTrace();
		}
	    
	}



}
