package com.hrms.repository;

import java.util.List;

import com.hrms.dao.GenericDao;
import com.hrms.model.Country;

public interface CountryDao extends GenericDao<Country> {

	List<Country> getActiveCountrys();

}
