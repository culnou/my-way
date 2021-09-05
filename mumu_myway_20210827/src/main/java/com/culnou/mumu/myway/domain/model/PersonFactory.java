package com.culnou.mumu.myway.domain.model;

public class PersonFactory {
	
	public static Person creatPerson(User user) {
		if(user.id() == null) {
			throw new IllegalArgumentException("The id may not be set to null.");
		}
		if(user.frstName() == null) {
			throw new IllegalArgumentException("The frstName may not be set to null.");
		}
		if(user.lastName() == null) {
			throw new IllegalArgumentException("The lastName may not be set to null.");
		}
		if(user.email() == null) {
			throw new IllegalArgumentException("The email may not be set to null.");
		}
		
		return new Person(new PersonId(user.id()), new FullName(user.frstName(), user.lastName()), new Email(user.email()));
	}

}
