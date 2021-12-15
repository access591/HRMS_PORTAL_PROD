package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.Country;
import com.hrms.repository.CountryDao;
@Service
public class CountryServiceImpl implements CountryService {
	@Autowired
	CountryDao countryDao;
	@Override
	public void addCountry(Country country) {
		country.setCountryCode(countryDao.getMaxId("CTR"));
		this.countryDao.saveOrUpdate(country);
	}

	@Override
	public List<Country> getAllCountrys() {
		return countryDao.findAll();
		
	}

	@Override
	public Country findCountryById(String id) {
	
		return countryDao.findById(id);
	}

	@Override
	public void updateCountry(Country c) {
	
		this.countryDao.saveOrUpdate(c);
	}

	@Override
	public void removeCountry(String id) {
	this.countryDao.delete(id);
		
	}

	@Override
	public List<Country> getActiveCountrys() {
	
		return countryDao.getActiveCountrys();
	}

}
