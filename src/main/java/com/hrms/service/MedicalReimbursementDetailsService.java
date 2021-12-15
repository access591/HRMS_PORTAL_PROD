package com.hrms.service;

import java.util.List;

import com.hrms.model.MedicalReimbursementDetail;

public interface MedicalReimbursementDetailsService {

	boolean addMedicalReimbursementDetails(MedicalReimbursementDetail m4);
	List<MedicalReimbursementDetail> getAllMedicalReimbursementDetails();
	MedicalReimbursementDetail getAllMedicalBYslipNO(String slipNo);
	
}
