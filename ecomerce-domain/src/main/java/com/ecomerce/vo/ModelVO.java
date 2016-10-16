package com.ecomerce.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelVO {
	
	Map<String,Object> model=new HashMap<String, Object>();
	
	List<Map<String,Object>> attributes=new ArrayList<Map<String,Object>>();
	
	public Map<String, Object> getModel() {
		return model;
	}

	public void setModel(Map<String, Object> model) {
		this.model = model;
	}

	public List<Map<String, Object>> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Map<String, Object>> attributes) {
		this.attributes = attributes;
	}

		
}
