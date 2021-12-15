package com.hrms.repository;

import com.hrms.dao.GenericDao;
import com.hrms.model.ArmsLicenses;
import com.hrms.model.Employee;

public interface ArmsLicenseDao extends GenericDao<ArmsLicenses> {

	ArmsLicenses findArmsByEmpEmpCode(String id);

}
