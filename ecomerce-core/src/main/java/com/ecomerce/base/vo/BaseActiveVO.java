package com.ecomerce.base.vo;

import javax.persistence.MappedSuperclass;

/**
 * Base Action VO class
 * 
 * @author Adik
 */
@MappedSuperclass
public abstract class BaseActiveVO extends BaseProfileVO {

	protected Boolean statusEnabled;

	public BaseActiveVO() {
		// TODO Auto-generated constructor stub
	}

	public Boolean getStatusEnabled() {
		return statusEnabled;
	}

	public void setStatusEnabled(Boolean statusEnabled) {
		this.statusEnabled = statusEnabled;
	}

}
