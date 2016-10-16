package com.ecomerce.base.vo.validation;


/**
 * VO which contains the information of a single validation error.
 * 
 * @author Roberto
 */
public class FieldErrorVO {
	private String field;

	private String message;

	public FieldErrorVO(String field, String message) {
		this.field = field;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public String getMessage() {
		return message;
	}

}
