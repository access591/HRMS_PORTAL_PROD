package com.hrms.service.impl;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.TrainingRequisition;
import com.hrms.repository.TrainingRequisitionDao;
import com.hrms.service.TrainingRequistionService;

@Service
public class TrainingRequisitionServiceImpl implements TrainingRequistionService {

	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	TrainingRequisitionDao trainingRequisitionDao;

	@Override
	public void addTrainingRequisition(TrainingRequisition trainingRequistion) {

		trainingRequistion.setTrReqCode(trainingRequisitionDao.getMaxId("TRN"));
		trainingRequisitionDao.saveOrUpdate(trainingRequistion);

	}

	@Override
	public List<TrainingRequisition> getAllTrainingRequisition() {

		return this.trainingRequisitionDao.findAll();
	}

	@Override
	public void trainingRequisitionApproval(String trReqCode, String status) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			TrainingRequisition tr = session.find(TrainingRequisition.class, trReqCode);
			tr.setTrReqStatus(status);
			session.merge(tr);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	@Override
	public TrainingRequisition findById(String trReqCode) {

		return this.trainingRequisitionDao.findById(trReqCode);
	}

	@Override
	public void updateTrainingRequisition(TrainingRequisition trainingRequisition) {

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {

			tx = session.beginTransaction();
			TrainingRequisition tr = session.find(TrainingRequisition.class, trainingRequisition.getTrReqCode());
			tr.getListTransactionReqEmployeeDetail().clear();
			tr.getListTransactionRequisitionDetail().clear();

			tr.getListTransactionReqEmployeeDetail().addAll(trainingRequisition.getListTransactionReqEmployeeDetail());
			tr.getListTransactionRequisitionDetail().addAll(trainingRequisition.getListTransactionRequisitionDetail());

			session.merge(trainingRequisition);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	@Override
	public List<TrainingRequisition> findTrainingRequisitionByStatusY() {

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<TrainingRequisition> query = session.createQuery(
					"from TrainingRequisition tr where " + "tr.trReqStatus = :status", TrainingRequisition.class);
			query.setParameter("status", "Y");
			
			tx.commit();
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return Collections.emptyList();
	}

	@Override
	public List<TrainingRequisition> findTrainingRequisitionByStatusYAndC() {
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<TrainingRequisition> query = session.createQuery(
					"from TrainingRequisition tr where " + "tr.trReqStatus = :status1 or tr.trReqStatus=:status2",
					TrainingRequisition.class);
			query.setParameter("status1", "N");
			query.setParameter("status2", "N");
			
			tx.commit();
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return Collections.emptyList();
	}

	@Override
	public void removeTrainingRequisition(String trainingRequisitionId) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			Object o = session.get(TrainingRequisition.class, trainingRequisitionId);
			TrainingRequisition e = (TrainingRequisition) o;

			session.delete(e);
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}

	}

}
