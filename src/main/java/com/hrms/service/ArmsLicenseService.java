package com.hrms.service;

import java.util.List;

import com.hrms.model.ArmsLicenses;
import com.hrms.model.Employee;

public interface ArmsLicenseService {

	void addArmsLicenseDetails(ArmsLicenses armsLicense);

	ArmsLicenses findArmsLicenseById(String id);

	List<ArmsLicenses> getAllArmsLicenses();

	void updateArmsLicenseService(ArmsLicenses armsLicense);
	public ArmsLicenses findArmsLicensesByEmployee(String id);
	void removeArmsLicenseService(String id2);
	
	
	//@rahul
	List<ArmsLicenses> allArmsLicenseDetails();
	ArmsLicenses findArmsByEmpEmpCode(String empCode);

}
