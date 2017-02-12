package com.ecomerce.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ecomerce.entities.Produk;
import com.ecomerce.entities.StrukOrder;

@Repository("StrukOrderDao")
public interface StrukOrderDao extends PagingAndSortingRepository<StrukOrder, Integer> {

	@Query("Select new Map(produk.id as produk) from StrukOrderDetail strukOrderDetail  join   strukOrderDetail.produk produk ")
	List<Map<String,Object>> listStrukOrder();


}
