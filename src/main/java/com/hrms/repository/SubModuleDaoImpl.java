package com.hrms.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.State;
import com.hrms.model.SubModule;

@Repository
public class SubModuleDaoImpl extends AbstractGenericDao<SubModule> implements SubModuleDao {
	private Logger logger = LoggerFactory.getLogger(SubModuleDaoImpl.class.getName());
	@Autowired SessionFactory sessionFactory;
	Session session=null;
	Query query =null;
	
	@Override
	public SubModule checkSubModuleExists(SubModule subModule) {
		SubModule subModuleName = null;
		try {
			
			Criteria criteria = getSession().createCriteria(SubModule.class);
			subModuleName = (SubModule) criteria.setFetchMode("SUB_MODULE_NAME", FetchMode.SELECT)
					.add(Restrictions.eq("subModuleName", subModule.getSubModuleName())).uniqueResult();

		} catch (Exception e) {
			logger.info(e.getMessage());
		}

		return subModuleName;
	}
	@Override
	public SubModule checkSubModuleSeqExists(SubModule subModule) {

		
		
		SubModule subsq = null;
		try {
			
			Criteria criteria = getSession().createCriteria(SubModule.class);
			subsq = (SubModule) criteria.setFetchMode("SEQ_NO", FetchMode.SELECT)
					.add(Restrictions.eq("seqNoSubModule", subModule.getSeqNoSubModule())).uniqueResult();

		} catch (Exception e) {
			logger.info( e.getMessage());
		}

		return subsq;
	
	}
	@Override
	public List<SubModule> findSubModuleByModuleCode(String id) {

		session = this.sessionFactory.getCurrentSession();
		if(id.equals("ALL")){
			query = session.createQuery("from SubModule s where s.acitveSubModule=:status");
			
		
		}else {
			query = session.createQuery("from SubModule s where s.moduleCode.moduleCode= :moduleCode and s.acitveSubModule=:status");
			query.setParameter("moduleCode", id);
			
		}
		
		query.setParameter("status", "Y");
	
		List<SubModule> subModuleList = query.list();
	
		return subModuleList;
		
		
		
		
	}
	


}
