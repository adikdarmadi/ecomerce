package com.ecomerce.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecomerce.entities.Agama;

@Repository("AgamaDao")
public interface AgamaDao extends PagingAndSortingRepository<Agama, Integer> {

	@Query("select model from Agama model where model.id=:id")
	Agama findByIdAgama(@Param("id") Integer id);

}
