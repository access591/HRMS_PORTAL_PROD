package com.hrms.service;

import java.util.List;

import com.hrms.model.State;

public interface StateService {
	public void addState(State state);
	List<State>getAllStates();
	State findStateById(String id);
	public void updateState(State s);
	public void removeState(String id);
	public List<State> findStateByCountry(String id);
	public List<State> getActiveStates();

}
