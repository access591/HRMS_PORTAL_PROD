package com.hrms.service;

import java.util.List;

import com.hrms.model.Award;

public interface AwardService {
	public void addAward(Award award);
	List<Award>getAllAwards();
	Award findAwardById(long id);
	public void updateAward(Award c);
	public void removeAward(long id);
	public boolean checkAwardExists(Award award);

}
