package com.hrms.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.PathDirectory;
import com.hrms.repository.PathDirectoryDao;
@Service
public class PathDirectoryServicesImpl implements PathDirectoryServices{
	@Autowired  PathDirectoryDao pathDirectoryDao;
	@Autowired SessionFactory sessionFactory;
	@Override
	public List<PathDirectory> getAllPathDirectory() {
		return pathDirectoryDao.findAll();
	}
	@Override
	public void addPathDirectory(PathDirectory pathDirectory) {
		this.pathDirectoryDao.save(pathDirectory);
	}
	@Override
	public PathDirectory findPathDirectoryById(long id) {
		return pathDirectoryDao.findById(id);
	}
	@Override
	public void updatePathDirectory(PathDirectory a) {
		this.pathDirectoryDao.saveOrUpdate(a);
	}
	@Override
	public void removePathDirectory(long id) {
		this.pathDirectoryDao.delete(id);
	}
	@Override
	public PathDirectory findPathByName(String pathType) {
	
		try {
			Session session = sessionFactory.openSession();
			Query<PathDirectory> query = session.createQuery("from PathDirectory  d where d.pType = :pathType",PathDirectory.class);
			
			query.setParameter("pathType", pathType);
			
			PathDirectory result = query.getSingleResult();
			System.out.println("result : ");
			return result;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
