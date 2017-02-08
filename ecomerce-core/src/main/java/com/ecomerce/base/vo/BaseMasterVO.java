package com.ecomerce.base.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.MappedSuperclass;

/**
 * Base Master VO class
 * 
 * @author Adik
 */
@MappedSuperclass
public abstract class BaseMasterVO implements Serializable {

	private static final long serialVersionUID = 3408886721062001433L;

	private Date createdDate;
	
	
}
