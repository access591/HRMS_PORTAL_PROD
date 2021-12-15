package com.hrms.service;

import java.util.List;

import com.hrms.model.Designation;

public interface DesignationService 
{
	public void addDesignation(Designation designation);
	List<Designation>getAllDesignations();
	Designation findDesignationById(String id);
	public void updateDesignation(Designation d);
	public void removeDesignation(String id);
	boolean checkDesignationExists(Designation designation);
	public List<Designation> getActiveDesignation();
}
