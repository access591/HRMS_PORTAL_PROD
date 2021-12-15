package com.hrms.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.hrms.model.Program;

import com.hrms.repository.ProgramDao;

@Service
public class ProgramServiceImpl implements ProgramService {

	@Autowired
	ProgramDao programDao;
	
	@Autowired SessionFactory sessionFactory;

	@Override
	public List<Program> getAllPrograms() {
		return programDao.findAll();
	}

	@Override
	public void addProgram(Program program) {
		program.setProgramCode(programDao.getMaxId("PRG"));
		programDao.saveOrUpdate(program);
	}

	@Override
	public boolean checkProgramExists(Program program) {

		Program e = programDao.checkProgramExists(program);
		if (e != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Program findProgramById(String id) {

		return programDao.findById(id);
	}

	@Override
	public void updateProgram(Program p) {
		
		this.programDao.saveOrUpdate(p);

	}

	@Override
	public void removeProgram(String id) {
		try {
			this.programDao.delete(id);
		} catch (Exception e) {
		
			e.printStackTrace();
		}

	}

	@Override
	public List<Program> getActivePrograms() {
		return programDao.findAll();
	}



	@Override
	public Program findByProgramName(String programName) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Program result = null;
		
		try {
			tx = session.beginTransaction();
			Query<Program> query = session.createQuery("from Program p where p.programName = :programName",Program.class);
			query.setParameter("programName", programName);
			result = query.getSingleResult();
			
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return result;
	}

	@Override
	public List<Program> findByProgramCodeSubModule(String id, String id2) {

		return programDao.findByProgramCodeSubModule(id,id2);
	}

	@Override
	public List<Program> findProgramByIdSubModule(String subMCode) {
		
		Session session = sessionFactory.openSession();
		try {
			
			Query<Program> query = session.createQuery("from Program  d where d.subModuleCode.subModuleCode = :pathType",Program.class);
			query.setParameter("pathType", subMCode);
			List<Program> result = query.getResultList();
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Program> findProgramByIdModuleCode(String moduleCode) {
		Session session = sessionFactory.openSession();
		try {
			Query<Program> query = session.createQuery("from Program  d where d.pModuleCode.moduleCode = :moduleCode",Program.class);
			query.setParameter("moduleCode", moduleCode);
			List<Program> result = query.getResultList();
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Program> findProgramByModuleCodeSubModule(String moduleCode, String subModulec) {
		
		Session session = sessionFactory.openSession();
		try {
			Query<Program> query = session.createQuery("from Program  d where d.pModuleCode.moduleCode = :moduleCode and d.subModuleCode.subModuleCode= :subModulec ",Program.class);
			query.setParameter("moduleCode", moduleCode);
			query.setParameter("subModulec", subModulec);
			List<Program> result = query.getResultList();
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return null;
	}
}
