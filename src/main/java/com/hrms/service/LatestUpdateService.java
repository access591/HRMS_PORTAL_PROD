package com.hrms.service;

import java.util.List;

import com.hrms.model.LatestUpdate;

public interface LatestUpdateService {

	List<LatestUpdate> getAllLatestUpdate();

	void addLatestUpdate(LatestUpdate latestUpdate);

	LatestUpdate findLatestUpdateById(long id);

	public void updateLatestUpdate(LatestUpdate l);

	public void removeLatestUpdate(long id);


}
