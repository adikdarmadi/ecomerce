package com.ecomerce.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ecomerce.entities.LoginUser;


/**
 * Repository class for Departemen
 * 
 * @author Roberto
 */
@Repository("LoginUserDao")
public interface LoginUserDao extends PagingAndSortingRepository<LoginUser, Integer> {

	List<LoginUser> findByNamaUser(String username);
	
}
