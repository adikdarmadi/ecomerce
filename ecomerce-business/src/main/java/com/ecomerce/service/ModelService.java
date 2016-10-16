package com.ecomerce.service;

import java.util.List;
import java.util.Map;

import com.ecomerce.vo.ModelVO;

/**
 * Pasien Service
 * 
 * @author Roberto
 */
public interface ModelService<T> {
	ModelVO getModelSerializeEntity(String name, String language);

	List<Map<String,Object>> getAllData(String entity, String field, Integer take, Integer skip, Integer page, Integer pageSize,
			String logic, String value, String fields, String operator, String ignorecase, String criteria, String values);
}
