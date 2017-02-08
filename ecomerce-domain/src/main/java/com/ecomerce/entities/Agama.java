package com.ecomerce.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

import com.ecomerce.base.BaseMaster;
import com.ecomerce.helper.Caption;

import org.hibernate.envers.Audited;

/**
 *  class Agama
 * 
 * @author Generator
 */
@Entity //@Audited
@Table(name = "m_agama")
public class Agama extends BaseMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@NotNull(message="Agama tidak boleh kosong")
	@Column(name = "Agama", nullable = false , length = 100)
	@Caption(value="Agama")
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

