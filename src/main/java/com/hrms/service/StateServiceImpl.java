package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.State;
import com.hrms.repository.StateDao;
@Service
public class StateServiceImpl  implements StateService{

	@Autowired
	StateDao stateDao;
	@Override
	public void addState(State state) {
	state.setStateCode(stateDao.getMaxId("STT"));	
	this.stateDao.saveOrUpdate(state);
		
	}

	@Override
	public List<State> getAllStates() {
		return stateDao.findAll();
	
	}

	@Override
	public State findStateById(String id) {
	
		return stateDao.findById(id);
	}

	@Override
	public void updateState(State s) {
	
		this.stateDao.saveOrUpdate(s);	
	}

	@Override
	public void removeState(String id) {
		
		this.stateDao.delete(id);
	}

	@Override
	public List<State> findStateByCountry(String country) {
		
		return this.stateDao.findStateByCountry(country);
	}

	@Override
	public List<State> getActiveStates() {
		
		return stateDao.getActiveStates();
	}

}
