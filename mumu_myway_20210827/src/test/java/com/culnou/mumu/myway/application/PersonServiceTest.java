package com.culnou.mumu.myway.application;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.culnou.mumu.myway.controller.PersonDto;
import com.culnou.mumu.myway.controller.UserDto;
@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonServiceTest {
	
	@Qualifier("personServiceImpl")
	@Autowired
	PersonService personService;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAssignPerson() throws Exception{
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        UserDto userDto = new UserDto(str, "personTest001");
		personService.assignPerson(userDto);
		PersonDto personDto = (PersonDto)personService.findPersonById(str);
		assertNotNull(personDto);
		assertEquals(personDto.getId(), userDto.getId());
		assertEquals(personDto.getName(), userDto.getName());
		
	}

}
