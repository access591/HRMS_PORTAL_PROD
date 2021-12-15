package com.hrms.service;

import java.util.List;

import com.hrms.model.Acts;

public interface ActsService {
	
	void addActs(Acts order);
	List<Acts> getAllActs();

	Acts findActsById(long id);

	public void updateActs(Acts n);

	public void removeActs(long id);

}
