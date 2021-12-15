package com.hrms.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.Country;
import com.hrms.model.State;
@Repository
public class StateDaoImpl extends AbstractGenericDao<State> implements StateDao{
	@Autowired SessionFactory sessionFactory;
	private Logger logger = LoggerFactory.getLogger(CountryDaoImpl.class.getName());
	

	Query query =null;
	@Override
	public List<State> findStateByCountry(String country) {
	
		
		query =getSession().createQuery("from State e where e.countryCode.countryCode= :countryCode and e.active=:status");
		query.setParameter("countryCode", country);
		query.setParameter("status", "Y");
		List<State> stateList = query.list();
		return stateList;
	}
	@Override
	public List<State> getActiveStates() {
	  
				try {
					query =getSession().createQuery("from State c where  c.active=:status");
					query.setParameter("status","Y");
					List<State> stateList = query.list();
		   
					return stateList;
				} catch (Exception e) {
					
					logger.info(e.getMessage());
				}
				return null;
	}

}
