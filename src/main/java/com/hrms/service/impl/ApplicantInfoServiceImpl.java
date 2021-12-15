package com.hrms.service.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.hrms.model.ApplicantInfo;
import com.hrms.repository.ApplicantInfoDao;
import com.hrms.service.ApplicantInfoService;

@Service
public class ApplicantInfoServiceImpl implements ApplicantInfoService {

	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	ApplicantInfoDao applicantInfoDao;

	@Override
	@Modifying
	public void addApplicantInfo(ApplicantInfo applicantInfo) {

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			applicantInfo.setApplicantCode(this.applicantInfoDao.getMaxId("APP"));
			session.save(applicantInfo);
			tx.commit();
		} catch (HibernateException e) {
			
			e.printStackTrace();

		} finally {
			session.close();
		}

	}

	@Override
	public List<ApplicantInfo> getAllApplicantInfo() {

		List<ApplicantInfo> list = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Query<ApplicantInfo> query = session.createQuery("From ApplicantInfo", ApplicantInfo.class);
			list = query.getResultList();
			tx.commit();
		} catch (HibernateException e) {
			
			e.printStackTrace();

		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public ApplicantInfo getApplicantInfoByApplicantCode(String applicantCode) {

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		ApplicantInfo result = null;
		try {

			tx = session.beginTransaction();
			Query<ApplicantInfo> query = session
					.createQuery("From ApplicantInfo a where a.applicantCode = :applicantCode", ApplicantInfo.class);
			query.setParameter("applicantCode", applicantCode);
			result = query.uniqueResult();
			tx.commit();

		} catch (HibernateException e) {
			
			e.printStackTrace();
		} finally {
			session.close();
		}

		return result;
	}

	@Override
	public void updateApplicantInfoInterviewStatus(String applicantCode, String interviewStatus) {

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {

			tx = session.beginTransaction();
			ApplicantInfo applicantInfo = session.find(ApplicantInfo.class, applicantCode);
			applicantInfo.setInterStatus(interviewStatus);
			session.merge(applicantInfo);
			tx.commit();

		} catch (Exception e) {

			System.out.println("error occur in update applicant interview status");
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	@Override
	public List<ApplicantInfo> findApplicantInfoStatusHoldAndPending() {

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<ApplicantInfo> result = null;

		try {
			tx = session.beginTransaction();

			Query<ApplicantInfo> query = session.createQuery(
					"from ApplicantInfo a " + "where a.interStatus = :status1" + " or a.interStatus = :status2",
					ApplicantInfo.class);
			query.setParameter("status1", "Hold");
			query.setParameter("status2", "N");
			result = query.getResultList();
			tx.commit();

		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public List<ApplicantInfo> findApplicantInfoStatusForward() {

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<ApplicantInfo> result = null;

		try {
			tx = session.beginTransaction();
			Query<ApplicantInfo> query = session.createQuery("from ApplicantInfo a where a.interStatus = :status",
					ApplicantInfo.class);
			query.setParameter("status", "Forward");
			result = query.getResultList();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public List<ApplicantInfo> findAllApplicantInfo() {
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<ApplicantInfo> result = null;
		
		try {
			
			tx = session.beginTransaction();
			Query<ApplicantInfo> query = session.createQuery("from ApplicantInfo",ApplicantInfo.class);
			result = query.getResultList();
			tx.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return result;
	}

}
