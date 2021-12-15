package com.hrms.repository;

import com.hrms.dao.GenericDao;
import com.hrms.model.EmpMonOvertime;

public interface EmpMontOvertimeDao extends GenericDao<EmpMonOvertime> {

	EmpMonOvertime checkMonthOverTimeExists(String empCode);

}
