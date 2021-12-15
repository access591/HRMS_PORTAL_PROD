package com.hrms.dao;
import java.lang.reflect.ParameterizedType;
import java.sql.ResultSet;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;


public class AbstractGenericDao<E> implements GenericDao<E> {
	private final Class<E> entityClass;
	 @Autowired
	 private JdbcTemplate jdbcTemplate;
	public AbstractGenericDao() {
		this.entityClass = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	protected Criteria createEntityCriteria(){
		return getSession().createCriteria(entityClass);
	}

	@Override
	public List<E> findAll() {

		return getSession().createCriteria(this.entityClass).list();
	}

	@Override
	public E findById(String id) {
		return  getSession().get(this.entityClass, id);

	}
	@Override
	public void save(E entity) {
		Transaction tx = getSession().beginTransaction();
		getSession().save(entity);
		tx.commit();
		
	}
	@Override
	public void saveOrUpdate(E entity) {

		Transaction tx = getSession().beginTransaction();
		getSession().saveOrUpdate(entity);
		tx.commit();
	}

	@Override
	public void delete(String id) {
		Transaction tx = getSession().beginTransaction();
		getSession().delete(getSession().load(this.entityClass,id));
		tx.commit();

	}

	@Override
	public E existOrNot(E obj) {
		
		return null;
	}

	@Override
	public E findById(long id) {
		return  getSession().get(this.entityClass, id);

	}

	@Override
	public void delete(long id) {
		Transaction tx = getSession().beginTransaction();
		getSession().delete(getSession().load(this.entityClass, (id)));
		tx.commit();

	}

	@Override
	public String getMaxId(String incId) {
		
		String sql="select FN_PRGM_CODE('"+incId+"') incId";
		return jdbcTemplate.query(sql, new ResultSetExtractor<String>() {
			String incId;
		public String extractData(ResultSet rs)  {
			try {
				if (rs.next())
				{
					incId=rs.getString("incId");
				}
			
			} catch (Exception e) {
				e.printStackTrace();
				
			}
		
				return incId; 
				
		}
	});

		
	}


	

}
