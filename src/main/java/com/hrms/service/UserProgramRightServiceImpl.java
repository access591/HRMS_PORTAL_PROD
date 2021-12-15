package com.hrms.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.query.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.UserEntity;
import com.hrms.model.UserRights;
import com.hrms.repository.UserProgramRightDao;
@Service
public class UserProgramRightServiceImpl implements UserProgramRightService {
	@Autowired
	UserProgramRightDao userProgramRightDao;
	@Autowired SessionFactory sessionFactory;


	@Override
	public List<UserRights> getAllUserRights() {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<UserRights> result = null;
		try {
			
			tx = session.beginTransaction();
			Query<UserRights> query = session.createQuery("select distinct u from UserRights u",
					UserRights.class);
		
			result = query.getResultList();
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return result;
	}
	@Override
	public void addUserProgramRight(UserRights userRights) {
		userRights.setInsDate(new Date());
		// this.userProgramRightDao.saveOrUpdate(userRights);
		 
			Session session = sessionFactory.openSession();
			Transaction tx = null;
			try {
				 tx = session.beginTransaction();
				 session.save(userRights);
				 tx.commit();
			}catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				session.close();
			}

		
	}
	@Override
	public boolean checkUserRightsExists(UserRights userRights) {
		UserRights e = userProgramRightDao.checkUserRightsExists(userRights);
		if (e != null) {
			return true;
		} else {
			return false;
		}
	}
	@Override
	public void removeUserProgramRight(long id) {
		this.userProgramRightDao.delete(id);
		
	}
	@Override
	public UserRights findUserRightById(long id) {
		return userProgramRightDao.findById(id);
	
	}
	@Override
	public void updateUserRights(UserRights ur) {
		ur.setUserCode(ur.getUserCode());
		ur.setModuleCode(ur.getModuleCode());
		ur.setSubModuleCode(ur.getSubModuleCode());
		ur.setPrgCode(ur.getPrgCode());
		ur.setUpdDate(ur.getUpdDate());
		this.userProgramRightDao.saveOrUpdate(ur);
		
	}
	@Override
	public List<UserRights> findUserRightsByUserCode(String userCode) {
		
		System.out.println("find user rights method ");
		List<UserRights> results = new ArrayList<>();
		
		UserRights u = new UserRights();
		//UserRights u1 = new UserRights();
		
		if(userCode == "101") {
			//u.setUserRole("ROLE_ADMIN");
		}else {
			//u.setUserRole("ROLE_USER");
		}
		
		//u1.setUserRole("USERDETAIL");
		
		results.add(u);
		
		
		
//		Session session = sessionFactory.openSession();
//		Transaction tx = null;
//		
//		try {
//			tx = session.beginTransaction();
//			Query<UserRights> query = session.createQuery("from UserRights u where u.userCode.userCode=:userCode",UserRights.class);
//			query.setParameter("userCode", userCode);
//			results = query.getResultList();
//			results.removeAll(Arrays.asList("", null));
//			
//			for(UserRights u : results) {
//				System.out.println("=========>"+u.getUserRole());
//			}
//			tx.commit();
//		}catch(Exception e) {
//			e.printStackTrace();
//		}finally {
//			session.close();
//		}
		
		return results;
	}

}
