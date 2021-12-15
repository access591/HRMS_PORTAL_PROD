package com.hrms.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.commons.compress.utils.Lists;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.hrms.model.Category;
import com.hrms.model.Employee;
import com.hrms.util.EmployeeUtil;
import com.hrms.repository.EmployeeDao;
import com.hrms.service.CategoryService;
import com.hrms.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDao employeeDao;
	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	CategoryService categoryService;

	@Modifying
	@Override
	public Employee addEmployee(Employee employee) {
		
		System.out.println("=============>"+employeeDao.getMaxId("EMP"));
		employee.setEmpCode(employeeDao.getMaxId("EMP"));

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Employee result = null;

		try {
			tx = session.beginTransaction();
			result = (Employee) session.merge(employee);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;

	}

	@Override
	public List<Employee> getAllEmployees() {

		List<Employee> listEmployee = employeeDao.findAll();
		return listEmployee;
	}

	@Override
	public Employee findEmployeeById(String id) {

		return employeeDao.findById(id);
	}

	@Override
	public void updateEmployee(Employee e) {

		this.employeeDao.saveOrUpdate(e);

	}

	@Override
	public void removeEmployeet(String id) {
		this.employeeDao.delete(id);

	}

	@Override
	public List<Employee> getEmployeeByDeptCode(String deptCode) {
		return this.employeeDao.getEmployeeByDeptCode(deptCode);
	}

	public List<EmployeeUtil> getAllEmployeesAndArms() {
		List<EmployeeUtil> allEmployeesAndArms = employeeDao.getAllEmployeesAndArms();
		return allEmployeesAndArms;
	}

	public List<Employee> getEmployeeByCategoryCode(String categoryCode) {

		return this.employeeDao.getEmployeeByCategoryCode(categoryCode);

	}

	@Override
	public List<Employee> findByDateOfJoiningMonth(int month) {
		System.out.println("Service methosd calling");
		return this.employeeDao.findByDateOfJoiningMonth(month);
	}

	@Override
	public List<Employee> findByDepartmentCode(String deptCode) {

		return this.employeeDao.findByDepartmentCode(deptCode);
	}

	@Override
	public List<Employee> findByempCode(String empCode) {

		return employeeDao.findByIdList(empCode);
	}

	@Override
	public Map<String, Long> countRecordByCategory() {

		Session session = sessionFactory.openSession();
		Transaction tx = null;

		Map<String, Long> task4List = new HashMap();

		try {
			tx = session.beginTransaction();

			String category = "select e.category from Employee e where e.category.categoryName=IPS and "
					+ "e.category.categoryName='DDA' group by e.category";
			
			Query<Category> query = session.createQuery("select e.category from Employee e "
					+ "where e.category.categoryName=:ips or e.category.categoryName=:dda"
					+ " group by e.category", 
					
					Category.class);
			
			query.setParameter("ips", "IPS");
			query.setParameter("dda", "DDA");
			
			
			
			//query.setParameter("categoryName1", "IPS");
			//query.setParameter("categoryName2", "DDA");
			List<Category> categoryList = query.getResultList();

			String count = "SELECT  COUNT(category) AS counter FROM Employee e where e.category.categoryName='IPS'"
					+ "or e.category.categoryName='DDA' GROUP BY category";
			Query query1 = session.createQuery(count);
			List<Long> countList = query1.list();

			for (Category c : categoryList) {

				System.out.println("category id : "+ c.getCategoryCode());
				
				Category cat = categoryService.findCategoryByCatId(c.getCategoryCode());

				task4List.put(cat.getCategoryName(), countList.get(categoryList.indexOf(c)));

			}

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return task4List;
	}

	@Override
	public List<Employee> findAllEmployee() {

		Session session = sessionFactory.openSession();

		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
		Root<Employee> rootEntry = cq.from(Employee.class);
		CriteriaQuery<Employee> all = cq.select(rootEntry);

		TypedQuery<Employee> allQuery = session.createQuery(all);

		return allQuery.getResultList();
		// return null;
	}

	@Override
	public Employee findEmployeeByEmpCode(String empCode) {

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Employee result = null;

		try {
			tx = session.beginTransaction();
			Query<Employee> query = session.createQuery("from Employee e where e.empCode" + "=:empCode",
					Employee.class);
			query.setParameter("empCode", empCode);
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
	public Set<Employee> findAllEmployeeDescOrder() {

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Set<Employee> result = null;

		try {

			tx = session.beginTransaction();

//			Select f from Foo as f order by f.id desc";

			Query<Employee> query = session.createQuery("Select f from Employee as f order by f.empCode",
					Employee.class);
			List<Employee> list = query.getResultList();

			result = new HashSet<>(list);

			Iterator<Employee> i = list.iterator();

			while (i.hasNext()) {
				System.out.println("==========>" + i.next().getEmpCode());
			}

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public List<Employee> findPaginated(int pageNo, int pageSize) {

		int paginatedCount = 0;
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		
		List<Employee> result = null;
		
		try {
			
			tx = session.beginTransaction();
			Query<Employee> query = session.createQuery("from Employee e", Employee.class);
			query.setFirstResult(pageNo);
			query.setMaxResults(pageSize);
			
			result = query.getResultList();
			
			if(result != null) {
				paginatedCount = result.size();
				System.out.println("Total Results: " + paginatedCount);
				for(Employee empl : result) {
					System.out.println("Retrieved Product using Query. Name: " + empl.getEmpName());
				}
			}
			
			
			
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}finally {
			session.close();
		}
//		return paginatedCount;
		
		return result;
				
	}

}
