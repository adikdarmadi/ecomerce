package com.ecomerce.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ecomerce.constant.BaseConstant;
import com.ecomerce.controller.base.LocaleController;
import com.ecomerce.service.AgamaService;
import com.ecomerce.util.rest.RestUtil;

@SuppressWarnings("rawtypes")
@RestController
@RequestMapping("/agama")
public class AgamaController extends LocaleController{
	
	@Autowired
	private AgamaService agamaService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AgamaController.class);
	

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/count",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String,Object>> getStrukOrder() {
		Map<String, Object> result = new HashMap<String, Object>();
		result = agamaService.findCountAgama();
		mapHeaderMessage.put(BaseConstant.STATUS, HttpStatus.OK.name());
		mapHeaderMessage.put(BaseConstant.STATUS_CODE, HttpStatus.OK.toString());
		mapHeaderMessage.put(BaseConstant.MESSAGE, HttpStatus.OK.toString());
		return RestUtil.getJsonResponse(result, HttpStatus.OK, mapHeaderMessage);
		
	}

}
