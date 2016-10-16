package com.ecomerce.base.vo;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

/**
	 * @author Andri
	 */
@MappedSuperclass
public abstract class BaseTransactionVO extends BaseActiveVO implements Serializable{
	protected String noRec;
	

	public BaseTransactionVO() {

	}


	public String getNoRec() {
		return noRec;
	}


	public void setNoRec(String noRec) {
		this.noRec = noRec;
	}


	



}
