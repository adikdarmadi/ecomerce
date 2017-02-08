package com.ecomerce.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;

public class ApiError {

    private HttpStatus status;
    private String message;
    private List<Map<String,Object>> errors;
	private String statusCode;

    //

    public ApiError() {
        super();
    }

    public ApiError(final HttpStatus status, final String message, final List<Map<String,Object>> errors,String statusCode) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
        this.statusCode=statusCode;
    }

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public List<Map<String, Object>> getErrors() {
		return errors;
	}

	public void setErrors(List<Map<String, Object>> errors) {
		this.errors = errors;
	}

   


}
