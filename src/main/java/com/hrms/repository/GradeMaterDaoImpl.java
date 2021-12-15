package com.hrms.repository;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.Grade;

@Repository
public class GradeMaterDaoImpl extends AbstractGenericDao<Grade> implements GradeMaterDao {
	private Logger logger = LoggerFactory.getLogger(GradeMaterDaoImpl.class.getName());

	@Override
	public Grade checkGradeExists(Grade grade) {
		Grade gradeName = null;
		try {

			Criteria criteria = getSession().createCriteria(Grade.class);
			gradeName = (Grade) criteria.setFetchMode("GARDE_NAME", FetchMode.SELECT)
					.add(Restrictions.eq("gardeName", grade.getGardeName())).uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return gradeName;
	
	}

	
	

}
