package com.ecomerce.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecomerce.security.TokenAuthenticationService;
import com.ecomerce.security.UserAuthentication;
import com.ecomerce.service.LoginUserService;
import com.ecomerce.util.DateUtil;
import com.ecomerce.util.rest.RestUtil;
import com.ecomerce.vo.AuthVO;
import com.ecomerce.vo.LoginUserVO;

/**
 * Controller class for Authenticate Business
 * 
 * @author Roberto
 */
@RestController
@RequestMapping("/auth")
public class AuthenticateController {

	@Autowired
	private LoginUserService loginUserService;
	protected Map<String, String> mapHeaderMessage = new HashMap<String, String>();
	
	@Autowired
	public TokenAuthenticationService tokenAuthenticationService;

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticateController.class);
	
	
	@RequestMapping(value = "/sign-in", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<LoginUserVO> signIn(@RequestBody AuthVO vo, HttpServletRequest request, HttpServletResponse httpResponse) {

		if (vo.getNamaUser() == null || vo.getKataSandi() == null) {
			return RestUtil.getJsonHttptatus(HttpStatus.BAD_REQUEST);
		}

		LOGGER.info("starting logging {}", vo.getNamaUser() + " at " + DateUtil.getIndonesianStringDate(new Date()));

		try {
			mapHeaderMessage = new HashMap<String, String>();
			LoginUserVO loginUserVo = loginUserService.signIn(vo);
			if (loginUserVo == null) {
				return RestUtil.getJsonHttptatus(HttpStatus.NOT_ACCEPTABLE);
			}
			GrantedAuthority authority = new SimpleGrantedAuthority("USER");
			String token = tokenAuthenticationService.addAuthentication(httpResponse, new UserAuthentication(
					new User(loginUserVo.getNamaUser(), loginUserVo.getKataSandi(), Arrays.asList(authority))));

			mapHeaderMessage.put("X-AUTH-TOKEN", token);

			return RestUtil.getJsonResponse(loginUserVo, HttpStatus.OK, mapHeaderMessage);

		} catch (Exception ex) {
			LOGGER.error("Signing-in error {}", ex.getMessage());
			return RestUtil.getJsonHttptatus(HttpStatus.UNAUTHORIZED);
		}
	}
	


	@RequestMapping(value = "/sign-out", method = RequestMethod.POST)
	@ResponseBody
	public void signOut(@RequestBody AuthVO vo) {

		LOGGER.info("starting logout{}", vo.getNamaUser() + " at " + DateUtil.getIndonesianStringDate(new Date()));

		// misal call service logout dan seterusnya
		// Karena Stateless tidak perlu set " session user " menjadi tidak
		// aktif, return HttpStatus.OK ke client
		RestUtil.getJsonHttptatus(HttpStatus.OK);
	}

}
