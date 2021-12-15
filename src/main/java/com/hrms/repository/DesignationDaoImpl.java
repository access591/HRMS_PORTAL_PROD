package com.hrms.repository;




import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;

import com.hrms.model.Designation;

@Repository
public class DesignationDaoImpl extends AbstractGenericDao<Designation>  implements DesignationDao {
	private Logger logger = LoggerFactory.getLogger(DesignationDao.class.getName());
	@Override
	public Designation checkDesignationExists(Designation designation) {
		Designation desName = null;
		
		try {
			
			Criteria criteria = getSession().createCriteria(Designation.class);
			desName = (Designation) criteria.setFetchMode("DESG_NAME", FetchMode.SELECT)
					.add(Restrictions.eq("desgName", designation.getDesgName())).uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return desName;
	}

	
}
