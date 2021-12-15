package com.hrms.service;

import java.util.List;

import com.hrms.model.LocalConvyence;

public interface ConveyanceApprovalService {

	List<LocalConvyence>getAllLocalConveyance();

	void approvedByLocalConvyenceId(String id);
}
