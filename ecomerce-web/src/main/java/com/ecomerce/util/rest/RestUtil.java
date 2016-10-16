package com.ecomerce.util.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.google.gson.Gson;

/**
 * Rest Util Class
 * 
 * @author Roberto
 */
public class RestUtil {

	private static final String CONTENT_TYPE = "Content-Type";
	private static final String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";

	/**
	 * get JSON response from Object
	 * 
	 * @param src
	 * @param <T>
	 *            source class
	 * @return @ResponseEntity
	 */
	public static <T> ResponseEntity<T> getJsonResponse(T src) {

		HttpHeaders headers = new HttpHeaders();
		//headers.add(ACCESS_CONTROL_ALLOW_ORIGIN, "*");
		headers.set(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		return new ResponseEntity<T>(src, headers, HttpStatus.OK);
	}

	/**
	 * get JSON response from Object with HTTP status
	 * 
	 * @param src
	 * @param <T>
	 *            source class
	 * @return @ResponseEntity
	 */
	public static <T> ResponseEntity<T> getJsonResponse(T src, HttpStatus status) {
		
		HttpHeaders headers = new HttpHeaders();
//		headers.add(ACCESS_CONTROL_ALLOW_ORIGIN, "*");
		headers.set(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("data", src);
		return new ResponseEntity(map, headers, status);
	}

	/**
	 *
	 * @param src
	 * @param status
	 * @param mapHeaderMessage
	 * @param <T>
	 * @return
	 */
	public static <T> ResponseEntity<T> getJsonResponse(T src,
		HttpStatus status, Map<String, String> mapHeaderMessage) {
		

		HttpHeaders headers = new HttpHeaders();

		if (null != mapHeaderMessage) {
			for (String key : mapHeaderMessage.keySet()) {
				headers.add(key, mapHeaderMessage.get(key));
			}
		}

		Map<String,Object> map=new HashMap<String,Object>();
		map.put("messages", mapHeaderMessage);
		map.put("data", src);
		//headers.add(ACCESS_CONTROL_ALLOW_ORIGIN, "*");
		headers.set(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		return new ResponseEntity(map, headers, status);
	}

	/**
	 * get JSON response from HTTP status only
	 * 
	 * @param <T>
	 *            source class
	 * @return @ResponseEntity
	 */
	public static <T> ResponseEntity<T> getJsonHttptatus(HttpStatus status) {

		return new ResponseEntity<T>(status);

	}

	public static <T> ResponseEntity<T> getJsonHttptatus(HttpStatus status,
			Map<String, String> mapHeaderMessage) {

		HttpHeaders headers = new HttpHeaders();

		if (null != mapHeaderMessage) {
			for (String key : mapHeaderMessage.keySet()) {
				headers.add(key, mapHeaderMessage.get(key));
			}
		}

		headers.set(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		return new ResponseEntity<T>(headers, status);

	}

	/**
	 * get JSON response from List Object
	 * 
	 * @param src
	 * @param <T>
	 *            source class
	 * @return @ResponseEntity
	 */
	public static <T> ResponseEntity<List<T>> defaultJsonResponse(List<T> src) {

		HttpHeaders headers = new HttpHeaders();
//		headers.add(ACCESS_CONTROL_ALLOW_ORIGIN, "*");
		headers.set(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		return new ResponseEntity<List<T>>(src, headers, HttpStatus.OK);
	}

	/**
	 * get default JSON response from Object
	 * 
	 * @param src
	 * @return @ResponseEntity
	 */
	public static ResponseEntity<String> defaultJsonResponse(Object src) {

		Gson gson = new Gson();

		HttpHeaders headers = new HttpHeaders();
//		 headers.add(ACCESS_CONTROL_ALLOW_ORIGIN, "*");
		headers.set(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		return new ResponseEntity<String>(gson.toJson(src), headers,
				HttpStatus.OK);
	}

	/**
	 * convert JSON to Object
	 *
	 * @param strJson
	 *            string source JSON
	 * @param type
	 *            class type result
	 * @param <T>
	 * @return
	 */
	public static <T> T jsonToObject(String strJson, Class<T> type) {
		Gson gson = new Gson();
		return gson.fromJson(strJson, type);
	}

	/**
	 * convert object to json
	 *
	 * @param object
	 * @return
	 */
	public static String toJson(Object object) {
		Gson gson = new Gson();
		return gson.toJson(object);
	}

}
