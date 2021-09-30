package com.culnou.mumu.myway.domain.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
public class ActionExistException extends Exception {

	private static final long serialVersionUID = 1L;
	
	ActionExistException(String msg){
		super(msg);
	}

}
