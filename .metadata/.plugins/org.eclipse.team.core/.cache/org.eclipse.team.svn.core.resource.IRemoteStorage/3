package com.jasamedika.medifirst2000.controller;

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
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jasamedika.medifirst2000.constants.Constants;
import com.jasamedika.medifirst2000.constants.MessageResource;
import com.jasamedika.medifirst2000.controller.base.LocaleController;
import com.jasamedika.medifirst2000.core.web.WebConstants;
import com.jasamedika.medifirst2000.exception.ServiceVOException;
import com.jasamedika.medifirst2000.service.LimbahB3KeluarService;
import com.jasamedika.medifirst2000.service.LimbahB3MasukService;
import com.jasamedika.medifirst2000.util.rest.RestUtil;
import com.jasamedika.medifirst2000.vo.LimbahB3KeluarVO;

@RestController
@RequestMapping("/limbah-b3-keluar")
public class LimbahB3KeluarController extends LocaleController<LimbahB3KeluarVO> {
	
	@Autowired
	private LimbahB3KeluarService limbahB3KeluarService;
	
	@Autowired
	private LimbahB3MasukService limbahB3MasukService;;
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(LimbahB3KeluarController.class);
	
	@RequestMapping(value = "/save-limbah-b3-keluar/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String,Object>> addVO(@Valid @RequestBody LimbahB3KeluarVO vo,HttpServletRequest request) {
		
		try{
			Map<String, Object> result = limbahB3KeluarService.addLimbahB3Keluar(vo);
			if (null != result)
				mapHeaderMessage.put(WebConstants.HttpHeaderInfo.LABEL_SUCCESS, getMessage(MessageResource.LABEL_SUCCESS,request));
				return RestUtil.getJsonResponse(result, HttpStatus.CREATED, mapHeaderMessage);
		} catch (ServiceVOException e) {
			LOGGER.error("Got exception {} when add limbah b3 keluar", e.getMessage());
			addHeaderMessage(Constants.MessageInfo.ERROR_MESSAGE,
					e.getMessage());
			return RestUtil.getJsonHttptatus(HttpStatus.INTERNAL_SERVER_ERROR,
					mapHeaderMessage);
			
		} catch (JpaSystemException jse) {
			LOGGER.error("Got exception {} when add limbah b3 keluar", jse.getMessage());
			addHeaderMessage(Constants.MessageInfo.ERROR_MESSAGE,
					jse.getMessage());
			return RestUtil.getJsonHttptatus(HttpStatus.CONFLICT,
					mapHeaderMessage);
		}
	}
	
	@RequestMapping(value = "/find-all-limbah-b3-keluar/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<Map<String,Object>> getAllVO(HttpServletRequest request) {
	  
		  try {
			   Map<String,Object> result=limbahB3KeluarService.findAllLimbahB3Keluar();
			   if (null != result)
			   mapHeaderMessage.put(WebConstants.HttpHeaderInfo.LABEL_SUCCESS,getMessage(MessageResource.LABEL_SUCCESS,request ));
			   return RestUtil.getJsonResponse(result, HttpStatus.CREATED,mapHeaderMessage);
		  } catch (ServiceVOException e) {
			   LOGGER.error("Got exception {} when get all limbah b3 keluar", e.getMessage());
			   addHeaderMessage(Constants.MessageInfo.ERROR_MESSAGE,
			     e.getMessage());
			   return RestUtil.getJsonHttptatus(HttpStatus.INTERNAL_SERVER_ERROR,
			     mapHeaderMessage);
		  } catch (JpaSystemException jse) {
			   LOGGER.error("Got exception {} when get all limbah b3 keluar", jse.getMessage());
			   addHeaderMessage(Constants.MessageInfo.ERROR_MESSAGE,
			     jse.getMessage());
			   return RestUtil.getJsonHttptatus(HttpStatus.CONFLICT,
			     mapHeaderMessage);
		  }
	 }
	
	@RequestMapping(value = "/delete-limbah-b3-keluar", method = RequestMethod.GET)
	public ResponseEntity<String> deleteNamaBahan(@RequestParam(value = "noRec", required = true) String noRec) {
		
		try {
			if (limbahB3KeluarService.deleteLimbahB3Keluar(noRec) == true)
				return RestUtil.getJsonResponse(noRec +" Dihapus", HttpStatus.CREATED);

		} catch (ServiceVOException e) {
			LOGGER.error("Got exception {} when delete Limbah b3 keluar", e.getMessage());
			addHeaderMessage(Constants.MessageInfo.ERROR_MESSAGE,
					e.getMessage());
			return RestUtil.getJsonHttptatus(HttpStatus.INTERNAL_SERVER_ERROR,
					mapHeaderMessage);
		} catch (JpaSystemException jse) {
			LOGGER.error("Got exception {} when delete Limbah b3 keluar", jse.getMessage());
			addHeaderMessage(Constants.MessageInfo.ERROR_MESSAGE,
					jse.getMessage());
			return RestUtil.getJsonHttptatus(HttpStatus.CONFLICT,
					mapHeaderMessage);
		}

		return RestUtil.getJsonHttptatus(HttpStatus.NOT_ACCEPTABLE);
	}
	
