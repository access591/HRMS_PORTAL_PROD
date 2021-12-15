package com.hrms.repository;



import java.util.List;

import com.hrms.dao.GenericDao;
import com.hrms.model.City;

public interface CityDao extends GenericDao<City>{

	List<City> findCityByState(String stateCode);



	

}
