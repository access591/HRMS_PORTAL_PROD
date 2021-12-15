package com.hrms.service;

import java.util.List;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.TourPlan;
import com.hrms.repository.TourPlanDao;

@Service
public class TourPlanServiceImpl implements TourPlanService{
	@Autowired
	SessionFactory sessionfactory;
	@Autowired TourPlanDao tourPlanDao;
	Session session=null;
	@Override
	public void addTourPlan(TourPlan tourPlan) {
		
		tourPlan.setTourPlanId(tourPlanDao.getMaxId("TPL"));
		this.tourPlanDao.saveOrUpdate(tourPlan);
		
	}

	@Override
	public List<TourPlan> getAllTourPlan() {
		
		return this.tourPlanDao.findAll();
	}

	
	@Override
	public void updateTourPlan(TourPlan c) {
		this.tourPlanDao.saveOrUpdate(c);
		
	}

	@Override
	public void removeTourPlan(String id) {
		
		 session = sessionfactory.openSession();
		Object o = session.get(TourPlan.class, id);
		TourPlan e = (TourPlan) o;
		Transaction tx = session.beginTransaction();
		session.delete(e);
		tx.commit();
		session.close();
		this.tourPlanDao.delete(id);
		
	}

	@Override
	public TourPlan findByIdTourPlan(String id) {
		return this.tourPlanDao.findById(id);
	}

	
	@Override
	public List<TourPlan> findTourPlanByEmpCode(String empCode) {
		
		 session = sessionfactory.openSession();
		
		try {
			session.beginTransaction();

					
			Query<TourPlan> query  = session.createQuery("from TourPlan t left join fetch t.empCode e "
					+ " inner join fetch t.tourPlanDetail "
					+ "where e.empCode = :empCode",TourPlan.class);
			
			query.setParameter("empCode", empCode);
			List<TourPlan> tr = query.getResultList();
			session.getTransaction().commit();
			session.close();
			System.out.println("list result : " + tr.get(0).getEmpCode().getEmpName());
			System.out.println("list result : " + tr.get(0).getMobNumber());
			return tr;
		}catch(Exception e) {
			System.out.println("tour plan service impl");
			e.printStackTrace();
		}
		
		
		return null;
	}

}
