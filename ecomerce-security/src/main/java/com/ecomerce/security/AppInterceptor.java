package com.ecomerce.security;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ecomerce.constant.SecurityConstant;
import com.ecomerce.dao.LoginUserDao;
import com.ecomerce.entities.LoginUser;

/**
 * Interceptor class for All annotation method controller @AppPermission
 * 
 * @author Roberto
 */
public class AppInterceptor implements HandlerInterceptor {
	
	private final Logger LOG = LoggerFactory.getLogger(AppInterceptor.class);
	
	@Autowired
	private TokenAuthenticationService tokenAuthenticationService;
	
	@Autowired
	private LoginUserDao loginUserDao;
	
//	@Autowired
//	private MapLoginUserToRuanganDao mapLoginUserToRuanganDao;
	
	public AppInterceptor() {}
	
	/*
	 * return true untuk valid permission request ke controller method
	 * */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		try {
			HandlerMethod hm = (HandlerMethod) handler;
			Method method = hm.getMethod();

			if (method.isAnnotationPresent(AppPermission.class)) {
				// TODO: get module name, form name and form action within
				// attribute/value from request header

				String methodName = method.getAnnotation(AppPermission.class).value();
				Boolean cekAuth = tokenAuthenticationService.cekAuth(request);
				if(cekAuth){
					Authentication authentication = tokenAuthenticationService.getAuthentication(request);
					String namaUser = authentication.getName();
					List<LoginUser> loginUser = loginUserDao.findByNamaUser(namaUser);
					if (loginUser.isEmpty()) {
						// untuk testing false
						response.addHeader(SecurityConstant.MessageInfo.ERROR_MESSAGE,
								"User " + namaUser + " can not access Controller " + methodName);
						response.setStatus(HttpServletResponse.SC_FORBIDDEN);
						return false;
					}

					// get user login
					if (!loginUser.isEmpty()) {
						LoginUser user = loginUser.get(0);

						return true;
					}
				}else{
					response.addHeader(SecurityConstant.MessageInfo.ERROR_MESSAGE,
							"User  can not access Controller " + methodName);
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					return false;
				}
			}
		} catch (ClassCastException e) {
			System.out.println("Not Found "+request.getRequestURL());
		}
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object obj, Exception ex)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object obj, ModelAndView mav) throws Exception {
	}

}