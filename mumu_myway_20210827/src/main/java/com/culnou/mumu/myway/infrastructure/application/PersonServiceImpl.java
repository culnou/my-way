package com.culnou.mumu.myway.infrastructure.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.myway.application.Dto;
import com.culnou.mumu.myway.application.PersonService;
import com.culnou.mumu.myway.controller.PersonDto;
import com.culnou.mumu.myway.controller.UserDto;
import com.culnou.mumu.myway.domain.model.Person;
import com.culnou.mumu.myway.domain.model.PersonFactory;
import com.culnou.mumu.myway.domain.model.PersonId;
import com.culnou.mumu.myway.domain.model.PersonQuery;
import com.culnou.mumu.myway.domain.model.PersonRepository;
import com.culnou.mumu.myway.domain.model.User;
@Service("personServiceImpl")
@Transactional
public class PersonServiceImpl implements PersonService {
	
	@Qualifier("personMongoRepository")
	@Autowired
	private PersonRepository personRepository;
	@Qualifier("personMongoQuery")
	@Autowired
	private PersonQuery personQuery;

	@Override
	public void assignPerson(Dto user) throws Exception {
		// TODO Auto-generated method stub
		UserDto userDto = (UserDto)user;
		User usr = new User(userDto.getId(), userDto.getName());
		Person person = PersonFactory.creatPerson(usr);
        personRepository.save(person);
	}

	@Override
	public Dto findPersonById(String id) throws Exception {
		// TODO Auto-generated method stub
		PersonId personId = new PersonId(id);
		Person person = personQuery.findById(personId);
		PersonDto personDto = new PersonDto(person.personId().id(), person.name());
		return personDto;
	}

	@Override
	public void removeAllPerson() throws Exception {
		// TODO Auto-generated method stub
		personRepository.removeAll();
		
	}

}
