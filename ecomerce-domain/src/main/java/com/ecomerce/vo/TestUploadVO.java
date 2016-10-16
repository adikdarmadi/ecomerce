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
public class TestUploadVO {
	
	private String fileInput;
	
	private String fileName;

	public String getFileInput() {
		return fileInput;
	}

	public void setFileInput(String fileInput) {
		this.fileInput = fileInput;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	
	
	
}

