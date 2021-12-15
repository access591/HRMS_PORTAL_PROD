package com.hrms.model;

import java.util.Map;

public class SubModuleProgram {

	private String moduleName;
	private String moduleCode;
	private String subModuleName;
	private String subModuleCode;
	private Map<String, String> subPrograms;

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

	public String getSubModuleName() {
		return subModuleName;
	}

	public void setSubModuleName(String subModuleName) {
		this.subModuleName = subModuleName;
	}

	public String getSubModuleCode() {
		return subModuleCode;
	}

	public void setSubModuleCode(String subModuleCode) {
		this.subModuleCode = subModuleCode;
	}

	public Map<String, String> getSubPrograms() {
		return subPrograms;
	}

	public void setSubPrograms(Map<String, String> subPrograms) {
		this.subPrograms = subPrograms;
	}

}
