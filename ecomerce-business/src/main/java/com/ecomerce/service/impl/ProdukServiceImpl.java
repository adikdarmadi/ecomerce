package com.ecomerce.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecomerce.dao.ProdukDao;
import com.ecomerce.entities.Produk;
import com.ecomerce.service.ProdukService;

@Service("produkService")
public class ProdukServiceImpl implements ProdukService {

	@Autowired
	private ProdukDao produkDao;

	@Override
	public Map<String,Object> saveProduk(Produk p) {
		Produk produk=produkDao.save(p);
		Map<String,Object> result=new HashMap<String,Object>(); 
		result.put("id", produk.getId());
		result.put("namaProduk", produk.getNamaProduk());
		return result;
	}
	
	@Override
	@Transactional(readOnly=false)
	public Map<String,Object> findByIdProduk() {
		Map<String,Object> result=new HashMap<String,Object>(); 
		result.put("listProduk", produkDao.findAllProduk());
		return result;
	}


}
