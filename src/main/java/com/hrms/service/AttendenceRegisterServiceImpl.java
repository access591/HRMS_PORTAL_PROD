package com.hrms.service;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.AttendenceRegister;
import com.hrms.model.MedicalReimbursement;
import com.hrms.repository.AttendenceRegisterDao;

@Service
public class AttendenceRegisterServiceImpl implements AttendenceRegisterService{

	@Autowired SessionFactory sessionFactory;
	@Autowired AttendenceRegisterDao attendenceRegisterDao;
	@Override
	public List<AttendenceRegister> findAttendenceByEmpCodeBetweenDate(String empCode,Date fromDate,Date toDate) {
		
		try {
			Session session = sessionFactory.openSession();
			Query<AttendenceRegister> query = session.createQuery("from AttendenceRegister a inner join fetch a.employee e "
					+ "inner join fetch a.department d where e.empCode = :empCode "
					+ "and a.timeIn = :fromDate and a.timeOut =:toDate", AttendenceRegister.class);
			query.setParameter("empCode", empCode);
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
			List<AttendenceRegister> result = query.getResultList();
			System.out.println("result : "+ result.size());
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<AttendenceRegister> findAttendenceByDeptBetweenDate(String deptCode, Date FromDate, Date toDate) {
		try {
			Session session = sessionFactory.openSession();
			Query<AttendenceRegister> query = session.createQuery("from AttendenceRegister a inner join fetch a.employee e "
					+ "inner join fetch a.department d where d.departmentCode = :deptCode "
					+ "and a.timeIn = :fromDate and a.timeOut =:toDate", AttendenceRegister.class);
			query.setParameter("deptCode", deptCode);
			query.setParameter("fromDate", FromDate);
			query.setParameter("toDate", toDate);
			List<AttendenceRegister> result = query.getResultList();
			System.out.println("result : "+ result.size());
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<AttendenceRegister> findAllAttendenceBetweenDate(Date FromDate, Date toDate) {
		try {
			Session session = sessionFactory.openSession();
			Query<AttendenceRegister> query = session.createQuery("from AttendenceRegister a inner join fetch a.employee e "
					+ "inner join fetch a.department d "
					+ "where a.attendenceDate >= :fromDate and a.attendenceDate <=:toDate", AttendenceRegister.class);
			
			query.setParameter("fromDate", FromDate);
			query.setParameter("toDate", toDate);
			List<AttendenceRegister> result = query.getResultList();
			System.out.println("result : "+ result.size());
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean addAttendenceRegister(AttendenceRegister attn) {
		Session s=sessionFactory.openSession();
		s.beginTransaction();
		s.save(attn);
		s.getTransaction().commit();
		s.clear();
		s.close();
		return true;
	}

	@Override
	public List<AttendenceRegister> getAllAttendenceRegister() {
		// TODO Auto-generated method stub
		return attendenceRegisterDao.findAll();
	}


	public AttendenceRegister findAttendenceRegisterByEmpCode(String empCode) {
		
		try {
			Session session = sessionFactory.openSession();
			Query<AttendenceRegister> query = session.createQuery("from AttendenceRegister a inner join fetch a.employee e "
					+ "where e.empCode = :empCode", AttendenceRegister.class);
			
			query.setParameter("empCode", empCode);
			
			AttendenceRegister result = query.getSingleResult();
			System.out.println("result : ");
			return result;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<AttendenceRegister> findAttendenceByEmpStatusAbsent(String empCode) {
		try {
			Session session = sessionFactory.openSession();
			Query<AttendenceRegister> query = session.createQuery("from AttendenceRegister a inner join fetch a.employee e "
					+ "where e.empCode = :empCode and a.status= :status", AttendenceRegister.class);
			
			query.setParameter("empCode", empCode);
			query.setParameter("status", "Absent");
			
			List<AttendenceRegister> result = query.getResultList();
			System.out.println("result : "+result.size());
			return result;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	return null;
	}

	public void removeAttendanceRegister(long id) {
		Session session = sessionFactory.openSession();
		Object o = session.get(AttendenceRegister.class, id);
		AttendenceRegister e = (AttendenceRegister) o;
		Transaction tx = session.beginTransaction();
		session.delete(e);
		tx.commit();
		session.close();
		
	

	}

	@Override
	public List<AttendenceRegister> findAttendenceStatusByDeptCode(String deptCode, Date fromDate, Date toDate) {
		
		try {
			
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			Query<AttendenceRegister> query = session.createQuery("from AttendenceRegister a inner join fetch a.department e "
					+ "where e.departmentCode = :deptCode and a.status= :status and a.attendenceDate>=:fromDate and "
					+ "a.attendenceDate<=:toDate", AttendenceRegister.class);
			query.setParameter("status", "Y");
			query.setParameter("deptCode", deptCode);
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);

			List<AttendenceRegister> result = query.getResultList();
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<AttendenceRegister> findTodayAttendenceList() {
		
		java.util.Date date = new java.util.Date();
		java.sql.Date todayDate = new Date(date.getTime());
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<AttendenceRegister> result = null;
		
		try {
			tx = session.beginTransaction();
			Query<AttendenceRegister> query = session.createQuery("from AttendenceRegister a where "
					+ "a.attendenceDate = :todayDate and a.status=:status",AttendenceRegister.class);
			query.setParameter("todayDate", todayDate);
			query.setParameter("status", "Y");
			result = query.getResultList();
			tx.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return result;
	}

	@Override
	public List<AttendenceRegister> findTodayLeaveEmployee() {
		java.util.Date date = new java.util.Date();
		java.sql.Date todayDate = new Date(date.getTime());
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<AttendenceRegister> result = null;
		
		try {
			tx = session.beginTransaction();
			Query<AttendenceRegister> query = session.createQuery("from AttendenceRegister a where "
					+ "a.attendenceDate = :todayDate and a.status=:status",AttendenceRegister.class);
			query.setParameter("todayDate", todayDate);
			query.setParameter("status", "L");
			result = query.getResultList();
			tx.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return result;
	}

	@Override
	public AttendenceRegister findByIdAttendenceRegister(long id) {
		
		return attendenceRegisterDao.findById(id);
	}

	
	

}

