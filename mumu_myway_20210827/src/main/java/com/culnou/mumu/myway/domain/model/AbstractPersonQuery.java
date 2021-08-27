package com.culnou.mumu.myway.domain.model;

import com.culnou.mumu.myway.infrastructure.query.PersonQueryDocument;

public abstract class AbstractPersonQuery implements PersonQuery {

	@Override
	public Person findById(PersonId personId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected Person convertFrom(PersonQueryDocument doc){
		return new Person(doc.getPersonId(), doc.getName());
	}

}
