package com.culnou.mumu.myway.domain.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonRepositoryTest {
	
	//具象クラスをサービス名で切り替えられるようにする。
	@Qualifier("personMongoRepository")
	@Autowired
	private PersonRepository personRepository;
	
	private List<Person> testPersons = new ArrayList<>();
		

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		//personRepository.removeAll(this.testPersons);
	}

	@Test
	public void testSaveAndFindOnePerson() throws Exception{
	
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        User user = new User(str, "user1","user1","user1","ss@ss.com");
        Person person = PersonFactory.creatPerson(user);
		personRepository.save(person);
		this.testPersons.add(person);
		Person readPerson = personRepository.personOfId(person.personId());
		assertNotNull(readPerson);
		assertEquals(readPerson.personId(), person.personId());
		assertEquals(readPerson.fullName(), person.fullName());
	}

}
