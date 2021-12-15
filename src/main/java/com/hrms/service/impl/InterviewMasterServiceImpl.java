package com.hrms.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.InterviewMaster;
import com.hrms.repository.InterviewMasterDao;
import com.hrms.service.InterviewMasterService;

@Service
public class InterviewMasterServiceImpl implements InterviewMasterService{

	@Autowired SessionFactory sessionFactory;
	@Autowired InterviewMasterDao interviewMasterDao;
	
	@Override
	public void addInterviewMaster(InterviewMaster interviewMaster) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			interviewMaster.setInterviewCode(interviewMasterDao.getMaxId("INT"));
			session.save(interviewMaster);
			tx.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		
		
	}

	@Override
	public List<InterviewMaster> getAllInterviewMaster() {
		
		return interviewMasterDao.findAll();
	}

	@Override
	public void interviewFinalapproval(String applicantCode, String interviewCode, String finalApprovalStatus) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			InterviewMaster im = session.find(InterviewMaster.class, interviewCode);
			im.setSelectionStatus(finalApprovalStatus);
			
			session.merge(im);
			tx.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}

	}

	@Override
	public List<InterviewMaster> getFinalSelection() {
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<InterviewMaster> result = null;
		try {
			
			tx = session.beginTransaction();
			Query<InterviewMaster> query = session.createQuery("from InterviewMaster i where i.selectionStatus"
					+ "=:status", InterviewMaster.class);
			query.setParameter("status", "Selected");
			
			tx.commit();
			result = query.getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return result;
	}

	@Override
	public InterviewMaster findinterviewMasterById(String interviewCode) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			
			tx = session.beginTransaction();
			Query<InterviewMaster> query = session.createQuery("from InterviewMaster e where "
					+ "e.interviewCode = :interviewCode", 
					InterviewMaster.class);
			query.setParameter("interviewCode", interviewCode);
			
			tx.commit();
			return query.getSingleResult();
		}catch(Exception e) {
			System.out.println("========error block");
			e.printStackTrace();
		}finally {
			session.close();
		}
		return null;
	}

	
}
