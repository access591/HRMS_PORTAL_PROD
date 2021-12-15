package com.hrms.repository;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;

import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.Login;
import com.hrms.model.UserEntity;

@Repository
public class UserDaoImpl extends AbstractGenericDao<UserEntity> implements UserDao {

	private Logger logger = LoggerFactory.getLogger(UserDaoImpl.class.getName());
	@Autowired PasswordEncoder passwordEncoder;
	@Override
	public UserEntity findUser(Login login) {
		UserEntity user = null;
		try {

			Criteria criteria = getSession().createCriteria(UserEntity.class);
			user = (UserEntity) criteria.setFetchMode("Myuser", FetchMode.SELECT)
					.add(Restrictions.eq("userCode", login.getUserCode()))
					.add(Restrictions.eq("userActiveYn","Y"))
					.add(Restrictions.eq("userPass",passwordEncoder.encode(login.getUserPassword()))).uniqueResult();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return user;

	}

	@Override
	public List<UserEntity> getActiveUsers() {
		Query querym =null;
		
		try {
			querym =getSession().createQuery("from UserEntity s where  s.userActiveYn=:status");
			querym.setParameter("status","Y");
			List<UserEntity> userList = querym.list();

			return userList;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserEntity checkUserExistsOrNot(UserEntity userEntity) {
		UserEntity  userCode=null;
		try {
			
			Criteria criteria = getSession().createCriteria(UserEntity.class);
			userCode = (UserEntity) criteria.setFetchMode("User_code", FetchMode.SELECT)
					.add(Restrictions.eq("userCode", userEntity.getUserCode())).uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return userCode;
	}

	@Override
	public UserEntity checkUserExistsOrNotRest(UserEntity us) {
		UserEntity result = null;
		try {
			Criteria crit = createEntityCriteria();
	        crit.add(Restrictions.eq("userCode", us.getUserCode()));
	        
	        return (UserEntity) crit.uniqueResult();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return result;

	}
	}
	


