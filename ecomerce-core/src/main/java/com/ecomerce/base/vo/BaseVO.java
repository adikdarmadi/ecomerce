package com.ecomerce.base.vo;

/**
 * Base VO class for all entities, for exposing data as JSON Object
 * 
 * @author Roberto
 */
public class BaseVO {
	protected String id;

	public BaseVO(String id) {
		super();
		this.id = id;
	}

	public BaseVO() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
