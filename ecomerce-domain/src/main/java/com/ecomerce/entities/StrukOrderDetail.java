package com.ecomerce.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.ecomerce.base.BaseMaster;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * class Produk
 * 
 * @author Generator
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_struk_order_detail")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class StrukOrderDetail extends BaseMaster {


    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
    
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_struk_order")
	@NotNull(message = "Struk Order Tidak Boleh Kosong")
	private StrukOrder strukOrder;

	@Column(name = "fk_struk_order", nullable=false, insertable = false, updatable = false)
	private Integer strukOrderId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_produk")
	@NotNull(message = "Produk Tidak Boleh Kosong")
	private Produk produk;

	@Column(name = "fk_produk", nullable=false, insertable = false, updatable = false)
	private Integer produkId;
	
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "strukOrderDetail")
	private List<DetailKomponenHarga> detailKomponenHarga = new ArrayList<DetailKomponenHarga>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public StrukOrder getStrukOrder() {
		return strukOrder;
	}

	public void setStrukOrder(StrukOrder strukOrder) {
		this.strukOrder = strukOrder;
	}

	public Integer getStrukOrderId() {
		return strukOrderId;
	}

	public void setStrukOrderId(Integer strukOrderId) {
		this.strukOrderId = strukOrderId;
	}

	public Produk getProduk() {
		return produk;
	}

	public void setProduk(Produk produk) {
		this.produk = produk;
	}

	public Integer getProdukId() {
		return produkId;
	}

	public void setProdukId(Integer produkId) {
		this.produkId = produkId;
	}

	public List<DetailKomponenHarga> getDetailKomponenHarga() {
		return detailKomponenHarga;
	}

	public void setDetailKomponenHarga(List<DetailKomponenHarga> detailKomponenHarga) {
		this.detailKomponenHarga = detailKomponenHarga;
	}


	
}