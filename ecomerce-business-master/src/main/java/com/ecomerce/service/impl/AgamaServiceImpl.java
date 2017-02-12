package com.ecomerce.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomerce.dao.custom.AgamaDaoCustom;
import com.ecomerce.service.AgamaService;

@Service("agamaService")
public class AgamaServiceImpl implements AgamaService {

	@Autowired
	private AgamaDaoCustom agamaDaoCustom;

	@Override
	public Map<String, Object> findCountAgama() {
		// TODO Auto-generated method stub
		Map<String,Object> data=new HashMap<String,Object>();
		data.put("count", agamaDaoCustom.countAgama());
		return data;
	}
	
}
