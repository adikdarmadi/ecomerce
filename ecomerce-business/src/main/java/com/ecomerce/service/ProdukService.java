package com.ecomerce.service;

import java.util.Map;

import com.ecomerce.entities.Produk;
import com.ecomerce.vo.ProdukVO;

/**
 * Agama Service
 * 
 * @author Roberto
 */
public interface ProdukService {

	Map<String, Object> findByIdProduk();

	Map<String, Object> saveProduk(ProdukVO p);

}