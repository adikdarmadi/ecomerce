package com.ecomerce.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ecomerce.constants.Constants;
import com.ecomerce.constants.MessageResource;
import com.ecomerce.controller.base.LocaleController;
import com.ecomerce.core.web.WebConstants;
import com.ecomerce.entities.Produk;
import com.ecomerce.exception.ServiceVOException;
import com.ecomerce.service.ProdukService;
import com.ecomerce.util.rest.RestUtil;
import com.ecomerce.vo.ProdukVO;

@SuppressWarnings("rawtypes")
@RestController
@RequestMapping("/produk")
public class ProdukController extends LocaleController{
	
	@Autowired
	private ProdukService produkService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProdukController.class);


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
	public ResponseEntity<Map<String,Object>> getStrukOrder() {
		try {
			Map<String, Object> result = produkService.findByIdProduk();
			mapHeaderMessage.put(WebConstants.STATUS,HttpStatus.OK.name());
			mapHeaderMessage.put(WebConstants.STATUS_CODE,HttpStatus.OK.toString());
			mapHeaderMessage.put(WebConstants.MESSAGE, WebConstants.HttpHeaderInfo.LABEL_SUCCESS);
			return RestUtil.getJsonResponse(result, HttpStatus.OK, mapHeaderMessage);
		} catch (Exception e) {
			System.out.println("error bro"+e.toString());
		}
		return null;
	}

}
