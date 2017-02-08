package com.ecomerce.vo;

import com.ecomerce.base.vo.BaseMasterVO;

/**
 *  class Agama
 * 
 * @author Generator
 */
//@Entity
//@Table(name = "Agama_M")
public class AgamaVO extends BaseMasterVO {

	private Integer id;
	
	private String agama;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAgama() {
		return agama;
	}

	public void setAgama(String agama) {
		this.agama = agama;
	}
	
	
	
}

