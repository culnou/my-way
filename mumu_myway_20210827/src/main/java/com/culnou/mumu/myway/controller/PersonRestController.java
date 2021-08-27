package com.culnou.mumu.myway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.culnou.mumu.myway.application.PersonService;

@RestController
public class PersonRestController {
	
	@Qualifier("personServiceImpl")
	@Autowired
	PersonService personService;
	
	@PostMapping("/persons")
	public void assignPerson(@RequestBody UserDto userDto) throws Exception{
		this.personService.assignPerson(userDto);
	}
	
	@GetMapping("/persons/{id}")
	public ResponseEntity<PersonDto> findPersonById(@PathVariable String id) throws Exception{
		return ResponseEntity.ok().body((PersonDto)this.personService.findPersonById(id));
		
	}
	@DeleteMapping("/persons")
	public void removePerson() throws Exception{
		this.personService.removeAllPerson();
	}
	

}
