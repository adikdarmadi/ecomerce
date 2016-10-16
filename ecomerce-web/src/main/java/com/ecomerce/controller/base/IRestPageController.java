package com.ecomerce.controller.base;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecomerce.base.vo.BaseModelVO;

/**
 * Base Rest Operation for 'pagination' Controller Class
 * 
 * @author Roberto
 */
public interface IRestPageController<V extends BaseModelVO> {
	/**
	 *
	 * @param page
	 *            : page of
	 * @param limit
	 *            : limit query
	 * @param sort
	 *            : sort by
	 * @param dir
	 *            : direction {asc:desc}
	 * @return Collection of VO, Total-Count & Total Pages on response header
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Collection<V>> getAllVOWithQueryString(
			 HttpServletRequest request,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "sort", required = false, defaultValue = "id") String sort,
			@RequestParam(value = "dir", required = false, defaultValue = "asc") String dir);
}
