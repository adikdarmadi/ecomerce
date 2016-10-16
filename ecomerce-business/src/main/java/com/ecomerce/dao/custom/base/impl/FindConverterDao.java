package com.ecomerce.dao.custom.base.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ecomerce.base.BaseModel;
import com.ecomerce.dao.custom.base.util.CoreExceptionText;
import com.ecomerce.dao.custom.base.util.CorePersistenceException;

/**
 * @author Roberto
 * 
 */
public class FindConverterDao {

	@PersistenceContext
	protected EntityManager em;

	/**
	 * Find object by id.
	 * 
	 * @param clazz
	 *            the clazz
	 * @param id
	 *            the id
	 * @return the t
	 */
	public Object findObjectById(Class<? extends BaseModel> clazz, Integer id) {
		try {
			return em.find(clazz, id);
		} catch (Exception re) {
			throw new CorePersistenceException(
					CoreExceptionText.I18N_OPERATION_FAILED, re);
		}
	}

}
