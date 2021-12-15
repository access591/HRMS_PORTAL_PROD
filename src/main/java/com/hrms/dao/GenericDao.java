package com.hrms.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenericDao<E> {
	List<E> findAll();


	void delete(String id);

	E findById(String id);

	E findById(long id);
	void save(E entity);
	void saveOrUpdate(E entity);

	E existOrNot(E obj);

	void delete(long id);
	String getMaxId(String cId);

}
