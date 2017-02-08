package com.ecomerce.service;

import java.util.Map;

import com.ecomerce.entities.Produk;

/**
 * Agama Service
 * 
 * @author Roberto
 */
public interface ProdukService {

	Map<String, Object> saveProduk(Produk vo);

	Map<String, Object> findByIdProduk();

}
