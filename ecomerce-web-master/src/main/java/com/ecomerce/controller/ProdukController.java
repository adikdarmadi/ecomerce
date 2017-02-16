package com.ecomerce.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ecomerce.constant.BaseConstant;
import com.ecomerce.controller.base.LocaleController;
import com.ecomerce.security.AppPermission;
import com.ecomerce.service.ProdukService;
import com.ecomerce.util.rest.RestUtil;
import com.ecomerce.vo.ProdukVO;

@SuppressWarnings("rawtypes")
@RestController
@RequestMapping("/produk")
public class ProdukController extends LocaleController{
	
	@Autowired
	private ProdukService produkService;
	
	@SuppressWarnings("unchecked")
	@AppPermission(hakAkses="IS_PRINT,IS_ADD")
	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String,Object>> saveProduk(@Valid @RequestBody ProdukVO entity,HttpServletRequest request) {
		Map<String, Object> result = produkService.saveProduk(entity);
		mapHeaderMessage.put(BaseConstant.STATUS, HttpStatus.CREATED.name());
		mapHeaderMessage.put(BaseConstant.STATUS_CODE, HttpStatus.CREATED.toString());
		mapHeaderMessage.put(BaseConstant.MESSAGE, BaseConstant.HttpHeaderInfo.LABEL_SUCCESS);
		return RestUtil.getJsonResponse(result, HttpStatus.CREATED, mapHeaderMessage);
	
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/produks",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> getStrukOrder() {
		Map<String, Object> result = produkService.findAllProduk();
		mapHeaderMessage.put(BaseConstant.STATUS, HttpStatus.OK.name());
		mapHeaderMessage.put(BaseConstant.STATUS_CODE, HttpStatus.OK.toString());
		mapHeaderMessage.put(BaseConstant.MESSAGE, BaseConstant.HttpHeaderInfo.LABEL_SUCCESS);
		return RestUtil.getJsonResponse(result, HttpStatus.OK, mapHeaderMessage);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/produk/{id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> finProdukById(@PathVariable("id") Integer id) {
		Map<String, Object> result = produkService.findById(id);
		mapHeaderMessage.put(BaseConstant.STATUS, HttpStatus.OK.name());
		mapHeaderMessage.put(BaseConstant.STATUS_CODE, HttpStatus.OK.toString());
		mapHeaderMessage.put(BaseConstant.MESSAGE, BaseConstant.HttpHeaderInfo.LABEL_SUCCESS);
		return RestUtil.getJsonResponse(result, HttpStatus.OK, mapHeaderMessage);
	}

}
