package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.Rules;
import com.hrms.repository.RulesDao;
@Service
public class RulesServiceImpl implements RulesService {
	@Autowired
	RulesDao rulesDao;

	@Override
	public void addRules(Rules rules) {
		try {
			this.rulesDao.save(rules);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}
	@Override
	public List<Rules> getAllRules() {

		try {
			return rulesDao.findAll();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Rules findRulesById(long id) {

		try {
			return rulesDao.findById(id);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateRules(Rules r) {
		try {
			this.rulesDao.saveOrUpdate(r);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@Override
	public void removeRules(long id) {
		try {
			this.rulesDao.delete(id);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
