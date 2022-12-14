package com.tmx.dashboard.database;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class DatabaseOperationsImpl<T> implements DatabaseOperations<T> {

	@PersistenceContext
	private EntityManager em;


	@Override
	public Long count(Class<T> clazz, Map<String, Object> fields, Object[] between) {
		Long total;
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<T> r = query.from(clazz);
		query.select(builder.count(r));
		if (fields != null) {
			List<Predicate> predicates = getParametersFilter(builder, r, fields, between);
			query.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		}
		total = em.createQuery(query).getSingleResult();
		return total;
	}

	@Override
	public T save(T entity) {
		em.persist(entity);
		return entity;
	}

	@Override
	public T updateById(T entity) {
		em.merge(entity);
		return entity;
	}

	@Override
	public Integer updateAll(Class<T> clazz, Map<String, Object> fields, T entity) {
		int status = 0;
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaUpdate<T> criteria = builder.createCriteriaUpdate(clazz);
		Root<T> root = criteria.from(clazz);

		if (fields != null) {
			getParametersUpdate(clazz, criteria, root, entity);
			List<Predicate> predicates = getParametersFilter(builder, root, fields, null);
			criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
			status = em.createQuery(criteria).executeUpdate();
		}

		return status;
	}

	@Override
	public Integer delete(Class<T> clazz, Map<String, Object> fields) {
		int status = 0;
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaDelete<T> criteria = builder.createCriteriaDelete(clazz);
		Root<T> root = criteria.from(clazz);

		List<Predicate> predicates = getParametersFilter(builder, root, fields, null);
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		status = em.createQuery(criteria).executeUpdate();
		return status;
	}

	@Override
	public Integer deleteById(Class<T> clazz, Object id) {
		T entity = em.find(clazz, id);
		em.remove(entity);
		return null;
	}

	@Override
	public Integer deleteAll(Class<T> clazz) {
		int status = 0;
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaDelete<T> criteria = builder.createCriteriaDelete(clazz);
		criteria.from(clazz);
		status = em.createQuery(criteria).executeUpdate();
		return status;
	}

	@Override
	public List<T> find(Class<T> clazz, Map<String, Object> fields, String[] order) {
		List<T> list;
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<T> criteria = builder.createQuery(clazz);
		Root<T> root = criteria.from(clazz);

		if (fields != null) {
			List<Predicate> predicates = getParametersFilter(builder, root, fields, null);
			criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		}

		if (order != null)
			criteria.orderBy((order[1].equalsIgnoreCase("DESC")) ? builder.desc(root.get(order[0])) : builder.asc(root.get(order[0])));

		TypedQuery<T> typedQuery = em.createQuery(criteria);
		list = typedQuery.getResultList();
		return list;
	}

	@Override
	public List<T> findAll(Class<T> clazz, String[] order) {
		List<T> list;
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<T> criteria = builder.createQuery(clazz);
		Root<T> root = criteria.from(clazz);

		if (order != null)
			criteria.orderBy((order[1].equalsIgnoreCase("DESC")) ? builder.desc(root.get(order[0])) : builder.asc(root.get(order[0])));

		TypedQuery<T> typedQuery = em.createQuery(criteria);
		list = typedQuery.getResultList();
		return list;
	}

	public Page<T> findAll(Class<T> clazz, String[] order, Pageable pageable) {

		CriteriaBuilder builder = em.getCriteriaBuilder();

		//Total de registros
		CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
		countQuery.select(builder.count(countQuery.from(clazz)));
		Long count = em.createQuery(countQuery).getSingleResult();


		CriteriaQuery<T> criteria = builder.createQuery(clazz);
		Root<T> root = criteria.from(clazz);

		if (order != null)
			criteria.orderBy((order[1].equalsIgnoreCase("DESC")) ? builder.desc(root.get(order[0])) : builder.asc(root.get(order[0])));

		TypedQuery<T> typedQuery = em.createQuery(criteria);
		typedQuery.setFirstResult((int) pageable.getOffset());
		typedQuery.setMaxResults(pageable.getPageSize());

		return PageableExecutionUtils.getPage(typedQuery.getResultList(), pageable, () -> count);
	}

	public Page<T> searchInfo(Class<T> clazz, Map<String, Object> fields, String[] order, Pageable pageable) {

		CriteriaBuilder builder = em.getCriteriaBuilder();

		//Total de registros
		CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
		countQuery.select(builder.count(countQuery.from(clazz)));
		Long count = em.createQuery(countQuery).getSingleResult();

		CriteriaQuery<T> criteria = builder.createQuery(clazz);
		Root<T> root = criteria.from(clazz);

		if (fields != null) {
			List<Predicate> predicates = getParametersFilter(builder, root, fields, null);
			criteria.where(builder.or(predicates.toArray(new Predicate[predicates.size()])));
		}

		if (order != null)
			criteria.orderBy((order[1].equalsIgnoreCase("DESC")) ? builder.desc(root.get(order[0])) : builder.asc(root.get(order[0])));

		TypedQuery<T> typedQuery = em.createQuery(criteria);
		typedQuery.setFirstResult((int) pageable.getOffset());
		typedQuery.setMaxResults(pageable.getPageSize());

		return PageableExecutionUtils.getPage(typedQuery.getResultList(), pageable, () -> count);
	}

	@Override
	public T findById(Class<T> clazz, Object id) {
		return em.find(clazz, id);
	}

	public List<Predicate> getParametersFilter(CriteriaBuilder builder, Root<T> root, Map<String, Object> filters, Object[] between) {
		List<Predicate> predicates = new ArrayList<>();
		if(between != null){
			switch (((String) between[0]).split("_")[0]) {
			case "DATE":
				predicates.add(builder.between(root.get(((String) between[0]).split("_")[1]), (Timestamp) between[1], (Timestamp) between[2]));
				break;
			case "LONG":
				predicates.add(builder.between(root.get(((String) between[0]).split("_")[1]), (Long) between[1], (Long)between[2]));
				break;
				default:
					predicates.add(builder.between(root.get(((String) between[0]).split("_")[1]), (String) between[1], (String) between[2]));
					break;
			}
		}

		if (filters != null && !filters.isEmpty()) {
			filters.forEach((k, v) -> {
				if(filters.get(k) instanceof String[]){
					for (int i = 0; i < ((String[]) filters.get(k)).length; i++) {
						predicates.add(builder.equal(root.get(k), ((String[]) filters.get(k))[i]));
					}
				}else{ predicates.add(builder.equal(root.get(k), filters.get(k))); }
			});
		}
		return predicates;
	}

	public CriteriaUpdate<T> getParametersUpdate(Class<T> clazz, CriteriaUpdate<T> update, Root<T> root, T entity) {
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			try {
				if(field.get(entity) != null)
					update.set(root.get(field.getName()), field.get(entity));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return update;
	}
}