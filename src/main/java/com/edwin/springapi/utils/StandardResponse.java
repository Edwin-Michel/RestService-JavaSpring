package com.edwin.springapi.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StandardResponse<T> {
	
	private Boolean ok;
	
	private String message;
	
	private T data;
	
}
