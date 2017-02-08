package com.ecomerce.dao.custom.base.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import com.ecomerce.base.BaseMaster;
import com.ecomerce.dao.custom.base.CountLayerDao;
import com.ecomerce.dao.custom.base.util.CoreExceptionText;
import com.ecomerce.dao.custom.base.util.CorePersistenceException;
import com.ecomerce.dao.custom.base.util.PropCriteriaAndValue;
import com.ecomerce.dao.custom.base.util.QueryComparator;
import com.ecomerce.dao.custom.base.util.SqlUtil;

/**
 * @author Roberto
 * 
 */
public class CountLayerDaoImpl<T extends BaseMaster> extends KernelDaoImpl<T>
		implements CountLayerDao<T> {

	/**
	 * constructor.
	 * 
	 * @param clazz
	 *            The same class as the typed parameter used when defining
	 *            CoreDaoImpl<T> sadly we need it to be passed in the
	 *            constructor also to be able to access class methods
	 */
	public CountLayerDaoImpl(Class<? extends BaseMaster> clazz) {
		super(clazz);
	}

	/**
	 * @return
	 */
	public Integer countAll() {
		LOG.debug("count all " + clazz.getSimpleName() + " instances");
		StringBuilder sqlString = new StringBuilder(getSelectCountQuery());
		try {
			Query query = createQuery(sqlString);
			return Integer.valueOf(query.getSingleResult().toString());
		} catch (Exception re) {
			LOG.error("count all failed", re);
			throw new CorePersistenceException(
					CoreExceptionText.I18N_OPERATION_FAILED, re);
		}
	}

	/**
	 * @param propertiesMap
	 * @return
	 */
	public Integer countByMapOfProperties(
			Map<String, ? extends Object> propertiesMap) {
		StringBuilder queryString = new StringBuilder(getSelectCountQuery())
				.append(WHERE);
		Query query = createQueryForMaps(queryString.toString(),
				QueryComparator.EQUALS, propertiesMap, null, null);
		return Integer.valueOf(query.getSingleResult().toString());
	}

	/**
	 * @param propertiesMap
	 * @return
	 */
	public Integer countLikeMapOfProperties(
			Map<String, ? extends Object> propertiesMap) {
		StringBuilder queryString = new StringBuilder(getSelectCountQuery())
				.append(WHERE);
		Query query = createQueryForMaps(queryString.toString(),
				QueryComparator.LIKE, propertiesMap, null, null);
		return Integer.valueOf(query.getSingleResult().toString());
	}

	/**
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public Integer countLikeProperty(String propertyName, Object value) {
		return countGenericUsingProperty(propertyName, QueryComparator.LIKE,
				value);
	}

	/**
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public Integer countByProperty(String propertyName, Object value) {
		return countGenericUsingProperty(propertyName, QueryComparator.EQUALS,
				value);
	}

	/**
	 * Performs the count HQL operation using the comparator (EQUALS, LIKE)
	 * 
	 * @param propertyName
	 * @param comparator
	 * @param value
	 * @return
	 */
	private Integer countGenericUsingProperty(String propertyName,
			QueryComparator comparator, Object value) {
		StringBuilder queryString = new StringBuilder(getSelectCountQuery())
				.append(WHERE);
		queryString.append(SqlUtil.createSingleWhereClause(propertyName, value,
				comparator));
		try {
			Query query = createQuery(queryString);
			setsParamToValueOrToLowercaseValue(query, propertyName, comparator,
					value, 0);
			return Integer.valueOf(query.getSingleResult().toString());
		} catch (Exception re) {
			LOG.error(I18N_COUNT + I18N_FAILED, re);
			throw new CorePersistenceException(
					CoreExceptionText.I18N_OPERATION_FAILED, re);
		}
	}

	/**
	 * @param filter
	 * @return
	 */
	public Integer countUsingFilter(List<PropCriteriaAndValue> filter) {
		// TO DO CORE JPA
		return 0;
	}

}
