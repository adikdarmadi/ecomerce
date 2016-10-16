package com.ecomerce.controller.base;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecomerce.base.vo.BaseVO;

/**
 * Base Rest Operation for Controller Class
 * 
 * @author Roberto
 */
public interface BaseRestController<V extends BaseVO> extends
		RestPageController<V> {

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<V> getVO(@PathVariable("id") String id);
	
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Integer> addVO(@RequestBody V vo);

	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = { "application/json" })
	@ResponseBody
	public ResponseEntity<Integer> editVO(@RequestBody V vo);

	// @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	// @ResponseBody
	// public ResponseEntity<String> deleteVO(@PathVariable("id") Integer id);

	// @RequestMapping(value = "/", method = RequestMethod.GET)
	// @ResponseBody
	// public ResponseEntity<Collection<V>> getAllVO();

	

}
