package com.culnou.mumu.myway.controller;

import com.culnou.mumu.myway.application.Dto;

import lombok.Data;
@Data
public class PersonDto implements Dto {
	
	String id;
	String name;
	
	public PersonDto(String id, String name) {
		this.setId(id);
		this.setName(name);
	}
	
	protected void setId(String id) {
		if(id == null) {
			throw new IllegalArgumentException("The id may not be set to null.");
		}
		this.id = id;
	}
	
	
	
	protected void setName(String name) {
		if(name == null) {
			throw new IllegalArgumentException("The name may not be set to null.");
		}
		this.name = name;
	}
	
	

}
