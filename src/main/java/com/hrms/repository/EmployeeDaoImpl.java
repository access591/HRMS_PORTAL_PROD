package com.hrms.repository;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.Employee;
import com.hrms.util.EmployeeUtil;
import com.hrms.model.Module;

@Repository
public class EmployeeDaoImpl extends AbstractGenericDao<Employee> implements EmployeeDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<Employee> getEmployeeByDeptCode(String deptCode) {

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<Employee> result = null;

		try {
			tx = session.beginTransaction();

			session = this.sessionFactory.getCurrentSession();
			Query<Employee> query = session.createQuery("from Employee e where e.department.departmentCode = :deptCode",
					Employee.class);
			query.setParameter("deptCode", deptCode);

			result = query.list();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	@Override

	public List<EmployeeUtil> getAllEmployeesAndArms() {
		List employeeUtils = null;
		String sql = null;
		try {
			Session session = this.sessionFactory.getCurrentSession();
			sql = " SELECT  DISTINCT  e.EMPLOYEE_CODE,a.ARMS_CODE,e.EMPLOYEE_NAME,e.CATEGORY_CODE,e.DEPARTMENT_CODE,e.DESIGNATION_CODE,e.EMP_IMG\r\n"
					+ "FROM M_EMPLOYEE e ,ARMS_LICENSE_DETAILS a where e.EMPLOYEE_CODE= a.EMPLOYEE_CODE";

			SQLQuery query = getSession().createSQLQuery(sql);
			employeeUtils = query.list();
			return employeeUtils;
		} catch (HibernateException e) {

			e.printStackTrace();
		}
		return null;
	}

	public List<Employee> getEmployeeByCategoryCode(String categoryCode) {

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<Employee> result = null;

		try {
			tx = session.beginTransaction();

			Query<Employee> query = session.createQuery("from Employee e where e.category.categoryCode = :categoryCode ",
					Employee.class);
			query.setParameter("categoryCode", categoryCode);
			result = query.list();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public List<Employee> findByDateOfJoiningMonth(int month) {

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<Employee> result = null;

		try {
			tx = session.beginTransaction();
			Query<Employee> query = session
					.createQuery("SELECT e FROM Employee e WHERE MONTH(e.doj) = :month ", Employee.class);
			query.setParameter("month", month);
			result = query.list();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return result;
	}

	@Override
	public List<Employee> findByDepartmentCode(String deptCode) {

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<Employee> result = null;

		try {
			tx = session.beginTransaction();
			Query<Employee> query = session.createQuery("from Employee e where e.department.departmentCode = :deptCode ",
					Employee.class);
			query.setParameter("deptCode", deptCode);
			result = query.list();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public List<Employee> findByIdList(String empCode) {

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<Employee> result = null;

		try {
			tx = session.beginTransaction();
			Query<Employee> query = session.createQuery("from Employee e where e.empCode = :empCode ", Employee.class);
			query.setParameter("empCode", empCode);
			result = query.getResultList();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

}
