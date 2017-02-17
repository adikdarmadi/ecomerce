package com.ecomerce.service;

import com.ecomerce.entities.LoginUser;
import com.ecomerce.vo.AuthVO;
import com.ecomerce.vo.LoginUserVO;


/**
 * LoginUser Service
 * 
 * @author Adik
 */
public interface LoginUserService{
	
	LoginUserVO signIn(AuthVO authVO);
	
	LoginUser getLoginUser();

}
