package com.hrms.repository;

import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.Policy;
@Repository
public class PolicyDaoImpl extends AbstractGenericDao<Policy> implements PolicyDao{

}
