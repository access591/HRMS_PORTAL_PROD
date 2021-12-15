package com.hrms.repository;

import java.util.List;

import com.hrms.dao.GenericDao;
import com.hrms.model.LtaRequest;

public interface LtaApprovalDao extends GenericDao<LtaRequest> {

	List<LtaRequest> getAllLtaApproval();

	void approvedLtaRequestById(String id);

}
