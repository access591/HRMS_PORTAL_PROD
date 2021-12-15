package com.hrms.service;

import java.util.List;

import com.hrms.model.Country;

public interface CountryService {

	public void addCountry(Country country);
	List<Country>getAllCountrys();
	Country findCountryById(String id);
	public void updateCountry(Country c);
	public void removeCountry(String id);
	public List<Country> getActiveCountrys();

}
