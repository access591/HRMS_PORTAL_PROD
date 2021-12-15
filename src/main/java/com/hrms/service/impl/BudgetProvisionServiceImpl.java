package com.hrms.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.BudgetProvision;
import com.hrms.model.Department;
import com.hrms.service.BudgetProvisionService;
import com.hrms.service.DepartmentService;

@Service
public class BudgetProvisionServiceImpl implements BudgetProvisionService {

	@Autowired
	SessionFactory sessionFactory;
	@Autowired DepartmentService departmentService;

	@Override
	public void saveBudgetProvision(BudgetProvision budgetProvision) {

		Session session = sessionFactory.openSession();
		try {
			
			Transaction tx = session.beginTransaction();

			session.save(budgetProvision);
			tx.commit();
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
	}

	@Override
	public List<BudgetProvision> getAllBudgetProvision() {
		
		Session session = sessionFactory.openSession();
		try  {

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<BudgetProvision> criteria = builder.createQuery(BudgetProvision.class);
			criteria.from(BudgetProvision.class);
			
			return session.createQuery(criteria).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			session.close();
		}
		return Collections.emptyList();
	}

	@Override
	public BudgetProvision findByBudgetProvisionId(Long budgitProvisionId) {

		Session session = sessionFactory.openSession();
		try {
			
			Transaction tx = session.beginTransaction();
			BudgetProvision budgetProvision = session.find(BudgetProvision.class, budgitProvisionId);
			tx.commit();
			session.close();
			return budgetProvision;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return null;
	}

	@Override
	public void updateBudgetProvision(BudgetProvision budgetProvision) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		
		try {
			
			tx = session.beginTransaction();
			session.find(BudgetProvision.class, budgetProvision.getBudgetProvisionId());

			session.merge(budgetProvision);
			tx.commit();
			
		} catch (Exception e) {
			if (tx!=null) 
				tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	
	@Override
	public void removeBudgetProvision(Long budgetId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Object o = session.get(BudgetProvision.class, budgetId);
		BudgetProvision e = (BudgetProvision) o;
		
		session.delete(e);
		tx.commit();
		session.close();
		
	}

	@Override
	public List<BudgetProvision> findBudgetProvisionByDepartment(String deptCode) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<BudgetProvision> query = session.createQuery("from BudgetProvision b "
					+ "inner join fetch b.department d where d.departmentCode = :deptCode", BudgetProvision.class);
			query.setParameter("deptCode", deptCode);
			
			tx.commit();
			
			
			return query.getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return null;
	}

	@Override
	public Map<String, Long> findBudgetTrackDepartment() {
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Map<String, Long> task4List = new HashMap<>();
		
		try {
			tx = session.beginTransaction();
			String departmentQuery = "select b.department from BudgetProvision b group by b.department.departmentCode";
			Query<Department> query = session.createQuery(departmentQuery,Department.class);
			List<Department>  departmentList = query.list();
			System.out.println("departmentQuery ====>"+departmentList.get(0));
			System.out.println("departmentQuery ====>"+departmentList);
			
			String budgetAmtCount = "select sum(expenditureAmount) as counter from BudgetProvision b group by"
					+ " b.department.departmentCode";
			Query<Long> query1 = session.createQuery(budgetAmtCount,Long.class);
			List<Long>  countList = query1.list();


			for (Department deptCode : departmentList) {
				Department department = departmentService.findDepartmentById(deptCode.getDepartmentCode());
				task4List.put("vt"+department.getDeptName(), countList.get(departmentList.indexOf(deptCode)));
			}
			tx.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return task4List;
	}

}
