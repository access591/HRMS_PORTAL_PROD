package com.hrms.service;

import java.util.List;

import com.hrms.model.LtaRequest;

public interface LtaApprovalService {

	List<LtaRequest> getAllLtaApproval();

	void approvedLtaRequestById(String id);

}
