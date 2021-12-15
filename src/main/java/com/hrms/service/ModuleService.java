package com.hrms.service;

import java.util.List;
import com.hrms.model.MenuModule;
import com.hrms.model.Module;

public interface ModuleService {
	List<MenuModule> getAllModules();
	List<MenuModule> getAllModulesList(String userCode);
	boolean checkModuleExists(Module module);
	List<Module> getModules();
	List<Module>getActiveModules();
	void addModule(Module module);
	Module findModuleById(String id);
	public void updateModule(Module m); 
	public void removeModule(String id);
	boolean checkModuleSeqExists(Module module);

	
}
