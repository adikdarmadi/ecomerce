package com.ecomerce.base.vo;

import javax.persistence.MappedSuperclass;

/**
 * Base Profile VO class
 * 
 * @author Adik
 */
@MappedSuperclass
public class BaseProfileVO extends BaseModelVO {

	public short kdProfile;

	public BaseProfileVO() {
		// TODO Auto-generated constructor stub
	}

	public short getKdProfile() {
		return kdProfile;
	}

	public void setKdProfile(short kdProfile) {
		this.kdProfile = kdProfile;
	}

}
