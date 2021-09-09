package com.culnou.mumu.myway.domain.model;



import com.culnou.mumu.myway.infrastructure.persistence.PersonDocument;

public abstract class AbstractPersonRepository implements PersonRepository {

	
	protected Person convertFrom(PersonDocument doc){
		Person person = new Person(doc.getPersonId(), doc.getFullName(), doc.getEmail());
		if(doc.getPhilosophy() != null) {
			person.setPhilosophy(doc.getPhilosophy());
		}
		if(doc.getPurpose() != null) {
			person.setPurpose(doc.getPurpose());
		}
		if(doc.getActionGuideline() != null) {
			person.setActionGuideline(doc.getActionGuideline());
		}
		return person;
	}

}
