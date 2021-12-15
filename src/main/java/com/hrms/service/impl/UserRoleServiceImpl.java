package com.hrms.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.Role;
import com.hrms.model.UserEntity;
import com.hrms.model.UserRole;
import com.hrms.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void addUserRole(UserRole userRole) {

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			session.save(userRole);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Transactional
	@Override
	public List<UserRole> findUserRoleBuUser(String userCode) {

		System.out.println("user role service : "+ userCode);
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<UserRole> results = null;

		try {
			tx = session.beginTransaction();

			Query<UserRole> query = session.createQuery("from UserRole u where u.userEntity.userCode=:userCode",UserRole.class);
			query.setParameter("userCode", userCode);
			results = query.getResultList();
			
			System.out.println("role length : "+ results.size());
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return results;
	}

	@Override
	public boolean roleExistForUser(String userCode, String roleName) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		boolean result = false;
		UserRole userRole = null;
		
		try {
			tx = session.beginTransaction();
			Query<UserRole> query = session.createQuery("from UserRole ur where ur.role.roleName=:roleName and "
					+ "ur.userEntity.userCode=:userCode",UserRole.class);
			query.setParameter("roleName", roleName);
			query.setParameter("userCode", userCode);
			
			userRole = query.getSingleResult();
			
			result = true;
			
		}catch(Exception e) {
			result = false;
		}
		
		return result;
	}

	@Override
	public void deleteUserRole(UserEntity u, Role role) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		
		System.out.println("delete function execute");
		UserRole userRole = null;
		
		try {
			tx = session.beginTransaction();
			Query<UserRole> query = session.createQuery("from UserRole ur where ur.role.roleId=:role and "
					+ "ur.userEntity.userCode=:u",UserRole.class);
			query.setParameter("role", role.getRoleId());
			query.setParameter("u", u.getUserCode());
			
			System.out.println("delete query execute");
			
			userRole = query.getSingleResult();
			
			session.delete(userRole);
			
			tx.commit();
			
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("delete exception execute");
		}finally {
			session.close();
		}
		
		
	}

}
