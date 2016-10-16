package com.ecomerce.controller.base;

import java.util.Collection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecomerce.base.vo.BaseVO;

/**
 * Base Rest Operation for 'pagination' Controller Class
 * 
 * @author Roberto
 */
public interface RestPageController<V extends BaseVO> {
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
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "sort", required = false, defaultValue = "id") String sort,
			@RequestParam(value = "dir", required = false, defaultValue = "asc") String dir);
}
