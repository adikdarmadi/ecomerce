package com.ecomerce.service;

import java.util.List;
import java.util.Map;

import com.ecomerce.entities.StrukOrder;
import com.ecomerce.vo.StrukOrderVO;

/**
 * Agama Service
 * 
 * @author Adik
 */
public interface StrukOrderService {


	Map<String, Object> saveStrukOrder(StrukOrderVO strukOrderVo);

	Map<String, Object> findAll();

}
