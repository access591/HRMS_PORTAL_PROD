package com.hrms.service;

import java.util.List;

import com.hrms.model.Rules;


public interface RulesService {

	void addRules(Rules rules);
	List<Rules> getAllRules();

	Rules findRulesById(long id);

	public void updateRules(Rules r);

	public void removeRules(long id);

}
