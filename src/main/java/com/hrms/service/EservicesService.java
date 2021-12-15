package com.hrms.service;

import java.util.List;

import com.hrms.model.Eservices;
public interface EservicesService {

	List<Eservices> getAllEservices();

	void addEservices(Eservices eServices);

	Eservices findEservicesById(long id);

	public void updateEservices(Eservices e);

	public void removeEservices(long id);

}
