package com.ecomerce.handler;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ecomerce.base.vo.validation.ValidationErrorVO;
import com.ecomerce.constants.Constants;

/**
 * Exception Handler Rest Controller class
 * 
 * @author Roberto
 */

@ControllerAdvice
public class RestErrorHandler {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(RestErrorHandler.class);

	private MessageSource messageSource;	

	@Autowired
	public RestErrorHandler(MessageSource validationNessageSource) {
		this.messageSource = validationNessageSource;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ValidationErrorVO processValidationError(
			MethodArgumentNotValidException ex) {
		LOGGER.warn("Handling data validation error");
		BindingResult result = ex.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();

		return processFieldErrors(fieldErrors);
	}

	private ValidationErrorVO processFieldErrors(List<FieldError> fieldErrors) {
		ValidationErrorVO dto = new ValidationErrorVO();

		for (FieldError fieldError : fieldErrors) {
			String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError);
			dto.addFieldError(fieldError.getField(), localizedErrorMessage);
		}

		return dto;
	}
	
	/*
	 * resolve error message with parameter locale
	 */
	private String resolveLocalizedErrorMessage(FieldError fieldError, String locale) {
		Locale currentLocale = LocaleContextHolder.getLocale();
		String localizedErrorMessage = messageSource.getMessage(fieldError,
				currentLocale);

		// If the message was not found, return the most accurate field error
		// code instead.
		// You can remove this check if you prefer to get the default error
		// message.
		if (localizedErrorMessage.equals(fieldError.getDefaultMessage())) {
			String[] fieldErrorCodes = fieldError.getCodes();
			localizedErrorMessage = fieldErrorCodes[0];
		}

		return localizedErrorMessage;
	}
	
	/*
	 * resolve error message with default locale
	 */
	private String resolveLocalizedErrorMessage(FieldError fieldError) {
		String localizedErrorMessage = messageSource.getMessage(fieldError,
				new Locale(Constants.Locale.INA));

		// If the message was not found, return the most accurate field error
		// code instead.
		// You can remove this check if you prefer to get the default error
		// message.
		if (localizedErrorMessage.equals(fieldError.getDefaultMessage())) {
			String[] fieldErrorCodes = fieldError.getCodes();
			localizedErrorMessage = fieldErrorCodes[0];
		}

		return localizedErrorMessage;
	}
}
