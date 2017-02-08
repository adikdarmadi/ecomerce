package com.ecomerce.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecomerce.controller.base.LocaleController;
import com.ecomerce.core.web.WebConstants;
import com.ecomerce.service.StrukOrderService;
import com.ecomerce.util.rest.RestUtil;
import com.ecomerce.vo.StrukOrderVO;

@SuppressWarnings("rawtypes")
@RestController
@RequestMapping("/strukOrder")
public class StrukOrderController extends LocaleController{
	
	@Autowired
	private StrukOrderService strukOrderService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StrukOrderController.class);


	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/save-strukOrder/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String,Object>> saveStrukOrder(@Valid @RequestBody StrukOrderVO entity,HttpServletRequest request) {
		Map<String, Object> result = strukOrderService.saveStrukOrder(entity);
		mapHeaderMessage.put(WebConstants.STATUS,HttpStatus.CREATED.name());
		mapHeaderMessage.put(WebConstants.STATUS_CODE,HttpStatus.CREATED.toString());
		mapHeaderMessage.put(WebConstants.MESSAGE, WebConstants.HttpHeaderInfo.LABEL_SUCCESS);
		return RestUtil.getJsonResponse(result, HttpStatus.CREATED, mapHeaderMessage);
		
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/kartu-pengendali-list",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String,Object>> getStrukOrder() {
		Map<String, Object> result = strukOrderService.findAll();
		mapHeaderMessage.put(WebConstants.STATUS,HttpStatus.OK.name());
		mapHeaderMessage.put(WebConstants.STATUS_CODE,HttpStatus.OK.toString());
		mapHeaderMessage.put(WebConstants.MESSAGE, WebConstants.HttpHeaderInfo.LABEL_SUCCESS);
		return RestUtil.getJsonResponse(result, HttpStatus.OK, mapHeaderMessage);
	}
	

}
