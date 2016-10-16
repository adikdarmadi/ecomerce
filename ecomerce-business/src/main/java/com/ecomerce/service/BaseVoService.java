package com.ecomerce.service;

import java.util.List;
import java.util.Map;

import org.springframework.orm.jpa.JpaSystemException;

import com.ecomerce.exception.ServiceVOException;

/**
 * View Object service
 * 
 * @param <T>
 *            Model
 * @param <V>
 *            View Object
 * @param <K>
 *            Key
 * @author Roberto
 */
public interface BaseVoService<T, V, K> {
	
	/**
	 * Add model from VO
	 * 
	 * @param vo
	 * @return
	 */
	public V add(V vo) throws JpaSystemException, ServiceVOException;

	/**
	 * Update model from vo
	 * 
	 * @param vo
	 * @return
	 */
	public V update(V vo) throws JpaSystemException, ServiceVOException;

	/**
	 * Delete model by primary key
	 * 
	 * @param key
	 * @return
	 */
	public Boolean delete(K key) throws JpaSystemException;

	/**
	 * Find by Id
	 * 
	 * @param key
	 *            primary key
	 * @return
	 */
	public V findById(K key) throws JpaSystemException;

	/**
	 * Find all model to vo
	 * 
	 * @return
	 */
	public List<V> findAll() throws JpaSystemException;

	/**
	 * Find all model to vo by page and limit and sort and dir parameter
	 * 
	 * @return
	 */
	public Map<String, Object> findAllWithPageAndLimitAndSortByAndDirectionParameter(
			Integer page, Integer limit, String sort, String dir);

}
