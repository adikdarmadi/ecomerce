package com.ecomerce.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

import com.ecomerce.constant.SecurityConstant;
import com.fasterxml.jackson.core.JsonParseException;

/**
 * TokenAuthenticationService class
 * 
 * @author Roberto
 */
public class TokenAuthenticationService {

	public  static TokenHandler tokenHandler;
	
	public TokenAuthenticationService() {
	}

	public TokenAuthenticationService(String secret, UserService userService) {
		tokenHandler = new TokenHandler(secret, userService);
	}

	public String addAuthentication(HttpServletResponse response,UserAuthentication authentication) {
		final User user = authentication.getDetails();
 		return tokenHandler.createTokenForUser(user);
	}

	public Boolean cekAuth(HttpServletRequest request)
			throws JsonParseException {
		
		String token = request.getHeader(SecurityConstant.AUTH_HEADER_NAME);
		if (token != null) {
			try {
				final User user = tokenHandler.parseUserFromToken(token);
				if (user != null) {
					return true;
				}
			} catch (Exception e) {
				return false;
			}
			
		}
		else{
			try
			{
				if(request.getQueryString()=="")return null;
				final String[] tokens= request.getQueryString().split("&");
				for (String tokenTemp : tokens) {
					if(tokenTemp.toLowerCase().indexOf(SecurityConstant.AUTH_HEADER_NAME.toLowerCase())>=0)
					{
						token =tokenTemp.split("=")[1];
						final User user = tokenHandler.parseUserFromToken(token);
						if (user != null) {
							return true;
						}
					}
				}
			}
			catch(Exception e)
			{
				return false;
			}
			
		}
		return null;
	}
	
	
	public Authentication getAuthentication(HttpServletRequest request)
			throws JsonParseException {
		
		String token = request.getHeader(SecurityConstant.AUTH_HEADER_NAME);
		if (token != null) {
			try {
				final User user = tokenHandler.parseUserFromToken(token);
				if (user != null) {
					return new UserAuthentication(user);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
			
		} else {
			try
			{
				if(request.getQueryString()=="")return null;
				final String[] tokens= request.getQueryString().split("&");
				for (String tokenTemp : tokens) {
					if(tokenTemp.toLowerCase().indexOf(SecurityConstant.AUTH_HEADER_NAME.toLowerCase())>=0)
					{
						token =tokenTemp.split("=")[1];
						final User user = tokenHandler.parseUserFromToken(token);
						if (user != null) {
							return new UserAuthentication(user);
						}
					}
				}
			}
			catch(Exception e)
			{
				
			}
			
		}
		return null;
	}
}
