package com.ecomerce.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "m_login_user")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class LoginUser  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	protected Integer id;

	@NotNull(message = "Kata Sandi tidak boleh kosong")
	@Column(name = "KataSandi", nullable = false, length = 50)
	private String kataSandi;
	
	@NotNull(message = "is add harus Diisi")
	@Column(name = "is_add", nullable = true)
	private Boolean isAdd;
	
	@NotNull(message = "is cconfirm harus Diisi")
	@Column(name = "is_confirm", nullable = true)
	private Boolean isConfirm;

	@NotNull(message = "Nama User tidak boleh kosong")
	@Column(name = "NamaUser", nullable = false, length = 40)
	private String namaUser;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKataSandi() {
		return kataSandi;
	}

	public void setKataSandi(String kataSandi) {
		this.kataSandi = kataSandi;
	}

	public String getNamaUser() {
		return namaUser;
	}

	public void setNamaUser(String namaUser) {
		this.namaUser = namaUser;
	}

	public Boolean getIsAdd() {
		return isAdd;
	}

	public void setIsAdd(Boolean isAdd) {
		this.isAdd = isAdd;
	}

	public Boolean getIsConfirm() {
		return isConfirm;
	}

	public void setIsConfirm(Boolean isConfirm) {
		this.isConfirm = isConfirm;
	}
	
	


}
