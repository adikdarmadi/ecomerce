package com.ecomerce.security;

import io.jsonwebtoken.MalformedJwtException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.ecomerce.constant.SecurityConstant;
import com.ecomerce.util.CommonUtil;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * StatelessAuthenticationFilter class
 * 
 * @author Roberto
 */
public class StatelessAuthenticationFilter extends GenericFilterBean {
	private final TokenAuthenticationService authenticationService;

	public StatelessAuthenticationFilter(
			TokenAuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		res.setHeader("Access-Control-Allow-Origin",
				req.getHeader("Origin"));
		res.setHeader("Access-Control-Allow-Credentials", "true");
		
		res.setHeader("Access-Control-Allow-Methods",
				"POST, GET, OPTIONS, DELETE");
		res.setHeader("Access-Control-Max-Age", "3600");
		res.setHeader("Access-Control-Allow-Headers",
				"Content-Type, Accept, X-Requested-With, remember-me,X-AUTH-TOKEN");

		Authentication authentication = null;
		try {
		
			authentication = authenticationService.getAuthentication(httpRequest);
			if(CommonUtil.isNullOrEmpty(authentication)){
				if(httpRequest.getRequestURI().equalsIgnoreCase("/ecomerce-web/auth/sign-in") || httpRequest.getRequestURI().equalsIgnoreCase("/ecomerce-web/auth/sign-in/")){
					SecurityContextHolder.getContext().setAuthentication(authentication);
					filterChain.doFilter(request, response);
					SecurityContextHolder.getContext().setAuthentication(null);
				}else{
					ObjectMapper mapper = new ObjectMapper();
					String json = "";
					Map<String, Object> map = new HashMap<String, Object>();
					map.put(SecurityConstant.STATUS, HttpStatus.UNAUTHORIZED.name());
					map.put(SecurityConstant.STATUS_CODE, HttpStatus.UNAUTHORIZED.toString());
					map.put(SecurityConstant.MESSAGE, HttpStatus.UNAUTHORIZED.toString());

					// convert map to JSON string
					json = mapper.writeValueAsString(map);


					json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);

					
					res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					res.setContentType("application/json");
					res.getWriter().write(json);
					res.getWriter().flush();
					res.getWriter().close();
				}
				
			}else{
				SecurityContextHolder.getContext().setAuthentication(authentication);
				filterChain.doFilter(request, response);
				SecurityContextHolder.getContext().setAuthentication(null);
			}
		} catch (JsonParseException | MalformedJwtException e) {
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.addHeader(SecurityConstant.MessageInfo.ERROR_MESSAGE,"Error Token (Not Valid Token)");
			filterChain.doFilter(request, response);
		}
	}
}
