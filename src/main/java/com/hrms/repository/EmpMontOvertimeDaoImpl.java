package com.hrms.repository;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.EmpMonOvertime;
@Repository
public class EmpMontOvertimeDaoImpl extends AbstractGenericDao<EmpMonOvertime> implements EmpMontOvertimeDao{

	@Override
	public EmpMonOvertime checkMonthOverTimeExists(String empCode) {
		EmpMonOvertime deptName = null;
		try {
			
			Criteria criteria = getSession().createCriteria(EmpMonOvertime.class);
			deptName = (EmpMonOvertime) criteria.setFetchMode("employee.empCode", FetchMode.SELECT)
					.add(Restrictions.eq("employee.empCode",empCode )).uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return deptName;
	}

}
