package com.hrms.repository;

import com.hrms.dao.GenericDao;
import com.hrms.model.LeaveDetail;

public interface LeaveDetailDao extends GenericDao<LeaveDetail> {

	LeaveDetail findLeaveDetailByLvCode(String lvCode);

}
