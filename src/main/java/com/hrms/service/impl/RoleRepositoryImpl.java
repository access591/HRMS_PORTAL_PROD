package com.hrms.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.hrms.model.Role;
import com.hrms.service.RoleRepository;

@Service
public class RoleRepositoryImpl implements RoleRepository {

	@Autowired
	SessionFactory sessionFactory;

	
	@Override
	public void addRole(Role role) {

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			session.save(role);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	@Override
	public Role isRoleExistOrNot(String roleName) {

		System.out.println("role Name : " + roleName);

		//roleName = roleName.replaceAll("\\s", "").toUpperCase();

		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		
		Role role = null;
		try {
			tx = session.beginTransaction();

			Query<Role> query = session.createQuery("from Role r where r.roleName=:roleName",Role.class);
			
			query.setParameter("roleName", roleName);
			
			role = query.getSingleResult();
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		
		} finally {
			session.close();
		}

		return role;
	}

	
	

}
