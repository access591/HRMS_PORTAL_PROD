package com.hrms.repository;

import com.hrms.dao.GenericDao;
import com.hrms.model.Award;

public interface AwardDao extends GenericDao<Award> {

	Award checkAwardExists(Award award);

}
