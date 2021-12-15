package com.hrms.repository;

import com.hrms.dao.GenericDao;
import com.hrms.model.Grade;

public interface GradeMaterDao extends GenericDao<Grade> {

	Grade checkGradeExists(Grade grade);



}
