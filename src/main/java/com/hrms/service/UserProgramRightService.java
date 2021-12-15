package com.hrms.service;
import java.util.List;

import com.hrms.model.UserRights;
public interface UserProgramRightService {
	
	List<UserRights> getAllUserRights();

	public void addUserProgramRight(UserRights userRights);

	boolean checkUserRightsExists(UserRights userRights);
	
	 UserRights findUserRightById(long id);
	 
	  public void updateUserRights(UserRights ur);

	public void removeUserProgramRight(long id);
	
	List<UserRights> findUserRightsByUserCode(String userCode);
}
