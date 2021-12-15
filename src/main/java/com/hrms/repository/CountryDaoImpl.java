package com.hrms.repository;

import java.util.List;

import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;

import com.hrms.model.Country;

@Repository
public class CountryDaoImpl extends AbstractGenericDao<Country> implements CountryDao {
	private Logger logger = LoggerFactory.getLogger(CountryDaoImpl.class.getName());
	@Override
	public List<Country> getActiveCountrys() {
	    Query query =null;
			try {
				query =getSession().createQuery("from Country c where  c.active=:status");
				query.setParameter("status","Y");
				List<Country> catList = query.list();
	   
				return catList;
			} catch (Exception e) {
				
				logger.info(e.getMessage());
			}
			return null;
		}
	}

