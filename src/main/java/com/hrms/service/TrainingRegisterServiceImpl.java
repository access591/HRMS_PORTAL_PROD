package com.hrms.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hrms.model.TrainingRegister;
import com.hrms.repository.TrainingRegisterDao;

@Service
public class TrainingRegisterServiceImpl implements TrainingRegisterService {
	@Autowired
	TrainingRegisterDao trainingRegisterDao;
	@Autowired SessionFactory sessionFactory;
	TrainingRegister em =null;
	Session session =null;
	@Override
	public void addTrainingRegister(TrainingRegister trainingRegister) {
		trainingRegister.setTrRegCode(trainingRegisterDao.getMaxId("TRC"));
		this.trainingRegisterDao.saveOrUpdate(trainingRegister);
	}

	@Override
	public List<TrainingRegister> getAllTrainingRegisters() {
		return trainingRegisterDao.findAll();

	}

	@Override
	public TrainingRegister findTrainingRegisterById(String id) {
		return trainingRegisterDao.findById(id);
	}

	@Override
	public void updateTrainingRegister(TrainingRegister t) {

		session = sessionFactory.openSession();
		em = session.load(TrainingRegister.class, t.getTrRegCode());
		Transaction tx = session.beginTransaction();
		em = (TrainingRegister) session.merge(t);
		tx.commit();

	}

	@Override
	public void removeTrainingRegister(String id) {
		this.trainingRegisterDao.delete(id);

	}

}
