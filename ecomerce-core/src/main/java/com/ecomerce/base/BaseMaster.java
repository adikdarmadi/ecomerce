package com.ecomerce.base;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

/**
 * Base class for all entities, but using String idString as 'id'
 * 
 * @author Adik
 */
@MappedSuperclass
public class BaseMaster extends BaseActive implements Serializable {

	/**
	 * 
	 */
//	private static final long serialVersionUID = -7522287859244078391L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	protected Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(columnDefinition = "CHAR(32)")
	private String noRec;

	public String getNoRec() {
		return noRec;
	}

	public void setNoRec(String noRec) {
		this.noRec = noRec;
	}

//	public static long getSerialversionuid() {
//		return serialVersionUID;
//	}

	@Length(min = 1, max = 50, message = "")
	@Column(name = "reportDisplay")
	protected String reportDisplay;

	@Length(min = 1, max = 15, message = "")
	@Column(name = "kodeExternal")
	protected String kodeExternal;

	@Length(min = 1, max = 50, message = "")
	@Column(name = "namaExternal")
	protected String namaExternal;

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
