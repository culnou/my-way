package com.culnou.mumu.myway.infrastructure.persistence;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.myway.domain.model.Person;
import com.culnou.mumu.myway.domain.model.PersonRepository;
@Service("personMongoRepository")
@Transactional
public class PersonMongoRepository implements PersonRepository {

	@Autowired
	private PersonMongoDataRepository personRepository;
	

	

	@Override
	public void save(Person person) throws Exception {
		// TODO Auto-generated method stub
		PersonDocument doc = this.convertPersonToPersonDocument(person);
		personRepository.save(doc);
		

	}

	@Override
	public void remove(Person person) throws Exception {
		// TODO Auto-generated method stub
		PersonDocument doc = this.convertPersonToPersonDocument(person);
		personRepository.delete(doc);

	}
	
	@Override
	public void removeAll() throws Exception{
		personRepository.deleteAll();
	}
	
	private PersonDocument convertPersonToPersonDocument(Person person) {
		PersonDocument doc = new PersonDocument();
		doc.setId(person.personId().id());
		doc.setPersonId(person.personId());
		doc.setName(person.name());
		return doc;
	}
	
	

}
