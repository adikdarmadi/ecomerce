package com.ecomerce.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Base class for all entities, but using String idString as 'id'
 * 
 * @author Adik
 */
@SuppressWarnings("serial")
@MappedSuperclass
public class BaseMaster implements Serializable {

	@Column(name = "created_date")
	private Date createdDate;

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	

}
