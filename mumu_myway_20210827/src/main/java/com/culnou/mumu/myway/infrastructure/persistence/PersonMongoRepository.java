package com.culnou.mumu.myway.infrastructure.persistence;




import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.myway.domain.model.AbstractPersonRepository;
import com.culnou.mumu.myway.domain.model.Person;
import com.culnou.mumu.myway.domain.model.PersonId;


@Service("personMongoRepository")
@Transactional
public class PersonMongoRepository extends AbstractPersonRepository {

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
	public void saveAll(List<Person> persons) throws Exception {
		// TODO Auto-generated method stub
		for(Person person : persons) {
			this.save(person);
		}
		
	}

	@Override
	public void removeAll(List<Person> persons) throws Exception {
		// TODO Auto-generated method stub
		for(Person person : persons) {
			this.remove(person);
		}
		
	}

	@Override
	public Person personOfId(PersonId personId) throws Exception {
		// TODO Auto-generated method stub
		Optional<PersonDocument> readDoc = personRepository.findById(personId.id());
		if (readDoc.isPresent()){
			PersonDocument doc = readDoc.get();
			return this.convertPersonDocumentToPerson(doc);
		}else {
			return null;
		}
	}
	
	private PersonDocument convertPersonToPersonDocument(Person person) {
		PersonDocument doc = new PersonDocument();
		doc.setId(person.personId().id());
		doc.setPersonId(person.personId());
		doc.setFullName(person.fullName());
		doc.setEmail(person.email());
		return doc;
	}
	
	private Person convertPersonDocumentToPerson(PersonDocument doc) {
		return this.convertFrom(doc);
			
	}

	
	
	

}
