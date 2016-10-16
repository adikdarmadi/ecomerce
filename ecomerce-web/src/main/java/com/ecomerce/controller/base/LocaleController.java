package com.ecomerce.controller.base;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.ecomerce.base.vo.BaseModelVO;
import com.ecomerce.constants.Constants;
import com.ecomerce.core.web.WebConstants;
import com.ecomerce.service.AgamaService;
import com.ecomerce.service.ModelService;
import com.ecomerce.util.rest.RestUtil;
import com.ecomerce.vo.ModelVO;

import io.socket.client.IO;

/**
 * Base Controller Class for handling messaga resource for internationalization
 * & locale
 * 
 * @author Roberto
 */
public abstract class LocaleController<V extends BaseModelVO> {
	/*
	 * messageSource bean injected for each controller for accessing message
	 * source
	 */
	@Autowired
	private MessageSource messageSource;


	protected Map<String, String> mapHeaderMessage = new HashMap<String, String>();

	/*
	 * code locale
	 */
	protected String getMessage(String code, HttpServletRequest request) {
		return messageSource.getMessage(code, null, new Locale(getCoociesLanguage(request)));
	}

	@RequestMapping(value = "/lang/{lang}", method = RequestMethod.GET)
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("lang") String lang) throws Exception {

		Cookie[] cookies = request.getCookies();
		List<Cookie> cookieList = new ArrayList<Cookie>();
		if (cookies != null) {
			cookieList = Arrays.asList(cookies);
			for (Cookie cookie : cookieList) {
				if (cookie.getName().equals("lang")) {
					cookie.setValue(null);
					cookie.setMaxAge(0);
					cookie.setPath("/");
					response.addCookie(cookie);
				}
			}
		}

		if (lang.equalsIgnoreCase("en")) {
			Cookie c = new Cookie("lang", "en");
			c.setPath("/");
			c.setDomain("localhost");
			response.addCookie(c);
		} else if (lang.equalsIgnoreCase("cn")) {
			Cookie c = new Cookie("lang", "cn");
			c.setPath("/");
			c.setDomain("localhost");
			response.addCookie(c);
		} else if (lang.equalsIgnoreCase("ina")) {
			Cookie c = new Cookie("lang", "ina");
			c.setPath("/");
			c.setDomain("localhost");
			response.addCookie(c);
		}

		return true;
	}

	/*
	 * default locale ID
	 */
	protected String getMessage(String code) {
		return messageSource.getMessage(code, null, new Locale(Constants.Locale.INA));
	}

	protected void addHeaderMessage(String key, String message) {
		this.mapHeaderMessage.put(key, message);
	}


	public String getCoociesLanguage(HttpServletRequest request) {
		Cookie cookie[] = request.getCookies();
		Cookie cook;
		String lang = Constants.Locale.INA;
		if (cookie != null) {
			for (int i = 0; i < cookie.length; i++) {
				cook = cookie[i];
				if (cook.getName().equalsIgnoreCase("lang"))
					lang = cook.getValue();
			}
		}

		return lang;

	}

	@SuppressWarnings("rawtypes")
	protected ResponseEntity constructListPageResult(Map<String, Object> map) {

		Map<String, String> mapHeaderMessage = new HashMap<String, String>();
		mapHeaderMessage.put(WebConstants.HttpHeaderInfo.TOTAL_PAGE_HEADER, "0");
		mapHeaderMessage.put(WebConstants.HttpHeaderInfo.TOTAL_COUNT_HEADER, "0");
		mapHeaderMessage.put(Constants.MessageInfo.ERROR_MESSAGE, "Data not found.");
		
		return RestUtil.getJsonResponse(null, HttpStatus.BAD_REQUEST, mapHeaderMessage);
	}

//	@PersistenceContext
//	protected EntityManager em;


}
