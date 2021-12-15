package com.hrms.service;

import java.util.List;

import com.hrms.model.MedicalReimbursement;

public interface MedicalReimbursementService {

	boolean addMedicalReimbursement(MedicalReimbursement m2);

	List<MedicalReimbursement> getAllMedicalReimbursement();

	MedicalReimbursement findByIdMedicalReimbursementMaster(String id);
	void removeMedicalReimbursement(String id);

	void updateMedicalReimbursement(MedicalReimbursement medicalReimbursement);

	void approvedByMrId(String slipNo);

	List<MedicalReimbursement> getAllMedicalReimbursementApproval();

}
