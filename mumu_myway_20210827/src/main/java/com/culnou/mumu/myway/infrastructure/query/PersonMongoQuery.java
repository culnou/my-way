package com.culnou.mumu.myway.infrastructure.query;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.myway.domain.model.AbstractPersonQuery;
import com.culnou.mumu.myway.domain.model.Person;
import com.culnou.mumu.myway.domain.model.PersonId;


@Service("personMongoQuery")
@Transactional
public class PersonMongoQuery extends AbstractPersonQuery{
	
	@Autowired
	private PersonMongoDataQuery personQuery;

	@Override
	public Person findById(PersonId personId) throws Exception {
		// TODO Auto-generated method stub
		Optional<PersonQueryDocument> readDoc = personQuery.findById(personId.id());
		if (readDoc.isPresent()){
			PersonQueryDocument doc = readDoc.get();
			return this.convertPersonQueryDocumentToPerson(doc);
		}else {
			return null;
		}
	}
		
	private Person convertPersonQueryDocumentToPerson(PersonQueryDocument doc) {
		return this.convertFrom(doc);
			
	}

}
