package com.ecomerce.controller.base;

import java.util.List;

import org.springframework.core.GenericTypeResolver;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecomerce.base.vo.BaseModelVO;
import com.ecomerce.constants.Constants;
import com.ecomerce.util.rest.RestUtil;
import com.monitorjbl.json.JsonResult;
import com.monitorjbl.json.JsonView;
import com.monitorjbl.json.Match;

/**
 * Base Controller Class for handling "include" parameter to controller 
 * @see https://github.com/monitorjbl/json-view
 * 
 * @author Roberto
 */
public abstract class ParamRestController<T extends BaseModelVO> {
	private JsonResult jsonResult = JsonResult.instance();

	/*
	 * Belum selesai, baru di parameter includes. Untuk field object juga belom.
	 * example :
	 * http://localhost:8080/ecomerce-web/{typeVOClass}/list-using-param
	 * ?includes =id,nama
	 */
	@RequestMapping(value = "/list-using-param", method = RequestMethod.GET)
	public ResponseEntity<List<T>> listUsingParam(
			@RequestParam(value = "includes", required = false) String includes,
			@RequestParam(value = "excludes", required = false) String excludes) {
		List<T> listVO = getAllVOFromService();
		String[] arrExcludes = null;
		String[] arrIncludes = null;
		if (excludes != null)
			arrExcludes = excludes.split(Constants.COMMA);
		if (includes != null)
			arrIncludes = includes.split(Constants.COMMA);
		if (arrExcludes != null && arrIncludes != null) {
			listVO = jsonResult.use(
					JsonView.with(listVO).onClass(
							getClazz(),
							Match.match().exclude(arrExcludes)
									.include(arrIncludes))).returnValue();
		}
		if (arrExcludes != null && arrIncludes == null) {
			listVO = jsonResult.use(
					JsonView.with(listVO).onClass(getClazz(),
							Match.match().include("*").exclude(arrExcludes)))
					.returnValue();
		}
		if (arrExcludes == null && arrIncludes != null) {
			listVO = jsonResult.use(
					JsonView.with(listVO).onClass(getClazz(),
							Match.match().exclude("*").include(arrIncludes)))
					.returnValue();
		}

		return RestUtil.getJsonResponse(listVO, HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	protected Class<T> getClazz() {
		return (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(),
				ParamRestController.class);
	}

	/*
	 * method untuk mendapatkan all VO from service
	 * */
	protected abstract List<T> getAllVOFromService();
}
