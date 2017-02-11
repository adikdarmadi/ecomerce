package com.ecomerce.service;

import com.ecomerce.vo.AuthVO;
import com.ecomerce.vo.LoginUserVO;


/**
 * LoginUser Service
 * 
 * @author Roberto
 */
public interface LoginUserService{
	
	LoginUserVO signIn(AuthVO authVO) throws Exception;

}
