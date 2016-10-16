package com.ecomerce.dao.custom.base.util;

import org.hibernate.HibernateException;

/**
 * The Class CorePersistenceException.
 */
public class CorePersistenceException extends HibernateException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7311418088412161513L;

	public CorePersistenceException(Throwable e) {
		super(e);
	}

	public CorePersistenceException(String msg, Throwable e) {
		super(msg, e);
	}

	public CorePersistenceException(String s) {
		super(s);
	}

}