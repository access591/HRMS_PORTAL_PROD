package com.hrms.repository;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.type.StringType;
import com.hrms.dao.AbstractGenericDao;

import com.hrms.model.Module;
import com.hrms.model.Program;
import com.hrms.model.SubModule;

@Repository
public class ModuleDaoImpl extends AbstractGenericDao<Module> implements ModuleDao {
	private Logger logger = LoggerFactory.getLogger(ModuleDaoImpl.class.getName());
	@Autowired SessionFactory sessionFactory;


	@Override
	public Module findModule(Module module) {
		Module mcode = null;
		try {

			Criteria criteria = getSession().createCriteria(Module.class);
			mcode = (Module) criteria.setFetchMode("M_MODULE", FetchMode.SELECT)
					.add(Restrictions.eq("moduleCode", module.getModuleCode())).uniqueResult();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		}

		return mcode;
	}

	@Override
	public List<Module> getAllModulesList(String userCode) {

		List modules = null;
		try {
			
			
			  String sql =
			  "SELECT  DISTINCT u.MODULE_CODE,m.MODULE_NAME,m.ACTIVE_YN,m.INS_BY,m.INS_DATE,m.UPDATE_BY,m.UPDATE_DATE,m.SEQ_NO  \r\n"
			  + "FROM M_MODULE m ,M_URIGHTS u\r\n" +
			  "Where m.MODULE_CODE = u.MODULE_CODE\r\n" +
			  " and m.ACTIVE_YN ='Y' AND u.ACTIVE_YN ='Y'\r\n" + " and u.USER_CODE ='" +
			  userCode + "'" + " ORDER BY m.SEQ_NO";
			  
			  SQLQuery query = getSession().createSQLQuery(sql); query.addEntity(Module.class);
			  modules = query.list();
			  	
				
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return modules;
	}

	@Override
	public List<SubModule> getAllSubModule(String modulecCode, String ucode) {
		List subMOdules = null;
		try {
		
			String sql = "SELECT DISTINCT u.SUB_MODULE_CODE,S.INS_DATE,S.INS_BY,S.ACTIVE_YN,S.SUB_MODULE_NAME,S.MODULE_CODE,S.SEQ_NO,S.UPDATE_BY,S.UPDATE_DATE   \r\n"
					+ "FROM M_MODULE m , M_URIGHTS u ,M_SUB_MODULE S "
					+ "Where m.MODULE_CODE = u.MODULE_CODE\r\n" + " AND u.MODULE_CODE =S.MODULE_CODE\r\n"
					+ " and u.SUB_MODULE_CODE =S.SUB_MODULE_CODE\r\n"
					+ " and m.ACTIVE_YN ='Y' AND u.ACTIVE_YN ='Y' and S.ACTIVE_YN ='Y'\r\n" + " and u.USER_CODE ='"
					+ ucode + "'" + " and S.MODULE_CODE ='" + modulecCode + "'" + " ORDER BY S.SEQ_NO";

			SQLQuery query = getSession().createSQLQuery(sql);
			query.addEntity(SubModule.class);
			subMOdules = query.list();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return subMOdules;
	}

	@Override
	public List<Program> getAllProgramList(String moduleCode, String smCode, String userCode) {
		List programs = null;
		try {

			String sql = "SELECT  DISTINCT DISTINCT  u.PRG_CODE,p.PRG_NAME,p.MODULE_CODE,p.PRG_TYPE,p.PRG_HREF_NAME,p.ACTIVE_YN,p.INS_BY,p.INS_DATE,p.UPDATE_BY,p.UPDATE_DATE,p.SUB_MODULE_CODE,p.SEQ_NO \r\n"
					+ " FROM M_MODULE m , M_URIGHTS u ,M_SUB_MODULE s ,M_PROGRAM p"
					+ "  Where m.MODULE_CODE = u.MODULE_CODE\r\n" + "  AND u.MODULE_CODE =s.MODULE_CODE\r\n"
					+ "  and u.SUB_MODULE_CODE =s.SUB_MODULE_CODE\r\n" + "  and u.MODULE_CODE =p.MODULE_CODE\r\n"
					+ "  and u.SUB_MODULE_CODE =p.SUB_MODULE_CODE\r\n" + "  and u.PRG_CODE =p.PRG_code\r\n"
					+ "  and m.ACTIVE_YN ='Y' AND u.ACTIVE_YN ='Y' and s.ACTIVE_YN ='Y' and p.ACTIVE_YN ='Y'\r\n"
					+ "  and u.USER_CODE ='" + userCode + "'" + "and u.MODULE_CODE ='" + moduleCode + "'"
					+ "and  u.SUB_MODULE_CODE ='" + smCode + "'" + " ORDER BY p.SEQ_NO ";

			SQLQuery query = getSession().createSQLQuery(sql);
			query.addEntity(Program.class);
			programs = query.list();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return programs;
	}

	@Override
	public Module checkModuleExists(Module module) {
		Module moduleName = null;
		try {
			
			Criteria criteria = getSession().createCriteria(Module.class);
			moduleName = (Module) criteria.setFetchMode("MODULE_NAME", FetchMode.SELECT)
					.add(Restrictions.eq("moduleName", module.getModuleName())).uniqueResult();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return moduleName;
	}

	@Override
	public Module checkModuleSeqExists(Module module) {
		
		Module seqNo = null;
		try {
			
			Criteria criteria = getSession().createCriteria(Module.class);
			seqNo = (Module) criteria.setFetchMode("SEQ_NO", FetchMode.SELECT)
					.add(Restrictions.eq("seqNo", module.getSeqNo())).uniqueResult();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return seqNo;
	}
	@Override
	public List<Module> getActiveModules() {
		Query querym =null;
	
		try {
			querym =getSession().createQuery("from Module s where  s.active=:status");
			
			querym.setParameter("status", "Y");
			List<Module> moduleList = querym.list();

			return moduleList;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}

}
