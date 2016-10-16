package com.ecomerce.base;

import java.io.Serializable;

/**
 * Base class for all entities, but using String idString as 'id'
 * 
 * @author Roberto
 */
public class Base implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2852686906555972960L;
	protected String idString;

	public Base(String idString) {
		super();
		this.idString = idString;
	}

	public Base() {
		// TODO Auto-generated constructor stub
	}

	public String getIdString() {
		return idString;
	}

	public void setIdString(String idString) {
		this.idString = idString;
	}
}
