package com.ecomerce.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ecomerce.base.vo.BaseMasterVO;
import com.ecomerce.helper.Caption;

/**
 *  class Agama
 * 
 * @author Generator
 */
//@Entity
//@Table(name = "Agama_M")
public class AgamaVO extends BaseMasterVO {
	@Caption(value="Agama")
	private String agama;
	
	
	

	public void setAgama(String agama) {
		this.agama = agama;
	}

	@Column(name = "Agama", nullable = false , length = 100)
	public String getAgama(){
		return this.agama;
	}

	@Caption(value="Kode Agama")
	private Byte kdAgama;

	public void setKdAgama(Byte kdAgama) {
		this.kdAgama = kdAgama;
	}

	@Column(name = "KdAgama", nullable = false )
	public Byte getKdAgama(){
		return this.kdAgama;
	}

	@Caption(value="QAgama")
	private short qAgama;

	public void setqAgama(short qAgama) {
		this.qAgama = qAgama;
	}

	@Column(name = "QAgama", nullable = false )
	public short getqAgama(){
		return this.qAgama;
	}


}

