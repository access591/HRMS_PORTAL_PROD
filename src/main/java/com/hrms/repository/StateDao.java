package com.hrms.repository;

import java.util.List;

import com.hrms.dao.GenericDao;
import com.hrms.model.State;

public interface StateDao extends GenericDao<State> {

	List<State> findStateByCountry(String country);

	List<State> getActiveStates();

}
