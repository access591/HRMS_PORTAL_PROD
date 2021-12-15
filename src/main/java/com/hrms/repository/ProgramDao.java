package com.hrms.repository;

import java.util.List;

import com.hrms.dao.GenericDao;
import com.hrms.model.Program;

public interface ProgramDao extends GenericDao<Program> {



	List<Program> findByProgramCodeSubModule(String id, String id2);

	Program checkProgramExists(Program program);
	
}
