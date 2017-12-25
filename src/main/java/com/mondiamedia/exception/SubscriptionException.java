package com.mondiamedia.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionException extends RuntimeException{

	 
	   private static final long serialVersionUID = 1L;
	   private HttpStatus status;
	   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	   private LocalDateTime timestamp;
	   private String message;

	   private SubscriptionException() {
	       timestamp = LocalDateTime.now();
	   }

	   public SubscriptionException(HttpStatus status) {
	       this();
	       this.status = status;
	   }

	  public  SubscriptionException(HttpStatus status, Throwable ex) {
	       this();
	       this.status = status;
	       this.message = "Unexpected error";
	   }

	  public SubscriptionException(HttpStatus status, String message) {
	       this();
	       this.status = status;
	       this.message = message;
	   }
	  public SubscriptionException(HttpStatus status, String message,Throwable ex) {
	       this();
	       this.status = status;
	       this.message = message;
	   }
}
