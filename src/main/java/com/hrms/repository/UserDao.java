package com.hrms.repository;

import java.util.List;

import com.hrms.dao.GenericDao;
import com.hrms.model.Login;
import com.hrms.model.UserEntity;

public interface UserDao extends GenericDao<UserEntity>{
	
	UserEntity findUser(Login login);

	List<UserEntity> getActiveUsers();

	UserEntity checkUserExistsOrNot(UserEntity userEntity);

	UserEntity checkUserExistsOrNotRest(UserEntity userEntity);



	


}
