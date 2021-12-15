package com.hrms.model;

import java.util.List;
import java.util.Map;

public class MenuModule {

	private String moduleName;
	private String moduleCode;
	private Map<String, String> programs;
	private List<SubModuleProgram> subModuleProgram;

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	public Map<String, String> getPrograms() {
		return programs;
	}

	public void setPrograms(Map<String, String> programs) {
		this.programs = programs;
	}

	public List<SubModuleProgram> getSubModuleProgram() {
		return subModuleProgram;
	}

	public void setSubModuleProgram(List<SubModuleProgram> subModuleProgram) {
		this.subModuleProgram = subModuleProgram;
	}

}
