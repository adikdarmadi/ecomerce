package com.ecomerce.vo;

import java.io.Serializable;

/**
 * VO untuk Login
 */
public class AuthVO implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7391557986006113925L;
	private String namaUser;
	private String kataSandi;

	public String getNamaUser() {
		return namaUser;
	}

	public void setNamaUser(String namaUser) {
		this.namaUser = namaUser;
	}

	public String getKataSandi() {
		return kataSandi;
	}

	public void setKataSandi(String kataSandi) {
		this.kataSandi = kataSandi;
	}

}
