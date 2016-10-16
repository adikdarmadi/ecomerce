package com.ecomerce.exception;

/**
 * ServiceVOException is class for containing exception that throw in service layer 
 *
 * @author Roberto
 */
public class ServiceVOException extends RuntimeException {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public ServiceVOException() {
	}

	public ServiceVOException(String s) {
		super(s);
	}

	public ServiceVOException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public ServiceVOException(Throwable throwable) {
		super(throwable);
	}
}
