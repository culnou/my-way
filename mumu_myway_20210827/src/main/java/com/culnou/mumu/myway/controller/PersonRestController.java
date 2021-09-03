package com.culnou.mumu.myway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.culnou.mumu.myway.application.PersonService;

@RestController
public class PersonRestController {
	
	@Qualifier("personServiceImpl")
	@Autowired
	PersonService personService;
	
	/*
	 * Person
	 */
	
	@PostMapping("/persons")
	public void assignPerson(@RequestBody UserDto userDto) throws Exception{
		this.personService.assignPerson(userDto);
	}
	
	@GetMapping("/persons/{id}")
	public ResponseEntity<PersonDto> findPersonById(@PathVariable String id) throws Exception{
		return ResponseEntity.ok().body((PersonDto)this.personService.findPersonById(id));
		
	}
	@DeleteMapping("/persons/{id}")
	public HttpStatus deletePerson(@PathVariable String id) throws Exception{
		this.personService.deletePerson(id);
		return HttpStatus.OK;
	}
	
	/*
	 * Vision
	 */
	@GetMapping("/visions/person/{personId}")
	public ResponseEntity<List<VisionDto>> findVisionsByPersonId(@PathVariable String personId) throws Exception{
		return ResponseEntity.ok().body((List<VisionDto>)this.personService.findVisionsByPersonId(personId));
	}
	
	@GetMapping("/visions/{id}")
	public ResponseEntity<VisionDto> findVisionById(@PathVariable String id) throws Exception{
		return ResponseEntity.ok().body((VisionDto)this.personService.findVisionById(id));
	}
	
	@PostMapping("/visions")
	public ResponseEntity<VisionDto> addVision(@RequestBody VisionDto visionDto) throws Exception{
		return ResponseEntity.ok().body((VisionDto)this.personService.addVision(visionDto));
	}
	
	@PutMapping("/visions")
	public HttpStatus updateVision(@RequestBody VisionDto visionDto) throws Exception{
		this.personService.updateVision(visionDto);
		return HttpStatus.OK;
	}
	
	@DeleteMapping("/visions/{id}")
	public HttpStatus deleteVision(@PathVariable String id) throws Exception{
		this.personService.deleteVision(id);
		return HttpStatus.OK;
	}
	
	
	

}
