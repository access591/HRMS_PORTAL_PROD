package com.hrms.service;

import java.util.List;

import com.hrms.model.Information;

public interface Informationservice {

	List<Information> getAllInformation();

	void addInformation(Information information);

	Information findInformationById(long id);

	public void updateInformation(Information i);

	public void removeInformation(long id);

}
