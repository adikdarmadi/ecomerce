package com.ecomerce.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "Agama_M")
public class Agama extends BaseMaster {
	@NotNull(message="Agama tidak boleh kosong")
	@Column(name = "Agama", nullable = false , length = 100)
	@Caption(value="Agama")
	private String agama;

	public void setAgama(String agama) {
		this.agama = agama;
	}


	public String getAgama(){
		return this.agama;
	}

	@NotNull(message="Kd Agama tidak boleh kosong")
	@Column(name = "KdAgama", nullable = false )
	@Caption(value="Kode Agama")
	private Byte kdAgama;

	public void setKdAgama(Byte kdAgama) {
		this.kdAgama = kdAgama;
	}


	public Byte getKdAgama(){
		return this.kdAgama;
	}

	@NotNull(message="QAgama tidak boleh kosong")
	@Column(name = "QAgama", nullable = false )
	@Caption(value="QAgama")
	private short qAgama;

	public void setqAgama(short qAgama) {
		this.qAgama = qAgama;
	}


	public short getqAgama(){
		return this.qAgama;
	}


}

