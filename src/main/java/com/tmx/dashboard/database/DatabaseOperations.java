package com.tmx.dashboard.database;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DatabaseOperations<T> {

	/* ................................................................................. GLOBAL DB-CRUD CONFIG - INI */
	public T save(T entity);
	public T updateById(T entity);
	public Integer updateAll(Class<T> clazz, Map<String, Object> fields, T entity);
	public Integer delete(Class<T> clazz, Map<String, Object> fields);
	public Integer deleteById(Class<T> clazz, Object ID);
	public Integer deleteAll(Class<T> clazz);
	public List<T> find(Class<T> clazz, Map<String, Object> fields, String[] order);
	public List<T> findAll(Class<T> clazz, String[] order);
	public Page<T> findAll(Class<T> clazz, String[] order, Pageable pageable);
	public T findById(Class<T> clazz, Object ID);
	public Long count(Class<T> clazz, Map<String, Object> fields, Object[] between);
	/* ................................................................................. GLOBAL DB-CRUD CONFIG - FIN */
}
