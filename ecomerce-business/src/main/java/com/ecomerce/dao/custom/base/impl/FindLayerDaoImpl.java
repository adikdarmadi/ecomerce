package com.ecomerce.dao.custom.base.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.hibernate.NonUniqueResultException;

import com.ecomerce.base.BaseMaster;
import com.ecomerce.dao.custom.base.FindLayerDao;
import com.ecomerce.dao.custom.base.util.CoreExceptionText;
import com.ecomerce.dao.custom.base.util.CorePersistenceException;
import com.ecomerce.dao.custom.base.util.PropCriteriaAndValue;
import com.ecomerce.dao.custom.base.util.QueryComparator;
import com.ecomerce.dao.custom.base.util.QueryOrder;
import com.ecomerce.dao.custom.base.util.SqlUtil;

/**
 * @author Roberto
 * 
 */
public class FindLayerDaoImpl<T extends BaseMaster> extends CountLayerDaoImpl<T>
		implements FindLayerDao<T> {

	/**
	 * constructor.
	 * 
	 * @param clazz
	 *            The same class as the typed parameter used when defining
	 *            CoreDaoImpl<T> sadly we need it to be passed in the
	 *            constructor also to be able to access class methods
	 */
	public FindLayerDaoImpl(Class<? extends BaseMaster> clazz) {
		super(clazz);
	}

	/**
	 * Find by id.
	 * 
	 * @param id
	 *            the id
	 * @return the t {@inheritDoc}
	 */
	@SuppressWarnings(UNCHECKED)
	public T findById(Integer id) {
		try {
			return (T) em.find(clazz, id);
		} catch (Exception re) {
			LOG.error(I18N_FIND + I18N_FAILED, re);
			throw new CorePersistenceException(
					CoreExceptionText.I18N_OPERATION_FAILED, re);
		}
	}

	/**
	 * Find by id.
	 * 
	 * @param id
	 *            the id
	 * @param leftJoinFetchNames
	 *            the left join fetch names
	 * @return the t {@inheritDoc}
	 */
	@SuppressWarnings(UNCHECKED)
	public T findById(String id, String... leftJoinFetchNames) {
		StringBuilder sb = new StringBuilder(getSelectFindQuery());
		sb.append(createLeftJoinFetchPhrase(leftJoinFetchNames));
		sb.append("WHERE model.id = ?");
		Query query = createQuery(sb);
		query.setParameter(0, id);
		return (T) query.getSingleResult();
	}

	/**
	 * Find all.
	 * 
	 * @param rowStartIdxAndCount
	 *            the row start idx and count
	 * @return the list {@inheritDoc}
	 */
	public List<T> findAll(final int... rowStartIdxAndCount) {
		return findAll(null, null, rowStartIdxAndCount);
	}

	/**
	 * Find all.
	 * 
	 * @param leftJoinFetchColumns
	 *            the left join fetch columns
	 * @param orders
	 *            the orders
	 * @param rowStartIdxAndCount
	 *            the row start idx and count
	 * @return the list {@inheritDoc}
	 */
	@SuppressWarnings(UNCHECKED)
	public List<T> findAll(List<String> leftJoinFetchColumns,
			List<QueryOrder> orders, final int... rowStartIdxAndCount) {
		StringBuilder sqlString = new StringBuilder(getSelectFindQuery());
		// left join fetch phrase
		sqlString.append(createLeftJoinFetchPhrase(leftJoinFetchColumns));
		// sorting orders
		sqlString.append(createQueryOrderPhrase(orders));
		try {
			Query query = createQuery(sqlString);
			return (List<T>) SqlUtil.doQuery(query, rowStartIdxAndCount);
		} catch (Exception re) {
			LOG.error(I18N_FIND + I18N_FAILED, re);
			throw new CorePersistenceException(
					CoreExceptionText.I18N_OPERATION_FAILED, re);
		}
	}

	/**
	 * Find by map of properties.
	 * 
	 * @param propertiesMap
	 *            the properties map
	 * @param orders
	 *            the orders
	 * @param rowStartIdxAndCount
	 *            the row start idx and count
	 * @return the list {@inheritDoc}
	 */
	public List<T> findByMapOfProperties(
			Map<String, ? extends Object> propertiesMap,
			List<QueryOrder> orders, final int... rowStartIdxAndCount) {
		return findByMapOfProperties(propertiesMap, null, orders,
				rowStartIdxAndCount);
	}

	/**
	 * Find by map of properties.
	 * 
	 * @param propertiesMap
	 *            the properties map
	 * @param leftJoinFetchColumns
	 *            the left join fetch columns
	 * @param orders
	 *            the orders
	 * @param rowStartIdxAndCount
	 *            the row start idx and count
	 * @return the list {@inheritDoc}
	 */
	public List<T> findByMapOfProperties(
			Map<String, ? extends Object> propertiesMap,
			List<String> leftJoinFetchColumns, List<QueryOrder> orders,
			final int... rowStartIdxAndCount) {
		StringBuilder queryString = new StringBuilder(getSelectFindQuery());
		// Create LEFT JOIN FETCH phrase
		queryString.append(createLeftJoinFetchPhrase(leftJoinFetchColumns));
		queryString.append(WHERE);
		return doQueryForMaps(queryString.toString(), QueryComparator.EQUALS,
				propertiesMap, orders, null, rowStartIdxAndCount);
	}

	/**
	 * Find like map of properties.
	 * 
	 * @param propertiesMap
	 *            the properties map
	 * @param rowStartIdxAndCount
	 *            the row start idx and count
	 * @return the list {@inheritDoc}
	 */
	public List<T> findLikeMapOfProperties(
			Map<String, ? extends Object> propertiesMap,
			final int... rowStartIdxAndCount) {
		return findLikeMapOfProperties(propertiesMap, null, rowStartIdxAndCount);
	}

	/**
	 * Find like map of properties.
	 * 
	 * @param propertiesMap
	 *            the properties map
	 * @param orders
	 *            the orders
	 * @param rowStartIdxAndCount
	 *            the row start idx and count
	 * @return the list {@inheritDoc}
	 */
	public List<T> findLikeMapOfProperties(
			Map<String, ? extends Object> propertiesMap,
			List<QueryOrder> orders, final int... rowStartIdxAndCount) {
		return findLikeMapOfProperties(propertiesMap, null, orders,
				rowStartIdxAndCount);
	}

	/**
	 * Find like map of properties.
	 * 
	 * @param propertiesMap
	 *            the properties map
	 * @param leftJoinFetchColumns
	 *            the left join fetch columns
	 * @param orders
	 *            the orders
	 * @param rowStartIdxAndCount
	 *            the row start idx and count
	 * @return the list {@inheritDoc}
	 */
	public List<T> findLikeMapOfProperties(
			Map<String, ? extends Object> propertiesMap,
			List<String> leftJoinFetchColumns, List<QueryOrder> orders,
			final int... rowStartIdxAndCount) {
		StringBuilder queryString = new StringBuilder(getSelectFindQuery());
		queryString.append(createLeftJoinFetchPhrase(leftJoinFetchColumns));
		queryString.append(WHERE);
		return doQueryForMaps(queryString.toString(), QueryComparator.LIKE,
				propertiesMap, orders, null, rowStartIdxAndCount);
	}

	/**
	 * Find by property.
	 * 
	 * @param propertyName
	 *            the property name
	 * @param value
	 *            the value
	 * @param rowStartIdxAndCount
	 *            the row start idx and count
	 * @return the list {@inheritDoc}
	 */
	public List<T> findByProperty(String propertyName, Object value,
			final int... rowStartIdxAndCount) {
		return findByProperty(propertyName, value, null, null,
				rowStartIdxAndCount);
	}

	/**
	 * Find by property.
	 * 
	 * @param propertyName
	 *            the property name
	 * @param value
	 *            the value
	 * @param orders
	 *            the orders
	 * @param rowStartIdxAndCount
	 *            the row start idx and count
	 * @return the list {@inheritDoc}
	 */
	public List<T> findByProperty(String propertyName, Object value,
			List<QueryOrder> orders, final int... rowStartIdxAndCount) {
		return findByProperty(propertyName, value, null, orders,
				rowStartIdxAndCount);
	}

	/**
	 * Find like property.
	 * 
	 * @param propertyName
	 *            the property name
	 * @param value
	 *            the value
	 * @param rowStartIdxAndCount
	 *            the row start idx and count
	 * @return the list {@inheritDoc}
	 */
	public List<T> findLikeProperty(String propertyName, Object value,
			final int... rowStartIdxAndCount) {
		return findLikeProperty(propertyName, value, null, rowStartIdxAndCount);
	}

	/**
	 * Find like property.
	 * 
	 * @param propertyName
	 *            the property name
	 * @param value
	 *            the value
	 * @param orders
	 *            the orders
	 * @param rowStartIdxAndCount
	 *            the row start idx and count
	 * @return the list {@inheritDoc}
	 */
	public List<T> findLikeProperty(String propertyName, Object value,
			List<QueryOrder> orders, final int... rowStartIdxAndCount) {
		return findLikeProperty(propertyName, value, null, orders,
				rowStartIdxAndCount);
	}

	/**
	 * Find like property.
	 * 
	 * @param propertyName
	 *            the property name
	 * @param value
	 *            the value
	 * @param leftJoinFetchColumns
	 *            the left join fetch columns
	 * @param orders
	 *            the orders
	 * @param rowStartIdxAndCount
	 *            the row start idx and count
	 * @return the list {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<T> findLikeProperty(String propertyName, Object value,
			List<String> leftJoinFetchColumns, List<QueryOrder> orders,
			final int... rowStartIdxAndCount) {
		return (List<T>) findGenericUsingProperty(propertyName,
				QueryComparator.LIKE, value, leftJoinFetchColumns, orders,
				null, rowStartIdxAndCount);
	}

	/**
	 * Find by property.
	 * 
	 * @param propertyName
	 *            the property name
	 * @param value
	 *            the value
	 * @param leftJoinFetchColumns
	 *            the left join fetch columns
	 * @param orders
	 *            the orders
	 * @param rowStartIdxAndCount
	 *            the row start idx and count
	 * @return the list {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByProperty(String propertyName, Object value,
			List<String> leftJoinFetchColumns, List<QueryOrder> orders,
			final int... rowStartIdxAndCount) {
		return (List<T>) findGenericUsingProperty(propertyName,
				QueryComparator.EQUALS, value, leftJoinFetchColumns, orders,
				null, rowStartIdxAndCount);
	}

	/**
	 * Find unique by property.
	 * 
	 * 
	 * @param propertyName
	 *            the property name
	 * @param value
	 *            the value
	 * @param leftJoinFetchColumns
	 *            the left join fetch columns
	 * @return the <T>
	 * @throws NonUniqueResultException
	 *             if there is more than one matching result
	 */
	@SuppressWarnings("unchecked")
	public T findUniqueByProperty(String propertyName, Object value,
			List<String> leftJoinFetchColumns) {
		return (T) findGenericUsingProperty(propertyName,
				QueryComparator.EQUALS, value, leftJoinFetchColumns, null, true);
	}

	/**
	 * Finds elements using the comparator (EQUALS,LIKE)
	 * 
	 * @param propertyName
	 * @param comparator
	 * @param value
	 * @param leftJoinFetchColumns
	 * @param orders
	 * @param uniqueResult
	 *            For unique results, defaults to false. USE WISELY!
	 * @param rowStartIdxAndCount
	 * @return Object Can be List<T> or <T> depending on uniqueResult
	 * @throws NonUniqueResultException
	 *             if there is more than one matching result
	 */
	private Object findGenericUsingProperty(String propertyName,
			QueryComparator comparator, Object value,
			List<String> leftJoinFetchColumns, List<QueryOrder> orders,
			Boolean uniqueResult, final int... rowStartIdxAndCount) {
		if (uniqueResult == null) {
			uniqueResult = false;
		}
		StringBuilder queryString = new StringBuilder(getSelectFindQuery());
		// Creating LEFT JOIN FETCH phrase
		queryString.append(createLeftJoinFetchPhrase(leftJoinFetchColumns));
		queryString.append(WHERE);
		queryString.append(SqlUtil.createSingleWhereClause(propertyName, value,
				comparator));
		// sorting orders

		queryString.append(createQueryOrderPhrase(orders));
		LOG.info("rod----------- "+queryString);
		try {
			Query query = createQuery(queryString);
			setsParamToValueOrToLowercaseValue(query, propertyName, comparator,
					value, 0);
			if (uniqueResult) {
				return query.getSingleResult();
			}
			return SqlUtil.doQuery(query, rowStartIdxAndCount);
		} catch (Exception re) {
			LOG.error(I18N_FIND + I18N_FAILED, re);
			throw new CorePersistenceException(
					CoreExceptionText.I18N_OPERATION_FAILED, re);
		}
		// dbejar: Calling findByMapOfProperties is much less efficient!!!
	}

	/**
	 * Find using filter.
	 * 
	 * @param filter
	 *            the filter
	 * @param rowStartIdxAndCount
	 *            the row start idx and count
	 * @return the list {@inheritDoc}
	 */
	public List<T> findUsingFilter(List<PropCriteriaAndValue> filter,
			final int... rowStartIdxAndCount) {
		return findUsingFilter(filter, null, rowStartIdxAndCount);
	}

	/**
	 * Find using filter.
	 * 
	 * @param filter
	 *            the filter
	 * @param orders
	 *            the orders
	 * @param rowStartIdxAndCount
	 *            the row start idx and count
	 * @return the list {@inheritDoc}
	 */
	@SuppressWarnings(UNCHECKED)
	public List<T> findUsingFilter(List<PropCriteriaAndValue> filter,
			List<QueryOrder> orders, final int... rowStartIdxAndCount) {
		// TO DO CORE JPA
		return null;
	}

	/**
	 * Find using filter.
	 * 
	 * @param leftJoinFetch
	 *            the left join fetch
	 * @param filter
	 *            the filter
	 * @param orders
	 *            the orders
	 * @param rowStartIdxAndCount
	 *            the row start idx and count
	 * @return the list {@inheritDoc}
	 */
	@SuppressWarnings(UNCHECKED)
	public List<T> findUsingFilter(List<String> leftJoinFetch,
			List<PropCriteriaAndValue> filter, List<QueryOrder> orders,
			final int... rowStartIdxAndCount) {
		// TO DO CORE JPA
		return null;
	}

}
