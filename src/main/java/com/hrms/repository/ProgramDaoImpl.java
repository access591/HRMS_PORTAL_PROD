package com.hrms.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.Program;



@Repository
public class ProgramDaoImpl extends AbstractGenericDao<Program> implements ProgramDao {
	@Autowired SessionFactory sessionFactory;
	Session session=null;
	Query query =null;
	


	@Override
	public List<Program> findByProgramCodeSubModule(String subModCode, String moduleCode) {
		
		

		System.out.println("subModuleAll:::::::::::::::::::::::::::"+subModCode);
		System.out.println("Module All:::::::::::::::::::::::::::"+moduleCode);
		System.out.println();
		session = this.sessionFactory.getCurrentSession();
		
		
		
		  if(subModCode.equals("ALL") &&moduleCode.equals("ALL") )
		  { 
			  query =session.createQuery("from Program p where p.activeYn=:status ");
		  query.setParameter("status", "Y"); List<Program> programListt = query.list();
		
		  return programListt;
		  
		  }
		 
		
		 if (subModCode.equals("ALL") && moduleCode!=null){
			query = session.createQuery("from Program s where  s.pModuleCode.moduleCode= :moduleCode and s.activeYn=:status");
			query.setParameter("moduleCode", moduleCode);
			query.setParameter("status", "Y");
			List<Program> programListm = query.list();
			
			return programListm;
		}
		
		
		
		else
		{
		query = session.createQuery("from Program s where s.subModuleCode.subModuleCode= :subModCode and s.activeYn=:status");
		query.setParameter("subModCode", subModCode);
		query.setParameter("status", "Y");
		query.setParameter("status", "Y");
		List<Program> programList = query.list();
		
		return programList;
		
		}
	
	
		
	}



	@Override
	public Program checkProgramExists(Program program) {
		Program prgName = null;
		try {
			Criteria criteria = getSession().createCriteria(Program.class);
			prgName = (Program) criteria.setFetchMode("PRG_NAME", FetchMode.SELECT)
					.add(Restrictions.eq("programName", program.getProgramName())).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return prgName;
	}

}
