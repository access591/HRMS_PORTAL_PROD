package com.hrms.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.LeaveDetail;

@Repository
public class LeaveDetailDaoImpl extends AbstractGenericDao<LeaveDetail> implements LeaveDetailDao  {

  
	@Autowired SessionFactory sessionFactory;
	
	@Override
	public LeaveDetail findLeaveDetailByLvCode(String lvCode) {
		
		Session session = sessionFactory.getCurrentSession();
		Query<LeaveDetail> query = session.createQuery("from LeaveDetail lv where lv.lvCode=:lvCode");
		query.setParameter("lvCode", lvCode);
		if(query.list().size()>=1) {
			LeaveDetail lv = (LeaveDetail) query.list().get(0);
			return lv;
		}
		return null;
	}

}
