package com.ecomerce.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ecomerce.constant.BaseConstant;
import com.ecomerce.controller.base.LocaleController;
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
	@RequestMapping(value = "/save-produk/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String,Object>> saveProduk(@Valid @RequestBody ProdukVO entity,HttpServletRequest request) {
			Map<String,Object> result=produkService.saveProduk(entity);
			/*mapHeaderMessage.put(WebConstants.STATUS,HttpStatus.CREATED.name());
			mapHeaderMessage.put(WebConstants.STATUS_CODE,HttpStatus.CREATED.toString());
			mapHeaderMessage.put(WebConstants.MESSAGE, WebConstants.HttpHeaderInfo.LABEL_SUCCESS);*/
			return RestUtil.getJsonResponse(result, HttpStatus.CREATED,mapHeaderMessage);
		
	}
	

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/kartu-pengendali-list",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> getStrukOrder() {
		Map<String, Object> result = produkService.findByIdProduk();
		mapHeaderMessage.put(BaseConstant.STATUS, HttpStatus.OK.name());
		mapHeaderMessage.put(BaseConstant.STATUS_CODE, HttpStatus.OK.toString());
		mapHeaderMessage.put(BaseConstant.MESSAGE, BaseConstant.HttpHeaderInfo.LABEL_SUCCESS);
		return RestUtil.getJsonResponse(result, HttpStatus.OK, mapHeaderMessage);
	}

}
