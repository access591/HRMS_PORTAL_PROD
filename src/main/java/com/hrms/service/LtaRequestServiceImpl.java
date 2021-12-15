package com.hrms.service;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.LtaRequest;
import com.hrms.repository.LtaRequestDao;
@Service
public class LtaRequestServiceImpl implements LtaRequestService {
@Autowired
LtaRequestDao ltaRequestDao;

	@Autowired SessionFactory sessionFactory;
	
	@Override
	public void addLtaRequest(LtaRequest ltaRequest) {
		ltaRequest.setLtaCode(ltaRequestDao.getMaxId("LTA"));
		this.ltaRequestDao.saveOrUpdate(ltaRequest);
				
	}
	@Override
	public List<LtaRequest> getAllLTARequest() {
		List<LtaRequest> ltaList= ltaRequestDao.findAll() ;
		return ltaList;
	}
	@Override
	public void removeLTAReques(String id) {
		this.ltaRequestDao.delete(id);
		
	}
	@Override
	public LtaRequest findByIdLta(String id) {
				return ltaRequestDao.findById(id);
	}
	@Override
	public void updateLtaRequest(LtaRequest ltaRequest) {
		
		this.ltaRequestDao.saveOrUpdate(ltaRequest);		
	}
	
	
	@Override
	public List<LtaRequest> findAllLtaByEmpCode(String empCode) {
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query<LtaRequest> query = session.createQuery("from LtaRequest l left join fetch "
				+ "l.empCode e where e.empCode = :empCode",LtaRequest.class);
		query.setParameter("empCode", empCode);
		
		List<LtaRequest> listLtaRequest = query.getResultList();
		session.getTransaction().commit();
		return listLtaRequest;
	}
	
	
	@Override
	public List<LtaRequest> findLtaByFromLeaveDateToLeave(Date leaveFrom, Date leaveTo,String empCode) {
		
		Session session = sessionFactory.openSession();
		try {
			
			session.beginTransaction();
			
			Query<LtaRequest> query = session.createQuery("from LtaRequest lt left join fetch lt.empCode e"
					+ " where lt.leaveFrom >=:leaveFrom and lt.leaveTo <= :leaveTo and e.empCode"
					+ "= :empCode",LtaRequest.class);
			query.setParameter("leaveFrom", leaveFrom);
			query.setParameter("leaveTo", leaveTo);
			query.setParameter("empCode", empCode);
			
			List<LtaRequest> listLtaRequest = query.getResultList();
			session.getTransaction().commit();
			
			return listLtaRequest;
		}catch(Exception e) {
			System.out.println("exception occured in findLtaByFromLeaveDateToLeave service");
			e.printStackTrace();
		}
		
		return null;
	}
	@Override
	public List<LtaRequest> findAllLtaByFromLeaveDateToLeave(Date leaveFrom, Date leaveTo) {
		
		Session session = sessionFactory.openSession();
		try {
			
			session.beginTransaction();
			
			Query<LtaRequest> query = session.createQuery("from LtaRequest lt left join fetch lt.empCode e"
					+ " where lt.leaveFrom >=:leaveFrom and lt.leaveTo <= :leaveTo"
						,LtaRequest.class);
			query.setParameter("leaveFrom", leaveFrom);
			query.setParameter("leaveTo", leaveTo);
			
			
			List<LtaRequest> listLtaRequest = query.getResultList();
			session.getTransaction().commit();
			
			return listLtaRequest;
		}catch(Exception e) {
			System.out.println("exception occured in findAllLtaByFromLeaveDateToLeave service");
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<LtaRequest> getAllDistinctLtaRequest() {
		Session session = sessionFactory.openSession();
		try {
			
			session.beginTransaction();
			
			Query<LtaRequest> query = session.createQuery("select distinct lt from LtaRequest lt inner join fetch"
					+ " lt.empCode e"
						,LtaRequest.class);
		
			List<LtaRequest> listLtaRequest = query.getResultList();
			session.getTransaction().commit();
			
			return listLtaRequest;
		}catch(Exception e) {
			System.out.println("exception occured in findAllLtaByFromLeaveDateToLeave service");
			e.printStackTrace();
		}
		return null;
	}

}
