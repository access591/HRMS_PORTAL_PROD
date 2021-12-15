package com.hrms.service.impl;

import java.io.File;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.hrms.model.ApplicantInfo;
import com.hrms.model.EmployeeUnderRule;
import com.hrms.service.EmployeeUnderRuleService;

@Service
public class EmployeeUnderRuleServiceImpl implements EmployeeUnderRuleService {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public EmployeeUnderRule addEmployeeUnderRule(EmployeeUnderRule employeeUnderRule) {

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		EmployeeUnderRule result = null;
		try {
			tx = session.beginTransaction();
			result = (EmployeeUnderRule) session.merge(employeeUnderRule);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public List<EmployeeUnderRule> getAllEmployeeUnderRule() {

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<EmployeeUnderRule> result = null;

		try {
			tx = session.beginTransaction();
			Query<EmployeeUnderRule> query = session.createQuery("from EmployeeUnderRule", EmployeeUnderRule.class);
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
	public EmployeeUnderRule getEmployeeUnderRuleFindById(Long eod) {

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		EmployeeUnderRule result = null;

		try {
			tx = session.beginTransaction();
			Query<EmployeeUnderRule> query = session.createQuery("from EmployeeUnderRule e where " + "e.eod=:eod",
					EmployeeUnderRule.class);
			query.setParameter("eod", eod);
			result = query.getSingleResult();
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public void deleteEmployeeUnderRule(Long eod) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Object o = session.get(EmployeeUnderRule.class, eod);
			EmployeeUnderRule e = (EmployeeUnderRule) o;

			File file = new File(e.getUnderRuleFile());
			File saveFileFolder = new ClassPathResource("static/img/").getFile();
			
			System.out.println("file path : "+ saveFileFolder+file.getName());
			System.out.println("========start========");
			
			String f = new File(saveFileFolder.toString()).getAbsolutePath() 
					+ File.separator + e.getUnderRuleFile();
			File file1 = new File(f);
			if(file1.exists()) {
				System.out.println("==========>file exists");
				if(file1.delete()) {
					System.out.println("if block ");
					session.delete(e);
				}else {
					System.out.println("else block");
				}
			}
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	@Override
	public void updateEmployeeUnderRule(EmployeeUnderRule employeeUnderRule) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {

			tx = session.beginTransaction();
			session.find(EmployeeUnderRule.class, employeeUnderRule.getEod());
			session.merge(employeeUnderRule);
			tx.commit();

		} catch (Exception e) {

			System.out.println("error occur in update applicant interview status");
			e.printStackTrace();
		} finally {
			session.close();
		}
		
	}

	@Override
	public List<EmployeeUnderRule> getUniqueEmployeeUnderRule() {
		
		List<EmployeeUnderRule> result = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {

			tx = session.beginTransaction();
			
			Query<EmployeeUnderRule> query = session.createQuery("from EmployeeUnderRule e group by e.employee.empCode,e.underRuleType", EmployeeUnderRule.class);
			result = query.getResultList();
			tx.commit();
		} catch (Exception e) {

			System.out.println("error occur in update applicant interview status");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}
	

	@Override
	public List<EmployeeUnderRule> findEmployeeUnderRuleByEmpCodeAndEmpType(String empCode, String empType) {
		
		List<EmployeeUnderRule> result = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Query<EmployeeUnderRule> query = session.createQuery("from EmployeeUnderRule e where"
					+ " e.employee.empCode = :empCode and e.underRuleType = :empTYpe", EmployeeUnderRule.class);
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
