package com.ecomerce.dao.custom;

import java.util.List;

public interface GenericServDao<T> {

 	int dataCount(String entity, String value, String fieldS, String criteria, String values);

	List<Object> getDatas(String entity, String field, Integer rowStart, Integer rowEnd, String logic, String value,
			String fieldS, String operator, String criteria, String values);

}