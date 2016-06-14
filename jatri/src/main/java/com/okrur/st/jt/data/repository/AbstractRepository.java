package com.okrur.st.jt.data.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class AbstractRepository<T> {
	@PersistenceContext(unitName = "jatriPU")
	public EntityManager entityManager;
	
//	@PersistenceContext(unitName = "remote_brachub")
//	public EntityManager remoteEM;
	
	private Class<T> entityClass;
	
	public AbstractRepository(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public void create(T entity) {
		entityManager.persist(entity);
		EntityLookupManager.setLookupNeeded(entityClass, true);
	}

	public void edit(T entity) {
		entityManager.merge(entity);
		EntityLookupManager.setLookupNeeded(entityClass, true);
	}

	public void remove(T entity) {
		entityManager.remove(entityManager.merge(entity));
		EntityLookupManager.setLookupNeeded(entityClass, true);
	}

	public T findById(String id) {
		return entityManager.find(entityClass, id);
	}
	
	public T findById(Long id) {
		return entityManager.find(entityClass, id);
	}

	public T findByUri(String uri) {
		return entityManager.find(entityClass, uri);
	}

	public List<T> findAll() {
		javax.persistence.criteria.CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		return entityManager.createQuery(cq).getResultList();
	}

	public List<T> findAll(String clause, Object[] params) {
		StringBuilder hsql = new StringBuilder();
		hsql.append("Select t from ").append(entityClass.getSimpleName()).append(" t ");

		if ((clause != null && !clause.isEmpty() && params != null)) {
			hsql.append(" where ").append(clause);
		}
		Query query = entityManager.createQuery(hsql.toString());
		for (int i = 0; params != null && i < params.length; i++) {
			query.setParameter(i + 1, params[i]);
		}
		return query.getResultList();
	}

	public List<T> findAll(String hsql) {
		Query query = entityManager.createQuery(hsql.toString());
		return query.getResultList();
	}

	public List<T> findRange(int[] range) {
		javax.persistence.criteria.CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		javax.persistence.Query q = entityManager.createQuery(cq);
		q.setFirstResult(range[0]);
		q.setMaxResults(range[1] - range[0]);
		return q.getResultList();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> findRange(int pageNo, int pageSize) {
		javax.persistence.criteria.CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		javax.persistence.Query query = entityManager.createQuery(cq);
		query.setFirstResult(pageNo * pageSize);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> findRangeWithOrder(int pageNo, int pageSize, String orderClause) {
		javax.persistence.criteria.CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		if (orderClause != null && !orderClause.isEmpty()) {
			cq.orderBy(entityManager.getCriteriaBuilder().desc(cq.from(entityClass).get(orderClause)));
		}
		javax.persistence.Query query = entityManager.createQuery(cq);
		query.setFirstResult(pageNo * pageSize);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

	/**
	 * @param pageNo
	 * @param pageSize
	 * @param whereClause
	 * @return
	 * @note: pageNo should start with '0'
	 */
	public List<T> findRange(int pageNo, int pageSize, String whereClause) {
		int first = pageNo * pageSize;
		int max = pageSize;

		StringBuilder hsql = new StringBuilder();
		hsql.append("Select t from ").append(entityClass.getSimpleName()).append(" t ");

		if (whereClause != null && !whereClause.isEmpty()) {
			hsql.append(" where ").append(whereClause);
		}
		Query query = entityManager.createQuery(hsql.toString());
		query.setFirstResult(first);
		query.setMaxResults(max);

		return query.getResultList();
	}

	public List<T> findRange(int pageNo, int pageSize, String clause, Object[] params) {
		int first = (pageNo - 1) * pageSize;
		int max = pageSize;

		StringBuilder hsql = new StringBuilder();
		hsql.append("Select t from ").append(entityClass.getSimpleName()).append(" t ");

		if ((clause != null && !clause.isEmpty() && params != null)) {
			hsql.append(" where ").append(clause);
		}
		Query query = entityManager.createQuery(hsql.toString());
		query.setFirstResult(first);
		query.setMaxResults(max);

		/** bind parameters */
		for (int i = 0; params != null && i < params.length; i++) {
			query.setParameter(i + 1, params[i]);
		}
		return query.getResultList();
	}

	public List<T> findRangeByJQL(int pageNo, int pageSize, String jql) {
		int first = (pageNo - 1) * pageSize;
		int max = pageSize;

		Query query = entityManager.createQuery(jql);
		if (pageSize != 0) {// TODO (raju) we have remove this.this is just test
							// purpose
			query.setFirstResult(first);
			query.setMaxResults(max);
		}
		return query.getResultList();
	}

	public List findRangeByNQL(int pageNo, int pageSize, String nativeSql) {
		int first = (pageNo - 1) * pageSize;
		int max = pageSize;

		Query query = entityManager.createNativeQuery(nativeSql);
		query.setFirstResult(first);
		query.setMaxResults(max);

		return query.getResultList();
	}

	public int count() {
		javax.persistence.criteria.CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
		javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
		cq.select(entityManager.getCriteriaBuilder().count(rt));
		javax.persistence.Query q = entityManager.createQuery(cq);
		return ((Long) q.getSingleResult()).intValue();
	}

	public int count(String clause, Object[] params) {
		StringBuilder hsql = new StringBuilder();
		hsql.append("Select t from ").append(entityClass.getSimpleName()).append(" t ");

		if ((clause != null && !clause.isEmpty() && params != null)) {
			hsql.append(" where ").append(clause);
		}
		Query query = entityManager.createQuery(hsql.toString());

		/** bind parameters */
		for (int i = 0; params != null && i < params.length; i++) {
			query.setParameter(i + 1, params[i]);
		}

		return query.getResultList().size();
	}
	
	public int count(String whereClause) {
		StringBuilder hsql = new StringBuilder();
		hsql.append("Select t from ").append(entityClass.getSimpleName()).append(" t ");
		if (whereClause != null && !whereClause.isEmpty()) {
			hsql.append(" where ").append(whereClause);
		}
		Query query = entityManager.createQuery(hsql.toString());
		return query.getResultList().size();
	}

	public int countByJQL(String jql) {
		Query query = entityManager.createQuery(jql);
		return ((Long) query.getSingleResult()).intValue();
	}

	public int countByJNQ(String jql) {
		Query query = entityManager.createNativeQuery(jql);
		List list = query.getResultList();
		return list == null ? 0 : list.size();
	}

	public List<T> loadByClause(String clause, Object[] params) {
		StringBuilder hsql = new StringBuilder();
		hsql.append("Select t from ").append(entityClass.getSimpleName()).append(" t ");

		if ((clause != null && !clause.isEmpty() && params != null)) {
			hsql.append(" where ").append(clause);
		}

		Query query = entityManager.createQuery(hsql.toString());
		/** bind parameters */
		for (int i = 0; params != null && i < params.length; i++) {
			if (params[i] != null) {
				query.setParameter(i + 1, params[i]);
			}
		}
		return query.getResultList();
	}
	
//	javax.persistence.criteria.CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
//	cq.select(cq.from(entityClass));
//	if (orderClause != null && !orderClause.isEmpty()) {
//		cq.orderBy(entityManager.getCriteriaBuilder().desc(cq.from(entityClass).get(orderClause)));
//	}

	public List<T> loadByClause(String clause, Object[] params, int size) {
		StringBuilder hsql = new StringBuilder();
		hsql.append("Select t from ").append(entityClass.getSimpleName()).append(" t ");

		if ((clause != null && !clause.isEmpty() && params != null)) {
			hsql.append(" where ").append(clause);
		}

		Query query = entityManager.createQuery(hsql.toString());
		query.setMaxResults(size);
		/** bind parameters */
		for (int i = 0; params != null && i < params.length; i++) {
			if (params[i] != null) {
				query.setParameter(i + 1, params[i]);
			}
		}
		return query.getResultList();
	}

	public List<T> loadByQuery(String sql, Object[] params) {
		Query query = entityManager.createQuery(sql);

		/** bind parameters */
		for (int i = 0; params != null && i < params.length; i++) {
			query.setParameter(i + 1, params[i]);
		}
		return query.getResultList();
	}
	
	public List<T> loadByQuery(String sql) {
		Query query = entityManager.createQuery(sql);
		return query.getResultList();
	}
	
	public List<T> loadByQueryAndRang(int pageNo, int pageSize, String sql) {
		int first = pageNo * pageSize;
		int max = pageSize;
		
		Query query = entityManager.createQuery(sql);
		query.setFirstResult(first);
		query.setMaxResults(max);
		return query.getResultList();
	}


	public List loadsByNativeQuery(String sql) {
		Query query = entityManager.createNativeQuery(sql);
		return query.getResultList();
	}
	
	public int removeByNativeQuery(String sql) {
		int NumberOfDeletedrows = entityManager.createNativeQuery(sql).executeUpdate();
		return NumberOfDeletedrows;
	}
	
	public List loadsByNativeQuery(String sql, Object[] params) {
		Query query = entityManager.createNativeQuery(sql);

		/** bind parameters */
		for (int i = 0; params != null && i < params.length; i++) {
			query.setParameter(i + 1, params[i]);
		}
		return query.getResultList();
	}

	public Object loadByNativeQuery(String sql) {
		Query query = entityManager.createNativeQuery(sql);
		return query.getSingleResult();
	}

	public List loadsByNativeQuery(String sql, int pageNo, int pageSize) {
		int first = (pageNo - 1) * pageSize;
		int max = pageSize;
		Query query = entityManager.createNativeQuery(sql);
		query.setFirstResult(first);
		query.setMaxResults(max);
		return query.getResultList();
	}

	public List<T> findRangeWithDescByStatus(int pageNo, int pageSize, boolean status) {
		int first = (pageNo - 1) * pageSize;
		int max = pageSize;
		StringBuilder hsql = new StringBuilder();
		hsql.append("Select t from ").append(entityClass.getSimpleName()).append(" t ");
		hsql.append(" where t.status = ").append(status);
		hsql.append(" order by t.id desc");

		Query query = entityManager.createQuery(hsql.toString());
		query.setFirstResult(first);
		query.setMaxResults(max);
		return query.getResultList();
	}

	public List<T> findWithDescByStatus(boolean status) {
		StringBuilder hsql = new StringBuilder();
		hsql.append("Select t from ").append(entityClass.getSimpleName()).append(" t ");
		hsql.append(" where t.status = ").append(status);
		hsql.append(" order by t.id desc");

		Query query = entityManager.createQuery(hsql.toString());
		return query.getResultList();
	}

	public List<T> findWithDescByStatus(String entityClass, boolean status) {
		StringBuilder hsql = new StringBuilder();
		hsql.append("Select t from ").append(entityClass).append(" t ");
		hsql.append(" where t.status = ").append(status);
		hsql.append(" order by t.id desc");

		Query query = entityManager.createQuery(hsql.toString());
		return query.getResultList();
	}

	// get total list according with status and id order
	public List<T> getList(boolean status, String order) {
		StringBuilder hsql = new StringBuilder();
		hsql.append("Select t from ").append(entityClass.getSimpleName()).append(" t ");
		hsql.append(" where t.status = ").append(status);
		hsql.append(" order by t.id ").append(order);

		Query query = entityManager.createQuery(hsql.toString());
		return query.getResultList();
	}

	// public List loadsByNativeQuery( String sql, Class enitity ) {
	// Query query = entityManager.createNativeQuery(sql, enitity);
	//
	// List list = query.getResultList();
	// for (Iterator it = list.iterator(); it.hasNext();) {
	// Object object = it.next();
	// System.out.println(object);
	// }
	//
	// return query.getResultList();
	// }

	public T findBy(String columnName, String columnValue) {
		StringBuilder hsql = new StringBuilder();
		hsql.append("Select t from ").append(entityClass.getSimpleName()).append(" t ");
		hsql.append(" where t.").append(columnName).append(" ='").append(columnValue).append("'");

		Query query = entityManager.createQuery(hsql.toString());
		return (query.getResultList() == null || query.getResultList().isEmpty()) ? null : (T) query.getResultList().get(0);
	}

	public T findBy(String entityClass, String columnName, String columnValue) {
		StringBuilder hsql = new StringBuilder();
		hsql.append("Select t from ").append(entityClass).append(" t ");
		hsql.append(" where t.").append(columnName).append(" ='").append(columnValue).append("'");

		Query query = entityManager.createQuery(hsql.toString());
		return (query.getResultList() == null || query.getResultList().isEmpty()) ? null : (T) query.getResultList().get(0);
	}

	public int editByJql(String jql) {
		Query query = entityManager.createQuery(jql);
		return query.executeUpdate();
	}

	public int editByNativeQuery(String sql) {
		Query query = entityManager.createNativeQuery(sql);
		return query.executeUpdate();
	}
	
	public List<T> loadByNamedQuery(String queryName, String paramName, Object paramValue) {
		Query query = entityManager.createNamedQuery(queryName);
		query.setParameter(paramName, paramValue);
		List results = query.getResultList();
		return (results == null || results.isEmpty()) ? null : (List<T>) results; 
	}
	
	public List<T> loadByNamedQuery(String queryName, List<Object[]> params) {
		Query query = entityManager.createNamedQuery(queryName);
		for (Object[] param : params) {
			query.setParameter(param[0].toString(), param[1]);
		}
		List results = query.getResultList();
		return (results == null || results.isEmpty()) ? null : (List<T>) results;
	}
	
//	public List loadsByRemoteNativeQuery(String sql) {
//		Query query = remoteEM.createNativeQuery(sql);
//		return query.getResultList();
//	}
}
