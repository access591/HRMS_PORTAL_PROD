package com.hrms.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.LocalConvyenceDetail;
import com.hrms.model.TourPlanDetails;
import com.hrms.repository.LocalConvyenceDetailDao;

@Service
public class LocalConvyenceDetailServiceImpl implements LocalConvyenceDetailService{
	
	  @Autowired LocalConvyenceDetailDao localConvyenceDetailDao;
	 

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public boolean addTourPlanDetail(LocalConvyenceDetail localConvyenceDetail) {
		
		
		Session s=sessionFactory.openSession();
		s.beginTransaction();
		
		// TODO Auto-generated method stub
		s.save(localConvyenceDetail);
		s.getTransaction().commit();
		s.clear();
		s.close();
		return true;
	}

	@Override
	public List<LocalConvyenceDetail> findLocalConvyenceDetailByEmpCode(String empCode) {
		
		try {
			Session session = sessionFactory.openSession();
			Query<LocalConvyenceDetail> query = session.createQuery("from LocalConvyenceDetail lcd inner join "
					+ "fetch lcd.localConvId lc inner join fetch lcd.empCode e where e.empCode = :empCode", LocalConvyenceDetail.class);
			query.setParameter("empCode", empCode);
			
			List<LocalConvyenceDetail> listLocalConvyenceDetail = query.getResultList();
			System.out.println("listLocalConvyenceDetail : "+ listLocalConvyenceDetail.size());
			return listLocalConvyenceDetail;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<LocalConvyenceDetail> findAllLocalConvyenceDetail() {
		try {
			Session session = sessionFactory.openSession();
			Query<LocalConvyenceDetail> query = session.createQuery("from LocalConvyenceDetail", LocalConvyenceDetail.class);
			
			
			List<LocalConvyenceDetail> listLocalConvyenceDetail = query.getResultList();
			System.out.println("listLocalConvyenceDetail : "+ listLocalConvyenceDetail.size());
			return listLocalConvyenceDetail;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
