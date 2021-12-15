package com.hrms.service;

import java.util.Date;
import java.util.List;


import com.hrms.model.LtaRequest;

public interface LtaRequestService {

	void addLtaRequest(LtaRequest ltaRequest);
	List<LtaRequest>getAllLTARequest();
	public void removeLTAReques(String id);
	LtaRequest findByIdLta(String id);
	void updateLtaRequest(LtaRequest ltaRequest);
	public List<LtaRequest> findAllLtaByEmpCode(String empCode);
	public List<LtaRequest> findLtaByFromLeaveDateToLeave(Date leaveFrom,Date leaveTo,String empCode);
	public List<LtaRequest> findAllLtaByFromLeaveDateToLeave(Date leaveFrom,Date leaveTo);
	public List<LtaRequest> getAllDistinctLtaRequest();
	
}
