package com.ecomerce.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.osgi.service.application.ApplicationException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import scala.collection.immutable.HashMap;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        List<Map<String,Object>> data=new ArrayList<Map<String,Object>>();
        //
        final List<String> errors = new ArrayList<String>();
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            Map<String,Object> rm=new java.util.HashMap<String,Object>();
            rm.put("error",error.getDefaultMessage());
            data.add(rm);
        }
        for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
        	Map<String,Object> rm=new java.util.HashMap<String,Object>();
            rm.put("error",error.getDefaultMessage());
            data.add(rm);
        }
        
   


		
        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, null, data,"400");
        return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(final BindException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //
        List<Map<String,Object>> data=new ArrayList<Map<String,Object>>();
        //
        final List<String> errors = new ArrayList<String>();
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            Map<String,Object> rm=new java.util.HashMap<String,Object>();
            rm.put("error",error.getDefaultMessage());
            data.add(rm);
        }
        for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
        	Map<String,Object> rm=new java.util.HashMap<String,Object>();
            rm.put("error",error.getDefaultMessage());
            data.add(rm);
        }
        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, null, data,"400");
        return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(final TypeMismatchException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //
        final String error = ex.getValue() + " value for " + ex.getPropertyName() + " should be of type " + ex.getRequiredType();
        List<Map<String,Object>> data=new ArrayList<Map<String,Object>>();
        Map<String,Object> rm=new java.util.HashMap<String,Object>();
        rm.put("error",error);
        data.add(rm);

        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, null, data,"400");
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(final MissingServletRequestPartException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        //logger.info(ex.getClass());
        //
        final String error = ex.getRequestPartName() + " part is missing";
        List<Map<String,Object>> data=new ArrayList<Map<String,Object>>();
        Map<String,Object> rm=new java.util.HashMap<String,Object>();
        rm.put("error",error);
        data.add(rm);

        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, null, data,"400");
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(final MissingServletRequestParameterException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //
        final String error = ex.getParameterName() + " parameter is missing";
        List<Map<String,Object>> data=new ArrayList<Map<String,Object>>();
        Map<String,Object> rm=new java.util.HashMap<String,Object>();
        rm.put("error",error);
        data.add(rm);

        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, null, data,"400");
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

 

    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleConstraintViolation(final ConstraintViolationException ex, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //
        final List<String> errors = new ArrayList<String>();
        List<Map<String,Object>> data=new ArrayList<Map<String,Object>>();


        for (final ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            Map<String,Object> rm=new java.util.HashMap<String,Object>();
            rm.put("error", violation.getMessage());
            data.add(rm);
        }

        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, null, data,"400");
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    // 404

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(final NoHandlerFoundException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //
        final String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();

        List<Map<String,Object>> data=new ArrayList<Map<String,Object>>();
        Map<String,Object> rm=new java.util.HashMap<String,Object>();
        rm.put("error",error);
        data.add(rm);
        final ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, null, data,"404");
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    // 405

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(final HttpRequestMethodNotSupportedException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //
        final StringBuilder builder = new StringBuilder();
        builder.append(ex.getMethod());
        builder.append(" method is not supported for this request. Supported methods are ");
        //ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));
        List<Map<String,Object>> data=new ArrayList<Map<String,Object>>();
        Map<String,Object> rm=new java.util.HashMap<String,Object>();
        rm.put("error",builder);
        data.add(rm);

        final ApiError apiError = new ApiError(HttpStatus.METHOD_NOT_ALLOWED, null, data,"405");
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    // 415

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(final HttpMediaTypeNotSupportedException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //
        final StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        //ex.getSupportedMediaTypes().forEach(t -> builder.append(t + " "));
        List<Map<String,Object>> data=new ArrayList<Map<String,Object>>();
        Map<String,Object> rm=new java.util.HashMap<String,Object>();
        rm.put("error",builder);
        data.add(rm);

        final ApiError apiError = new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, null, data,"415");
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }
    

    // "500"

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAll(final Exception ex, final WebRequest request) {
    	final StringBuilder builder = new StringBuilder();
        builder.append(ex.toString());
        //
        List<Map<String,Object>> data=new ArrayList<Map<String,Object>>();
        Map<String,Object> rm=new java.util.HashMap<String,Object>();
        rm.put("error",builder);
        data.add(rm);
        final ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.toString(), data,"500");
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

}
