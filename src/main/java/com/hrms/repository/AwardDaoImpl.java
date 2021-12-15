package com.hrms.repository;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.Award;
/**
 * @author Access surendra
 *
 */
@Repository
public class AwardDaoImpl extends AbstractGenericDao<Award> implements AwardDao {

	@Override
	public Award checkAwardExists(Award award) {
		Award awardName = null;
		try {
			Criteria criteria = getSession().createCriteria(Award.class);
			awardName = (Award) criteria.setFetchMode("AWARD_NAME", FetchMode.SELECT)
					.add(Restrictions.eq("awardName", award.getAwardName())).uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return awardName;
	}

}
