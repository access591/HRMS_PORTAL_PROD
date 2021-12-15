package com.hrms.repository;


import com.hrms.dao.GenericDao;
import com.hrms.model.UserRights;

public interface UserProgramRightDao extends GenericDao<UserRights> {

	UserRights checkUserRightsExists(UserRights userRights);
	
	

}
