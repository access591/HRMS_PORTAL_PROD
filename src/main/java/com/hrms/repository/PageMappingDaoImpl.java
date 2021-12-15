package com.hrms.repository;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.type.StringType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.UrlDetail;

@Repository
public class PageMappingDaoImpl extends AbstractGenericDao<UrlDetail>implements PageMappingDao {
	@Resource(name = "sessionFactory")
	SessionFactory sessionFactory;
	private Logger logger = LoggerFactory.getLogger(PageMappingDaoImpl.class.getName());

	@Override
	public String pageRequestMapping(String requestMpping, int id) {
		String pagname = null;
		try {
			Session session = this.sessionFactory.getCurrentSession();
		
			SQLQuery query = session.createSQLQuery(
					"select PAGE_NAME from URL_DTL where URL_ID=" + id + " and REQ_MAPPING='" + requestMpping + "'")
					.addScalar("PAGE_NAME", new StringType());
			List rows = query.list();
		
			pagname = rows.get(0).toString();
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return pagname;

	}

	
}
