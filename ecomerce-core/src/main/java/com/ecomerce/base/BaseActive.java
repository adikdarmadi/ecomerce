package com.ecomerce.base;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Base class for all entities, but using String idString as 'id'
 * 
 * @author Adik
 */
@MappedSuperclass
public abstract class BaseActive extends BaseProfile implements Serializable {

	@Column(name = "statusEnabled")
	public Boolean statusEnabled;

	public Boolean getStatusEnabled() {
		return statusEnabled;
	}

	public void setStatusEnabled(Boolean statusEnabled) {
		this.statusEnabled = statusEnabled;
	}
	
	
}
