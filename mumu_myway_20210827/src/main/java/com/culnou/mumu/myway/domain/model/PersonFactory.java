package com.culnou.mumu.myway.domain.model;

public class PersonFactory {
	
	public static Person creatPerson(User user) {
		if(user.id() == null) {
			throw new IllegalArgumentException("The id may not be set to null.");
		}
		if(user.name() == null) {
			throw new IllegalArgumentException("The name may not be set to null.");
		}
		return new Person(new PersonId(user.id()), user.name());
	}

}
