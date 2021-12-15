package com.hrms.repository;


import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.UserRights;
@Repository
public class UserProgramRightDaoImpl extends AbstractGenericDao<UserRights> implements UserProgramRightDao {
	@Autowired SessionFactory sessionFactory;
	@Override
	public UserRights checkUserRightsExists(UserRights userRights) {
		UserRights userCodem = null;
		try {
			
			Criteria criteria = getSession().createCriteria(UserRights.class);
			userCodem = (UserRights) criteria.setFetchMode("PRG_CODE", FetchMode.SELECT)
					.add(Restrictions.eq("userCode", userRights.getUserCode()))
					.add(Restrictions.eq("prgCode.programCode", userRights.getPrgCode().getProgramCode()))
					.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return userCodem;
	}
	}


