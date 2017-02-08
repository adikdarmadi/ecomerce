package com.ecomerce.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ecomerce.entities.Produk;

@Repository("ProdukDao")
public interface ProdukDao extends PagingAndSortingRepository<Produk, Integer> {

	@Query("select new map (p as produk,detailJenisProduk as detailJenisProduk,jenisProduk as jenisProduk) from Produk p left join  p.detailJenisProduk detailJenisProduk left join  detailJenisProduk.jenisProduk jenisProduk ")
	List<Map<String,Object>> findAllProduk();


}
