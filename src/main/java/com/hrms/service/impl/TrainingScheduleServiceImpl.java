package com.hrms.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.TrainingSchedule;
import com.hrms.repository.TrainingScheduleDao;
import com.hrms.service.TrainingScheduleService;

@Service
public class TrainingScheduleServiceImpl implements TrainingScheduleService{

	@Autowired TrainingScheduleDao trainingScheduleDao; 
	@Autowired SessionFactory sessionFactory;
	
	@Override
	public void saveTrainingSchedule(TrainingSchedule trainingSchedule) {
		
		trainingSchedule.setTrScheduleCode(trainingScheduleDao.getMaxId("SCE"));
		
		trainingScheduleDao.saveOrUpdate(trainingSchedule);
		
	}
	@Override
	public List<TrainingSchedule> getAllTrainingSchedule() {
		
		return trainingScheduleDao.findAll();
	}
	@Override
	public TrainingSchedule findTrainingScheduleById(String trReqCode) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<TrainingSchedule> query = session.createQuery("from TrainingSchedule e where e.trScheduleCode = :trReqCode", TrainingSchedule.class);
			query.setParameter("trReqCode", trReqCode);
			tx.commit();
			return query.getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return null;
	}
	
	@Override
	public void removeTrainingSchedule(String trReqCode) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Object o = session.get(TrainingSchedule.class, trReqCode);
			TrainingSchedule e = (TrainingSchedule) o;
			
			session.delete(e);
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
	
	}
	
	@Override
	public void updateTrainingSchedule(TrainingSchedule trainingSchedule) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.load(TrainingSchedule.class, trainingSchedule.getTrScheduleCode());
			
			session.merge(trainingSchedule);
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
			
		
		
		
			
			
		
		
	}

}
