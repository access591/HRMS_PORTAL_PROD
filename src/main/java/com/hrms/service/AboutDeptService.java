package com.hrms.service;

import java.util.List;

import com.hrms.model.AboutDept;

public interface AboutDeptService {

	void addAboutDept(AboutDept aboutDept);
    List<AboutDept>getAllAboutsDepts();
	AboutDept findAboutDeptById(long id);
	public void updateAboutDept(AboutDept a);
	public void removeAboutDept(long id);
}
