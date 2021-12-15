package com.hrms.repository;
import java.util.List;

import com.hrms.dao.GenericDao;
import com.hrms.model.SubModule;

public interface SubModuleDao extends GenericDao<SubModule>
{

	SubModule checkSubModuleExists(SubModule subModule);

	SubModule checkSubModuleSeqExists(SubModule subModule);

	List<SubModule> findSubModuleByModuleCode(String id);
	
}
