package com.hrms;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

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

import com.hrms.model.Category;
import com.hrms.model.Department;
import com.hrms.model.Designation;
import com.hrms.model.Employee;
import com.hrms.service.CategoryService;
import com.hrms.service.DepartmentService;
import com.hrms.service.DesignationService;
import com.hrms.service.EmployeeService;

@Component
public class EmployeeGradationExcel {

	@Autowired
	DepartmentService departmentService;

	@Autowired
	EmployeeService employeeService;
	@Autowired
	DesignationService designationService;
	@Autowired
	CategoryService categoryService;

	public ByteArrayInputStream generateExcel(List<Employee> listEmployee) {

		String[] columns = { "Sr.No", "Officer Code", "Officer Name", "Category Name", "Department Name", "Designation",
				"IPS No", " Batch Year", "Payee Code", "Home District", "Court or Department", "Date Of Birth",
				"Present Posting", "Date of Posting", "Date of Joining", "Date of Retirement", "Gender",
				"Qualification", "Aadhar No", "Mobile No", "Office Phone", "Email", "Under Rule7", "Under Rule 8",
				"Vigilance Inquiry", "Suspenction", "Promotion", "ACP", "APR", "ACR", "Trainig", "LTC", "Leave Acount",
				"Awards", "Deputation", "Previous Postings", "OnDeputation", "Add Charge", "OnAdditionalCharge",
				"Remarks", "VRS", "Expired" };

		try {
			Workbook workBook = new XSSFWorkbook();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			Sheet sheet = workBook.createSheet("employee");

			Font headerFont = workBook.createFont();
			headerFont.setBold(true);
			headerFont.setColor(IndexedColors.BLUE.getIndex());

			CellStyle headerCellStyle = workBook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			Row headerRow = sheet.createRow(0);

			for (int col = 0; col < columns.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(columns[col]);
				cell.setCellStyle(headerCellStyle);

			}

			int rowIndex = 1;

			for (Employee emp : listEmployee) {

				Employee e = employeeService.findEmployeeById(emp.getEmpCode());
				Department d = departmentService.findDepartmentById(e.getDepartment().getDepartmentCode());

				Designation desig = designationService.findDesignationById(emp.getDesignation().getDesgCode());

				Category category = categoryService.findCategoryByCatId(emp.getCategory().getCategoryCode());

				Row row = sheet.createRow(rowIndex++);

				row.createCell(0).setCellValue(rowIndex); //
				row.createCell(1).setCellValue(emp.getEmpCode());
				row.createCell(2).setCellValue(emp.getEmpName());
				row.createCell(3).setCellValue(category.getCategoryName());
				row.createCell(4).setCellValue(d.getDeptName());
				row.createCell(5).setCellValue(desig.getDesgName());
				row.createCell(6).setCellValue("");
				row.createCell(7).setCellValue(emp.getBatchYear());
//				row.createCell(8).setCellValue(emp.getEmployeePayeeCode());
//				row.createCell(9).setCellValue(emp.getEmployeePayeeCode());
//
//				if (emp.getTypeCourtDepartment().equals("D")) {
//					row.createCell(10).setCellValue("Department");
//				} else if (emp.getTypeCourtDepartment().equals("C")) {
//					row.createCell(10).setCellValue("Court");
//				} else {
//					row.createCell(10).setCellValue("-");
//				}
//
//				row.createCell(11).setCellValue(emp.getDateOfPosting().toString()); // birth
//
//				row.createCell(12).setCellValue(emp.getPresentPosting());
//				row.createCell(13).setCellValue(emp.getDateOfPosting().toString().substring(0, 11));
//				row.createCell(14).setCellValue(emp.getDateOfJoining().toString().substring(0, 11));
//				row.createCell(15).setCellValue(emp.getDateOfRetirement().toString().substring(0, 11));
//
//				if (emp.getGender().equals("M")) {
//					row.createCell(16).setCellValue("Male");
//				} else if (emp.getGender().equals("F")) {
//					row.createCell(16).setCellValue("Female");
//				} else {
//					row.createCell(16).setCellValue("-");
//				}
//
//				row.createCell(17).setCellValue(emp.getQualification());
//				row.createCell(18).setCellValue(emp.getAadharNo());
//				row.createCell(19).setCellValue(emp.getMobileNumber1());
//				row.createCell(20).setCellValue(emp.getMobileNumber1()); // office phone
//				row.createCell(21).setCellValue(emp.getEmail());
//
//				if (emp.getUnderRule7().equals("Y")) {
//					row.createCell(22).setCellValue("Yes"); 
//				} else if (emp.getUnderRule7().equals("N")) {
//					row.createCell(22).setCellValue("No"); 
//				} else {
//					row.createCell(22).setCellValue("-"); 
//				}
//				
//				if (emp.getUnderRule8().equals("Y")) {
//					row.createCell(23).setCellValue("Yes");
//				} else if (emp.getUnderRule8().equals("N")) {
//					row.createCell(23).setCellValue("No");
//				} else {
//					row.createCell(23).setCellValue("-");
//				}
//				// add column
//				
//				row.createCell(24).setCellValue(emp.getVigilanceQuery());
//				row.createCell(25).setCellValue(emp.getSuspention());
//				row.createCell(26).setCellValue(emp.getPromotion());
//				row.createCell(27).setCellValue(emp.getAcp());
//				row.createCell(28).setCellValue(emp.getApr());
//				row.createCell(29).setCellValue(emp.getAcr());
//				row.createCell(30).setCellValue(emp.getTraining());
//				row.createCell(31).setCellValue(emp.getLtc());
//				row.createCell(32).setCellValue(emp.getLeaveAccount());
//				row.createCell(33).setCellValue(emp.getEmpAwards());
//				row.createCell(34).setCellValue(emp.getEmpDeputation());
//				row.createCell(35).setCellValue(emp.getPresentPosting());
//				
//				
//				if(emp.getOnDeputation()==null || emp.getOnDeputation().isEmpty() || emp.getOnAdditionalCharge().trim().isEmpty()) 
//				{
//					row.createCell(36).setCellValue("-");
//				}
//				else {
//					
//					if(emp.getOnDeputation().equals("Y")) {
//						row.createCell(36).setCellValue("Yes");
//					}else if(emp.getOnDeputation().equals("N")) {
//						row.createCell(36).setCellValue("No");
//					}
//				}
//				
//				row.createCell(37).setCellValue(emp.getAddCharge());
//				row.createCell(38).setCellValue(emp.getOnAdditionalCharge());
//				row.createCell(39).setCellValue("Remarks");
//				row.createCell(40).setCellValue(emp.getVrs());
				
//				if(emp.getExpired() == null || emp.getExpired().isEmpty() || emp.getExpired().trim().isEmpty()) {
//					row.createCell(41).setCellValue("-");
//				}else {
//					if(emp.getExpired().equals("Y")) {
//						row.createCell(41).setCellValue("Yes");
//					}else if(emp.getExpired().equals("N")) {
//						row.createCell(41).setCellValue("No");
//					}
//				}
				

			}
			workBook.write(out);
			workBook.close();
			return new ByteArrayInputStream(out.toByteArray());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

}
