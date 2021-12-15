package com.hrms.repository;

import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.LoanSchedule;
@Repository
public class LoanTrackingDaoImpl extends AbstractGenericDao<LoanSchedule> implements LoanTrackingDao  {

}
