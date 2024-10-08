package com.sizzle.server.exceptions;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.PropertyValueException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({ BadRequestException.class, HttpMessageNotReadableException.class,
			DataIntegrityViolationException.class, PropertyValueException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorResponse badRequestExceptionHandler(Exception ex, WebRequest request) {
		return handleException(ex, request);
	}

	private ErrorResponse handleException(Exception ex, WebRequest request) {
		HttpServletRequest servletRequest = ((ServletWebRequest) request).getRequest();
		String reqMethod = servletRequest.getMethod();
		String requestParams = "";

		if ("GET".equalsIgnoreCase(reqMethod)) {
			requestParams = Collections.list(servletRequest.getParameterNames()).stream()
					.map(pn -> pn + " : "
							+ Arrays.toString(
									Optional.ofNullable(servletRequest.getParameterValues(pn)).orElse(new String[0])))
					.collect(Collectors.joining(", "));
		} else if ("POST".equalsIgnoreCase(reqMethod)) {
			try {
				requestParams = servletRequest.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
			} catch (Exception e) {
				log.error("Error reading request body: {}", e.getMessage());
			}
		}

		log.warn("Error: [{}], Url: [{}], Parameters: [{}]", ex.getMessage(), servletRequest.getRequestURI(),
				requestParams);
		return new ErrorResponse(new Date(), ex.getMessage(), request.getDescription(false));
	}
}
