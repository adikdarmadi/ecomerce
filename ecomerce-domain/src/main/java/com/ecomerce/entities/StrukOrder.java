package com.ecomerce.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * class Produk
 * 
 * @author Generator
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_struk_order")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class StrukOrder {


    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
    
    @Column(name = "tanggalOrder")
	private Date tanggalOrder;
    
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "strukOrder")
	private List<StrukOrderDetail> strukOrders = new ArrayList<StrukOrderDetail>();


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getTanggalOrder() {
		return tanggalOrder;
	}

	public void setTanggalOrder(Date tanggalOrder) {
		this.tanggalOrder = tanggalOrder;
	}

	public List<StrukOrderDetail> getStrukOrders() {
		return strukOrders;
	}

	public void setStrukOrders(List<StrukOrderDetail> strukOrders) {
		this.strukOrders = strukOrders;
	}
    
    
    


	
}