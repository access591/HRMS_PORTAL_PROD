package com.hrms.repository;

import java.util.List;

import com.hrms.dao.GenericDao;
import com.hrms.model.LeaveGrant;

public interface LeaveGrantRegisterDao  extends GenericDao<LeaveGrant>{

	public List<LeaveGrant> findLeaveGrantByEmp(String empCode);
}
