package com.hrms.repository;

import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.TrainingSchedule;

@Repository
public class TrainingScheduleDaoImpl extends AbstractGenericDao<TrainingSchedule> implements TrainingScheduleDao{

}
