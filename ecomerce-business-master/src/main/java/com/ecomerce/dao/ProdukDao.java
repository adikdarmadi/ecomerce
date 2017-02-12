package com.ecomerce.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ecomerce.entities.Produk;

@Repository("ProdukDao")
public interface ProdukDao extends PagingAndSortingRepository<Produk, Integer> {

	@Query("select new map (p.id as id,p.namaProduk as namaProduk) from Produk p  ")
	List<Map<String,Object>> findAllProduk();


}
