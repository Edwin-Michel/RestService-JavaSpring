package com.edwin.springapi.config;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.edwin.springapi.excepcions.GeneralException;
import com.edwin.springapi.excepcions.NoDataFoudException;
import com.edwin.springapi.excepcions.ValidateServiceException;
import com.edwin.springapi.utils.StandardResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ErrorHandlerConfig extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> error (Exception e, WebRequest request){
		log.error(e.getMessage(), e);
		StandardResponse<?> response = new StandardResponse<>(false, "Internal Server Error", null);
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ValidateServiceException.class)
	public ResponseEntity<?> validateService (ValidateServiceException e, WebRequest request){
		log.info(e.getMessage(), e);
		StandardResponse<?> response = new StandardResponse<>(false, e.getMessage(), null);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoDataFoudException.class)
	public ResponseEntity<?> noData (NoDataFoudException e, WebRequest request){
		log.info(e.getMessage(), e);
		StandardResponse<?> response = new StandardResponse<>(false, e.getMessage(), null);
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(GeneralException.class)
	public ResponseEntity<?> generalException (GeneralException e, WebRequest request){
		log.error(e.getMessage(), e);
		StandardResponse<?> response = new StandardResponse<>(false, "Internal Server Error", null);
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
