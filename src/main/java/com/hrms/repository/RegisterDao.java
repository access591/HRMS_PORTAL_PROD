package com.hrms.repository;

import com.hrms.dao.GenericDao;
import com.hrms.model.Register;

public interface RegisterDao extends GenericDao<Register> {

	Register checkRegisterExists(Register register);

}
