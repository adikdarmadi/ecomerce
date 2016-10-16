package com.ecomerce.base.vo.validation;

import java.util.ArrayList;
import java.util.List;

/**
 * VO which wraps those validation errors together.
 * 
 * @author Roberto
 */
public class ValidationErrorVO {
	private List<FieldErrorVO> fieldErrors = new ArrayList<FieldErrorVO>();

	public ValidationErrorVO() {

	}

	public void addFieldError(String path, String message) {
		FieldErrorVO error = new FieldErrorVO(path, message);
		fieldErrors.add(error);
	}

	public List<FieldErrorVO> getFieldErrors() {
		return fieldErrors;
	}
}