	@RequestMapping(value = "/find-by-periode", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> findDaftarOrderPemakaianRuangRapatByPeriode(
			@RequestParam(value = "periodeAwal", required = true) String periodeAwal,
			@RequestParam(value = "periodeAkhir", required = true) String periodeAkhir, HttpServletRequest request) {

		Map<String, Object> result = limbahB3KeluarService
				.findLimbahB3KeluarByPeriode(periodeAwal, periodeAkhir);
		Boolean dataFound = new Boolean((boolean) result.get("dataFound"));
		if (dataFound) {
			mapHeaderMessage.put(WebConstants.HttpHeaderInfo.LABEL_SUCCESS,
					getMessage(MessageResource.LABEL_SUCCESS, request));
		} else {
			mapHeaderMessage.put(WebConstants.HttpHeaderInfo.LABEL_ERROR,
					getMessage(MessageResource.LABEL_ERROR, request));
		}
		return RestUtil.getJsonResponse(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/get-total-limbah-by-periode/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Double>> getTotalByPeriode(
			@RequestParam(value = "periodeAwal", required = true) String periodeAwal,
			@RequestParam(value = "periodeAkhir", required = true) String periodeAkhir,
			@RequestParam(value = "jenisLibahB3MasukId", required = true) Integer jenisLibahB3MasukId,HttpServletRequest request) {
	
		  try {
			   Map<String,Double> result=limbahB3MasukService.getTotalBeratLimbah(periodeAwal, periodeAkhir, jenisLibahB3MasukId);
			   if (null != result)
			   mapHeaderMessage.put(WebConstants.HttpHeaderInfo.LABEL_SUCCESS,getMessage(MessageResource.LABEL_SUCCESS,request ));
			   return RestUtil.getJsonResponse(result, HttpStatus.CREATED,mapHeaderMessage);
		  } catch (ServiceVOException e) {
			   LOGGER.error("Got exception {} when get total LimbahB3Masuk", e.getMessage());
			   addHeaderMessage(Constants.MessageInfo.ERROR_MESSAGE,
			     e.getMessage());
			   return RestUtil.getJsonHttptatus(HttpStatus.INTERNAL_SERVER_ERROR,
			     mapHeaderMessage);
		  } catch (JpaSystemException jse) {
			   LOGGER.error("Got exception {} when get total LimbahB3Masuk", jse.getMessage());
			   addHeaderMessage(Constants.MessageInfo.ERROR_MESSAGE,
			     jse.getMessage());
			   return RestUtil.getJsonHttptatus(HttpStatus.CONFLICT,
			     mapHeaderMessage);
		  }
	 }
	
	
	@RequestMapping(value = "/get-total-limbah-by-periode-list/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Map<String, Object>>> getTotalByPeriodeList(
			@RequestParam(value = "periodeAwal", required = true) String periodeAwal,
			@RequestParam(value = "periodeAkhir", required = true) String periodeAkhir,HttpServletRequest request) {
	
		  try {
			  List<Map<String, Object>> result=limbahB3MasukService.getTotalBeratLimbahList(periodeAwal, periodeAkhir);
			   if (null != result)
			   mapHeaderMessage.put(WebConstants.HttpHeaderInfo.LABEL_SUCCESS,getMessage(MessageResource.LABEL_SUCCESS,request ));
			   return RestUtil.getJsonResponse(result, HttpStatus.CREATED,mapHeaderMessage);
		  } catch (ServiceVOException e) {
			   LOGGER.error("Got exception {} when get total LimbahB3Masuk", e.getMessage());
			   addHeaderMessage(Constants.MessageInfo.ERROR_MESSAGE,
			     e.getMessage());
			   return RestUtil.getJsonHttptatus(HttpStatus.INTERNAL_SERVER_ERROR,
			     mapHeaderMessage);
		  } catch (JpaSystemException jse) {
			   LOGGER.error("Got exception {} when get total LimbahB3Masuk", jse.getMessage());
			   addHeaderMessage(Constants.MessageInfo.ERROR_MESSAGE,
			     jse.getMessage());
			   return RestUtil.getJsonHttptatus(HttpStatus.CONFLICT,
			     mapHeaderMessage);
		  }
	 }
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/list-limbah-b3-keluar")
	public Map<String,Object>  lisLimbahB3Masuk(
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "take", required = false, defaultValue = "100") Integer limit,
			@RequestParam(value = "sort", required = false, defaultValue = "noRec") String sort,
			@RequestParam(value = "dir", required = false, defaultValue = "asc") String dir,
			@RequestParam(value = "dateStart", required = false) String dateStart,
			@RequestParam(value = "dateEnd", required = false) String dateEnd,
			@RequestParam(value = "jenisLimbah", required = false) Integer jenisLimbah) {

		Map<String, Object> resultPageMap = limbahB3KeluarService.lisLimbahB3Keluar(page, limit, sort, dir, dateStart, dateEnd, jenisLimbah);
		
		return resultPageMap;
	}

}
