package com.ecomerce.entities;

import java.io.Serializable;
import org.hibernate.envers.Audited;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.internal.util.logging.Messages;

import com.ecomerce.base.BaseMaster;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.envers.Audited;

/**
 * class KelompokProduk
 * 
 * @author Generator
 */
@Entity 
@Table(name = "m_kelompok_produk")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class KelompokProduk  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	

	@NotNull(message = "Kelompok Produk tidak boleh kosong")
	@Column(name = "kelompok_produk", nullable = false, length = 50)
	private String kelompokProduk;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getKelompokProduk() {
		return kelompokProduk;
	}


	public void setKelompokProduk(String kelompokProduk) {
		this.kelompokProduk = kelompokProduk;
	}


}