package com.hrms.repository;

import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.Travel;

@Repository
public class TravelDaoImpl extends AbstractGenericDao<Travel> implements TravelDao {

}
