package com.ecomerce.base.vo;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

/**
 * Base Master VO class
 * 
 * @author Adik
 */
@MappedSuperclass
public abstract class BaseMasterVO extends BaseActiveVO implements Serializable {

	private static final long serialVersionUID = 3408886721062001433L;

	protected Integer id;
	protected String noRec;
	protected String reportDisplay;
	protected String kodeExternal;
	protected String namaExternal;

	public BaseMasterVO() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNoRec() {
		return noRec;
	}

	public void setNoRec(String noRec) {
		this.noRec = noRec;
	}

	public String getReportDisplay() {
		return reportDisplay;
	}

	public void setReportDisplay(String reportDisplay) {
		this.reportDisplay = reportDisplay;
	}

	public String getKodeExternal() {
		return kodeExternal;
	}

	public void setKodeExternal(String kodeExternal) {
		this.kodeExternal = kodeExternal;
	}

	public String getNamaExternal() {
		return namaExternal;
	}

	public void setNamaExternal(String namaExternal) {
		this.namaExternal = namaExternal;
	}

}
