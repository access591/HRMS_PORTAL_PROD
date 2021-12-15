package com.hrms.repository;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.City;

/**
 * @author Access surendra
 *
 */
@Repository
public class CityDaoImpl extends AbstractGenericDao<City> implements CityDao {
	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Query query = null;

	@Override
	public List<City> findCityByState(String stateCode) {
		session = this.sessionFactory.getCurrentSession();
		query = session.createQuery("from City e where e.stateCode.stateCode= :stateCode and e.active=:status");
		query.setParameter("stateCode", stateCode);
		query.setParameter("status", "Y");
		List<City> cityList = query.list();
		return cityList;
	}

}
