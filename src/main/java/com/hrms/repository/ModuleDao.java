package com.hrms.repository;

import java.util.List;

import com.hrms.dao.GenericDao;
import com.hrms.model.Module;
import com.hrms.model.Program;
import com.hrms.model.SubModule;

public interface ModuleDao  extends GenericDao<Module>{
	
	Module findModule(Module module);

	List<Module> getAllModulesList(String userCode);

	List<SubModule> getAllSubModule(String modulecCode, String ucode);

	List<Program> getAllProgramList(String moduleCode, String smCode, String userCode);

	Module checkModuleExists(Module module);

	Module checkModuleSeqExists(Module module);

	List<Module> getActiveModules();
	   

}
