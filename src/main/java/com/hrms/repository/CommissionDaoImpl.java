package com.hrms.repository;

import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.Commission;

@Repository
public class CommissionDaoImpl extends AbstractGenericDao<Commission> implements CommissionDao {

}
