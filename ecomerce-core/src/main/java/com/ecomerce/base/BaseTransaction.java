package com.ecomerce.base;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

/**
	 * @author Andri
	 */
@MappedSuperclass	
public abstract class BaseTransaction extends BaseActive  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2640102986817067644L;
	
	@GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)", unique = true)
    @Id
    public String noRec;

	public String getNoRec() {
		return noRec;
	}

	public void setNoRec(String noRec) {
		this.noRec = noRec;
	}


	
}
