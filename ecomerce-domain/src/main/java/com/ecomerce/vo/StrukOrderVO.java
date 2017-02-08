package com.ecomerce.vo;

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
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.ecomerce.base.BaseMaster;

/**
 * class Produk
 * 
 * @author Generator
 */
public class StrukOrderVO {

   @NotNull(message="tanggal harus Diisi") 
	private Date tanggalOrder;
    
	@NotEmpty(message="struk order Detail Harus Diisi")
	@Valid
	private List<StrukOrderDetailVO> strukOrders = new ArrayList<StrukOrderDetailVO>();

	public Date getTanggalOrder() {
		return tanggalOrder;
	}

	public void setTanggalOrder(Date tanggalOrder) {
		this.tanggalOrder = tanggalOrder;
	}

	public List<StrukOrderDetailVO> getStrukOrders() {
		return strukOrders;
	}

	public void setStrukOrders(List<StrukOrderDetailVO> strukOrders) {
		this.strukOrders = strukOrders;
	}


	
}