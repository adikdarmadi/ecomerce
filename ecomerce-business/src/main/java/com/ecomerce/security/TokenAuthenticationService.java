package com.ecomerce.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

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

	public Authentication getAuthentication(HttpServletRequest request)
			throws JsonParseException {
		
		String token = request.getHeader(WebConstants.AUTH_HEADER_NAME);
		if (token != null) {
			final User user = tokenHandler.parseUserFromToken(token);
			if (user != null) {
				return new UserAuthentication(user);
			}
		}
		else{
			try
			{
				if(request.getQueryString()=="")return null;
				final String[] tokens= request.getQueryString().split("&");
				for (String tokenTemp : tokens) {
					if(tokenTemp.toLowerCase().indexOf(WebConstants.AUTH_HEADER_NAME.toLowerCase())>=0)
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
