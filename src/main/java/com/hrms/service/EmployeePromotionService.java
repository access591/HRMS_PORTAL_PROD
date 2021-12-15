package com.hrms.service;

import java.util.List;

import com.hrms.model.EmployeePromotion;

public interface EmployeePromotionService {

	boolean addEmployeePromotion(EmployeePromotion employeePromotion) ;

	List<EmployeePromotion> getAllEmployeePromotion();

	void removeEmployeePromotion(long id);

	EmployeePromotion findByIdEmployeePromotion(long id);

	void updateEmployeePromotion(EmployeePromotion employeePromotion);
	
	public EmployeePromotion findemployeePromotionByEmpCode(String empCode);



	
		
	
}
