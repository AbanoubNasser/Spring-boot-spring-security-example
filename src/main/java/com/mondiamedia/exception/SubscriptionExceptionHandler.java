package com.mondiamedia.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class SubscriptionExceptionHandler extends ResponseEntityExceptionHandler {

	   @Override
	   protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	       String error = "Malformed JSON request";
	       return buildResponseEntity(new SubscriptionException(HttpStatus.BAD_REQUEST, error, ex));
	   }

	   private ResponseEntity<Object> buildResponseEntity(SubscriptionException error) {
	       return new ResponseEntity<>(error, error.getStatus());
	   }
	   
	   @ExceptionHandler(SubscriptionException.class)
	   protected ResponseEntity<Object> handleSubscriptionException(
			   SubscriptionException ex) {
		   SubscriptionException subException=new SubscriptionException(ex.getStatus(), ex.getMessage());
	       return buildResponseEntity(subException);
	   }
}
