package com.culnou.mumu.myway.domain.model;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class PersonFactoryTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	//id NULLチェックのテスト
	@Test(expected = IllegalArgumentException.class)
	public void createPersonByIdNullTest() {
		User user = new User(null, "user1","user1","user1","ss@ss.com");
        Person person = PersonFactory.creatPerson(user);
        assertNotNull(person);
        assertEquals(person.personId().id(), user.id());
        assertEquals(person.fullName().firstName(), user.frstName());
	}
	//name NULLチェックのテスト
	@Test(expected = IllegalArgumentException.class)
	public void createPersonByNameNullTest() {
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        User user = new User(str, null,"user1","user1","ss@ss.com");
        Person person = PersonFactory.creatPerson(user);
        assertNotNull(person);
        assertEquals(person.personId().id(), user.id());
        assertEquals(person.fullName().firstName(), user.frstName());
	}
	//NULLチェックのテスト
	@Test(expected = IllegalArgumentException.class)
	public void createPersonByNullTest() {
        User user = new User(null, null,"user1","user1","ss@ss.com");
        Person person = PersonFactory.creatPerson(user);
        assertNotNull(person);
        assertEquals(person.personId().id(), user.id());
        assertEquals(person.fullName().firstName(), user.frstName());
	}
		

	@Test
	public void createPersonTest() {
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        User user = new User(str, "user1","user1","user1","ss@ss.com");
        Person person = PersonFactory.creatPerson(user);
        assertNotNull(person);
        assertEquals(person.personId().id(), user.id());
        assertEquals(person.fullName().firstName(), user.frstName());
	}

}
