package com.hrms.service;

import java.util.List;

import com.hrms.model.LocalConvyence;

public interface LocalConvyenceService {

	void addlocalConvyence(LocalConvyence lc);

	void removelocalConvyence(String id);

	List<LocalConvyence> getAlllocalConvyence();

	LocalConvyence findByIdLocalConvyence(String id);

	void updateLocalConvyence(LocalConvyence localConvyence);
	

}
