package com.hrms.service;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;



import com.hrms.model.City;
import com.hrms.repository.CityDao;

@Service
public class CityServiceImpl implements CityService {
	@Autowired
	CityDao cityDao;
	@Autowired SessionFactory sessionFactory;
	

	@Override
	public void addCity(City city) {

		city.setCityCode(cityDao.getMaxId("CTY"));
		this.cityDao.saveOrUpdate(city);
	}

	@Override
	public List<City> getAllCities() {
		return cityDao.findAll();
		
	}

	@Override
	public City findCityById(String id) {

		return cityDao.findById(id);
	}

	@Override
	public void updateCity(City c) {
		this.cityDao.saveOrUpdate(c);

	}

	@Override
	public void removeCity(String id) {
		this.cityDao.delete(id);

	}

	@Override
	public List<City> findCityByState(String stateCode) {
	
		return cityDao.findCityByState(stateCode);
	}

	@Override
	public List<City> findAllCity() {
		
		Session session = sessionFactory.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
	    CriteriaQuery<City> cq = cb.createQuery(City.class);
	    Root<City> rootEntry = cq.from(City.class);
	    CriteriaQuery<City> all = cq.select(rootEntry);

	    TypedQuery<City> allQuery = session.createQuery(all);
	    return allQuery.getResultList();
	}

	
}
