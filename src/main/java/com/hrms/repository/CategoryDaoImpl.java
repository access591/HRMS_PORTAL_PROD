package com.hrms.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.Category;

@Repository
public class CategoryDaoImpl extends AbstractGenericDao<Category> implements CategoryDao {
	private Logger logger = LoggerFactory.getLogger(CategoryDaoImpl.class.getName());

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Category chaeckCategoryExistOrNot(Category category) {

		Category desName = null;

		try {

			Criteria criteria = getSession().createCriteria(Category.class);
			desName = (Category) criteria.setFetchMode("CATEG_NAME", FetchMode.SELECT)
					.add(Restrictions.eq("categoryName", category.getCategoryName())).uniqueResult();

		} catch (Exception e) {
			logger.info(e.getMessage());
		}

		return desName;
	}

	@Override
	public List<Category> getActiveCategory() {

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<Category> result = null;

		try {

			tx = session.beginTransaction();
			Query<Category> query = session.createQuery("from Category c where c.active=:status and c.categoryName=:IPS"
					+ " or c.categoryName=:DDA", Category.class);
			query.setParameter("status", "Y");
			query.setParameter("IPS", "IPS");
			query.setParameter("DDA", "DDA");
			result = query.getResultList();
			tx.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	/*
	 * @Override public List<Category> getActiveCategory() { Query querym =null; try
	 * { querym
	 * =getSession().createQuery("from Category c where  c.active=:status");
	 * querym.setParameter("status","Y"); List<Category> catList = querym.list();
	 * 
	 * return catList; } catch (Exception e) {
	 * 
	 * logger.info(e.getMessage()); } return null; }
	 */

}
