package com.culnou.mumu.myway.domain.model;

public class User {
	
	private String id;
	private String name;
	
	public User(String id, String name) {
		this.setId(id);
		this.setName(name);
	}
	
	protected void setId(String id) {
		if(id == null) {
			throw new IllegalArgumentException("The id may not be set to null.");
		}
		this.id = id;
	}
	
	public String id() {
		return this.id;
	}
	
	protected void setName(String name) {
		if(name == null) {
			throw new IllegalArgumentException("The name may not be set to null.");
		}
		this.name = name;
	}
	
	public String name() {
		return this.name;
	}

}
