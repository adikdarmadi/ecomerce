package com.ecomerce.service.impl;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ecomerce.dao.LoginUserDao;
import com.ecomerce.entities.LoginUser;
import com.ecomerce.service.LoginUserService;
import com.ecomerce.util.PasswordUtil;
import com.ecomerce.vo.AuthVO;
import com.ecomerce.vo.LoginUserVO;

/**
 * Implement class for LoginUserService
 * 
 * @author Roberto
 */
@Service("LoginUserService")
public class LoginUserServiceImpl implements LoginUserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginUserServiceImpl.class);

	@Autowired
	private LoginUserDao loginUserDao;

	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public LoginUserVO signIn(AuthVO authVO) throws Exception {

		List<LoginUser> loginUsers = loginUserDao.findByNamaUser(authVO.getNamaUser());
		if (!loginUsers.isEmpty()) {

			LoginUser loginUser = loginUsers.get(0);
			PasswordUtil passwordUtil = new PasswordUtil();
			Boolean isValidPassword = false;
			try {
				isValidPassword = passwordUtil.isPasswordEqual(authVO.getKataSandi(), loginUser.getKataSandi());
			} catch (IOException ioe) {
				LOGGER.error("Password not match : " + ioe.getMessage());
				return null;
			}

			if (!isValidPassword) {
				return null;
			}
			// to do validasi yang advanced di sini

			LoginUserVO vo = new LoginUserVO();
			vo.setNamaUser(authVO.getNamaUser());
			vo.setKataSandi(authVO.getKataSandi());
			return vo;

		} else {
			throw new RuntimeException("User account is unauthorized : username : " + authVO.getNamaUser());
		}
	}
	
	
}
