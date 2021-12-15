package com.hrms.repository;

import java.util.List;

import com.hrms.dao.GenericDao;
import com.hrms.model.LocalConvyence;

public interface ConveyanceApprovalDao extends GenericDao<LocalConvyence>{

	List<LocalConvyence> getAllLocalConveyance();

	void approvedByLocalConvyenceId(String id);

}
