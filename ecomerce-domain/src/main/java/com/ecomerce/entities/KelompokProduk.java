package com.ecomerce.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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