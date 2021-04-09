package com.edwin.springapi.excepcions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NO_CONTENT)
public class NoDataFoudException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoDataFoudException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NoDataFoudException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public NoDataFoudException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NoDataFoudException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NoDataFoudException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
