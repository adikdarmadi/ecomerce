package com.ecomerce.base;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.validator.constraints.Length;

/**
 * Base class for all entities, but using String idString as 'id'
 * 
 * @author Adik
 */
@MappedSuperclass
public abstract class BaseProfile extends BaseModel implements Serializable {

	//@Length(min = 1,max =50, message = "")
	@Column(name = "kdProfile")
	public short kdProfile;

	public short getKdProfile() {
		return kdProfile;
	}

	public void setKdProfile(short kdProfile) {
		this.kdProfile = kdProfile;
	}

}
