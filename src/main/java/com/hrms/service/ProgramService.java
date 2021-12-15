package com.hrms.service;

import java.util.List;

import com.hrms.model.Program;


public interface ProgramService {
	
	List<Program> getAllPrograms();

	void addProgram(Program program);

	boolean checkProgramExists(Program program);

	Program findProgramById(String id);

	public void updateProgram(Program p);

	public void removeProgram(String id);

	List<Program> getActivePrograms();

	public Program findByProgramName(String programName);

	List<Program> findByProgramCodeSubModule(String id, String id2);

	List<Program> findProgramByIdSubModule(String subMCode);

	List<Program> findProgramByIdModuleCode(String moduleCode);

	List<Program> findProgramByModuleCodeSubModule(String moduleCode, String subModulec);
	
}
