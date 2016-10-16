package com.ecomerce.core.web;

public class WebConstants {
	public static final class PageParameter {
		public static final String LIST_DATA = "listData";
		public static final String TOTAL_PAGES = "totalPages";
		public static final String TOTAL_ELEMENTS = "totalElements";
	}

	public static final class HttpHeaderInfo {
		public static final String TOTAL_PAGE_HEADER = "Total-Pages";
		public static final String TOTAL_COUNT_HEADER = "Total-Count";
		public static final String ERROR_MESSAGE = "Error-Message";
		public static final String LABEL_SUCCESS = "label-success";
		public static final String LABEL_ERROR = "label-error";
		public static final String LABEL_SUCCESS_CREATED = "label-success-created";
		public static final String LABEL_SUCCESS_OK = "label-success-ok";
	}

	// HEADER KEY TOKEN
	public static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";
}
