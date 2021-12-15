package com.hrms.repository;

import com.hrms.dao.GenericDao;
import com.hrms.model.Designation;

public interface DesignationDao extends GenericDao<Designation> {

	Designation checkDesignationExists(Designation designation);

}
