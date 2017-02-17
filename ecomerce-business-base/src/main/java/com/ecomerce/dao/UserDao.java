package com.ecomerce.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ecomerce.entities.LoginUser;


/**
 * Repository class for Departemen
 * 
 * @author Adik
 */
@Repository("UserDao")
public interface UserDao extends PagingAndSortingRepository<LoginUser, Integer> {

	List<LoginUser> findByNamaUser(String username);
	
}
