package com.ecomerce.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
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

import com.ecomerce.constants.Constants;
import com.ecomerce.constants.MessageResource;
import com.ecomerce.controller.base.IBaseRestController;
import com.ecomerce.controller.base.LocaleController;
import com.ecomerce.core.web.WebConstants;
import com.ecomerce.entities.Agama;
import com.ecomerce.exception.ServiceVOException;
import com.ecomerce.service.AgamaService;
import com.ecomerce.util.rest.RestUtil;
import com.ecomerce.vo.AgamaVO;
import com.ecomerce.vo.TestUploadVO;

@RestController
@RequestMapping("/agama")
public class AgamaController extends LocaleController<AgamaVO> implements
IBaseRestController<AgamaVO>  {
	
	@Autowired
	private AgamaService agamaService;
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(AgamaController.class);

	@Override
	public ResponseEntity<Collection<AgamaVO>> getAllVOWithQueryString(HttpServletRequest request, Integer page,
			Integer limit, String sort, String dir) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<AgamaVO> getVO(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@RequestMapping(value = "/save-agama/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addVO(@Valid @RequestBody AgamaVO vo,HttpServletRequest request) {
		try {
			AgamaVO result = (AgamaVO) agamaService.add(vo); 

			if (null != result)				
				mapHeaderMessage.put(WebConstants.HttpHeaderInfo.LABEL_SUCCESS,getMessage(MessageResource.LABEL_SUCCESS,request ));
				return RestUtil.getJsonResponse("", HttpStatus.CREATED,mapHeaderMessage);
		} catch (ServiceVOException e) {
			LOGGER.error("Got exception {} when add Agama", e.getMessage());
			addHeaderMessage(Constants.MessageInfo.ERROR_MESSAGE,
					e.getMessage());
			return RestUtil.getJsonHttptatus(HttpStatus.INTERNAL_SERVER_ERROR,
					mapHeaderMessage);
		} catch (JpaSystemException jse) {
			LOGGER.error("Got exception {} when add Agama", jse.getMessage());
			addHeaderMessage(Constants.MessageInfo.ERROR_MESSAGE,
					jse.getMessage());
			return RestUtil.getJsonHttptatus(HttpStatus.CONFLICT,
					mapHeaderMessage);
		}
		
	}

	@RequestMapping(value = "/update-agama/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> editVO(@Valid @RequestBody AgamaVO vo) {
		try {
			AgamaVO result = (AgamaVO) agamaService.update(vo);

			if (null != result)
				return RestUtil.getJsonResponse("", HttpStatus.OK);
		} catch (ServiceVOException e) {
			LOGGER.error("Got exception {} when update Agama", e.getMessage());
			addHeaderMessage(Constants.MessageInfo.ERROR_MESSAGE,
					e.getMessage());
			return RestUtil.getJsonHttptatus(HttpStatus.INTERNAL_SERVER_ERROR,
					mapHeaderMessage);
		} catch (JpaSystemException jse) {
			LOGGER.error("Got exception {} when update Agama",
					jse.getMessage());
			addHeaderMessage(Constants.MessageInfo.ERROR_MESSAGE,
					jse.getMessage());
			return RestUtil.getJsonHttptatus(HttpStatus.CONFLICT,
					mapHeaderMessage);
		}

		return RestUtil.getJsonHttptatus(HttpStatus.NOT_ACCEPTABLE);
	}

	@RequestMapping(value = "/delete-agama/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteVO(Integer id) {
		try {
			if (agamaService.delete(id) == true)
				return RestUtil.getJsonResponse("", HttpStatus.CREATED);

		} catch (ServiceVOException e) {
			LOGGER.error("Got exception {} when delete Agama", e.getMessage());
			addHeaderMessage(Constants.MessageInfo.ERROR_MESSAGE,
					e.getMessage());
			return RestUtil.getJsonHttptatus(HttpStatus.INTERNAL_SERVER_ERROR,
					mapHeaderMessage);
		} catch (JpaSystemException jse) {
			LOGGER.error("Got exception {} when delete Agama", jse.getMessage());
			addHeaderMessage(Constants.MessageInfo.ERROR_MESSAGE,
					jse.getMessage());
			return RestUtil.getJsonHttptatus(HttpStatus.CONFLICT,
					mapHeaderMessage);
		}

		return RestUtil.getJsonHttptatus(HttpStatus.NOT_ACCEPTABLE);
	}

	@Override
	public ResponseEntity<List<AgamaVO>> getAllVO() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/search-agama", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<AgamaVO>> getAllVOWithQueryString(
			@RequestParam(value = "page", required = false ,defaultValue = "0") Integer page,
			@RequestParam(value = "limit", required = false ,defaultValue = "10") Integer limit,
			@RequestParam(value = "sort", required = false, defaultValue = "id") String sort,
			@RequestParam(value = "dir", required = false, defaultValue = "asc") String dir){
		Map<String, Object> resultPageMap = agamaService.findAllWithPageAndLimitAndSortByAndDirectionParameter(page,limit, sort, dir);
		
		return constructListPageResult(resultPageMap);
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/find-agama", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getAllVOWithQueryString(@RequestParam(value = "id", required = true) Integer id){
		Agama agama=agamaService.findByIdAgama(id);

		return RestUtil.getJsonResponse(agama.getAgama(), HttpStatus.OK);
	}
	

	@Override
	public ResponseEntity<String> addVO(AgamaVO vo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@RequestMapping(value = "/upload/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> upload(@Valid @RequestBody TestUploadVO vo,HttpServletRequest request) throws IOException {
		try {
			TestUploadVO result = (TestUploadVO) agamaService.upload(vo); 

			if (null != result)				
				mapHeaderMessage.put(WebConstants.HttpHeaderInfo.LABEL_SUCCESS,getMessage(MessageResource.LABEL_SUCCESS,request ));
				return RestUtil.getJsonResponse("", HttpStatus.CREATED,mapHeaderMessage);
		} catch (ServiceVOException e) {
			LOGGER.error("Got exception {} when add Agama", e.getMessage());
			addHeaderMessage(Constants.MessageInfo.ERROR_MESSAGE,
					e.getMessage());
			return RestUtil.getJsonHttptatus(HttpStatus.INTERNAL_SERVER_ERROR,
					mapHeaderMessage);
		} catch (JpaSystemException jse) {
			LOGGER.error("Got exception {} when add Agama", jse.getMessage());
			addHeaderMessage(Constants.MessageInfo.ERROR_MESSAGE,
					jse.getMessage());
			return RestUtil.getJsonHttptatus(HttpStatus.CONFLICT,
					mapHeaderMessage);
		}
		
	}

}
