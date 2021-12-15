package com.hrms.repository;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;

import com.hrms.model.Register;

@Repository
public class RegisterDaoImpl extends AbstractGenericDao<Register> implements RegisterDao {

	@Override
	public Register checkRegisterExists(Register register) {

		Register registerName = null;
		try {

			Criteria criteria = getSession().createCriteria(Register.class);
			registerName = (Register) criteria.setFetchMode("DESCRIPTION", FetchMode.SELECT)
					.add(Restrictions.eq("descriptionReg", register.getDescriptionReg())).uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return registerName;

	}

}
