package com.culnou.mumu.myway.domain.model;



import com.culnou.mumu.myway.infrastructure.persistence.PersonDocument;

public abstract class AbstractPersonRepository implements PersonRepository {

	
	protected Person convertFrom(PersonDocument doc){
		return new Person(doc.getPersonId(), doc.getName());
	}

}
