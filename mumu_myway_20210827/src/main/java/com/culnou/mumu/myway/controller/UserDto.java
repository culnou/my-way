package com.culnou.mumu.myway.controller;



import lombok.Data;
@Data
public class UserDto{
	
	private String id;
	private String firstName;
	private String lastName;
	private String fullName;
	private String email;
	
	
	public UserDto(String id, String firstName, String lastName, String name, String email) {
		this.setId(id);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setFullName(name);
		this.setEmail(email);
	}
	
	protected void setId(String id) {
		if(id == null) {
			throw new IllegalArgumentException("The id may not be set to null.");
		}
		this.id = id;
	}
	
	protected void setFirstName(String firstName) {
		if(firstName == null) {
			throw new IllegalArgumentException("The firstName may not be set to null.");
		}
		this.firstName = firstName;
	}
	
	protected void setLastName(String lastName) {
		if(lastName == null) {
			throw new IllegalArgumentException("The lastName may not be set to null.");
		}
		this.lastName = lastName;
	}
	
	protected void setFullName(String name) {
		if(name == null) {
			throw new IllegalArgumentException("The name may not be set to null.");
		}
		this.fullName = name;
	}
	
	protected void setEmail(String email) {
		if(email == null) {
			throw new IllegalArgumentException("The email may not be set to null.");
		}
		this.email = email;
	}

}
