package com.ecomerce.converter.base;

import java.util.List;

import com.ecomerce.base.BaseModel;

/**
 * Base Converter Class between V and VO
 * 
 * @author Roberto
 */
public interface BaseVoConverter<V, T extends BaseModel> {

	/**
	 * transfer value from vo object to domain object for enum value, please do
	 * manually using Enum.values()[ordinal]
	 * 
	 * @param vo
	 * @param model
	 * @return
	 */
	public T transferVOToModel(V vo, T model);

	/**
	 * transfer value from list of domain object to list of vo object
	 * 
	 * @param models
	 * @param vos
	 * @return
	 */
	public List<V> transferListOfModelToListOfVO(List<T> models, List<V> vos);

	/**
	 * transfer value from domain object to vo object
	 * 
	 * @param model
	 * @param vo
	 * @return
	 */
	public V transferModelToVO(T model, V vo);
}
