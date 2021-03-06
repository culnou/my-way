package com.culnou.mumu.myway.domain.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
public class ProjectExistException extends Exception {

	private static final long serialVersionUID = 1L;
	
	ProjectExistException(String msg){
		super(msg);
	}

}
