package com.hrms.repository;

import java.util.List;

import com.hrms.dao.GenericDao;
import com.hrms.model.MedicalReimbursement;

public interface MedicalReimbursementDao extends GenericDao<MedicalReimbursement> {

	void approvedByMrId(String slipNo);

	List<MedicalReimbursement> getAllMedicalReimbursementApproval();

}
