package com.hrms.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.EmployeeRequisition;
import com.hrms.model.Login;
import com.hrms.model.UserEntity;
import com.hrms.repository.UserDao;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public boolean checkUserExists(Login login) {
		UserEntity e = userDao.findUser(login);
		if (e != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<UserEntity> getAllUsers() {
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<UserEntity> result = null;
		try {
			
			tx = session.beginTransaction();
			Query<UserEntity> query = session.createQuery("select distinct u from UserEntity u where u.userActiveYn=:status",
					UserEntity.class);
			query.setParameter("status", "Y");
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
	public UserEntity findDataById(String id) {
		return userDao.findById(id);

	}

	@Override
	public void addUser(UserEntity userEntity) {
		this.userDao.saveOrUpdate(userEntity);

	}

	@Override
	public boolean checkUserExistsOrNot(UserEntity userEntity) {

		UserEntity e = userDao.checkUserExistsOrNot(userEntity);
		if (e != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void removeUser(String id) {
		this.userDao.delete(id);

	}

	@Override
	public UserEntity findUserById(String id) {

		return userDao.findById(id);
	}

	
	@Override
	public void updateUser(UserEntity u) {
		
		System.out.println(":::::::::::::::::::::::User Password update Done::::::::::::::::::::::"+u.getUserPass());
		u.setUpdDate(u.getUpdDate());
		this.userDao.saveOrUpdate(u);
	}

	@Override
	public List<UserEntity> getActiveUsers() {

		return userDao.getActiveUsers();
	}


	@Override
	public UserEntity findByUserCode(String userCode) {

		
		UserEntity result = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			
			tx = session.beginTransaction();
			Query<UserEntity> query = session.createQuery("from UserEntity u where u.userCode=:userCode", UserEntity.class);
			query.setParameter("userCode", userCode);
			result = query.uniqueResult();
			
			System.out.println("active user : "+ result.getUserCode());
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			session.close();
		}
		return result;

	}

	@Override
	public boolean updatePassword(String userCode, String password) {
		
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		boolean result = true;

		try {
			UserEntity user = session.find(UserEntity.class, userCode);
			user.setUserPass(password);
			tx = session.beginTransaction();
			
			tx.commit();
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
			
		} finally {
			session.close();
		}
		
		return result;
	}

	@Override

	public void update(UserEntity u) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			UserEntity emp = session.find(UserEntity.class, u.getUserCode());
			
			session.merge(u);
			tx.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
		
	
	public boolean checkUserExistsOrNotRest(UserEntity userEntity) {

		UserEntity e = userDao.checkUserExistsOrNotRest(userEntity);
		if (e != null) {
			return true;
		} else {
			return false;
		}

	}
}
