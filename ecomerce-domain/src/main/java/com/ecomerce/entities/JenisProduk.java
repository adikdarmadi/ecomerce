package com.ecomerce.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.ecomerce.base.BaseMaster;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * class JenisProduk
 * 
 * @author Generator
 */
@Entity // @Audited
@Table(name = "m_jenis_produk")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class JenisProduk {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "jenis_produk", nullable = false, length = 80)
	private String jenisProduk;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_kelompok_produk")
	private KelompokProduk kelompokProduk;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getJenisProduk() {
		return jenisProduk;
	}

	public void setJenisProduk(String jenisProduk) {
		this.jenisProduk = jenisProduk;
	}

	public KelompokProduk getKelompokProduk() {
		return kelompokProduk;
	}

	public void setKelompokProduk(KelompokProduk kelompokProduk) {
		this.kelompokProduk = kelompokProduk;
	}


}