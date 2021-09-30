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
import com.culnou.mumu.myway.domain.model.ProjectExistException;
import com.culnou.mumu.myway.domain.model.ProjectType;

@RestController
public class PersonRestController {
	
	@Qualifier("personServiceImpl")
	@Autowired
	PersonService personService;
	
	/*
	 * Person
	 */
	
	//PersonDtoを返すcreatePersonを作成したので不要になりました。2021/9/7
	/*
	@PostMapping("/persons")
	public void assignPerson(@RequestBody UserDto userDto) throws Exception{
		this.personService.assignPerson(userDto);
	}
	*/
	
	@PostMapping("/persons")
	public ResponseEntity<PersonDto> createPerson(@RequestBody UserDto userDto) throws Exception{
		return ResponseEntity.ok().body((PersonDto)this.personService.createPerson(userDto));
	}
	
	@PutMapping("/persons")
	public HttpStatus updatePerson(@RequestBody PersonDto personDto) throws Exception{
		this.personService.updatePerson(personDto);
		return HttpStatus.OK;
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
		try {
			System.out.println("@@@@@@@ controller vision " + id);
			this.personService.deleteVision(id);
			return HttpStatus.OK;
		//プロジェクトが存在した場合、406エラーを返す。
		}catch(ProjectExistException ex) {
			return HttpStatus.NOT_ACCEPTABLE;
		}
	}
	
	/*
	 * Project
	 */
	@GetMapping("/projects/vision/{visionId}/project_type/{projectType}")
	public ResponseEntity<List<ProjectDto>> findProjectsByVisionId(@PathVariable String visionId, @PathVariable ProjectType projectType) throws Exception{
		return ResponseEntity.ok().body((List<ProjectDto>)this.personService.findProjectsByVisionIdAndProjectType(visionId, projectType));
	}
	
	@GetMapping("/projects/{id}")
	public ResponseEntity<ProjectDto> findProjectById(@PathVariable String id) throws Exception{
		return ResponseEntity.ok().body((ProjectDto)this.personService.findProjectById(id));
	}
	
	@PostMapping("/projects")
	public ResponseEntity<ProjectDto> addProject(@RequestBody ProjectDto projectDto) throws Exception{
		return ResponseEntity.ok().body((ProjectDto)this.personService.addProject(projectDto));
	}
	
	@PutMapping("/projects")
	public HttpStatus updateProject(@RequestBody ProjectDto projectDto) throws Exception{
		this.personService.updateProject(projectDto);
		return HttpStatus.OK;
	}
	
	@DeleteMapping("/projects/{id}")
	public HttpStatus deleteProject(@PathVariable String id) throws Exception{
		this.personService.deleteProject(id);
		return HttpStatus.OK;
	}
	
	/*
	 * Action
	 */
	@GetMapping("/actions/project/{projectId}")
	public ResponseEntity<List<ActionDto>> findActionsByProjectId(@PathVariable String projectId) throws Exception{
		return ResponseEntity.ok().body((List<ActionDto>)this.personService.findActionsByProjectId(projectId));
	}
	
	@GetMapping("/actions/{id}")
	public ResponseEntity<ActionDto> findActionById(@PathVariable String id) throws Exception{
		return ResponseEntity.ok().body((ActionDto)this.personService.findActionById(id));
	}
	
	@PostMapping("/actions")
	public ResponseEntity<ActionDto> addAction(@RequestBody ActionDto actionDto) throws Exception{
		return ResponseEntity.ok().body((ActionDto)this.personService.addAction(actionDto));
	}
	
	@PutMapping("/actions")
	public HttpStatus updateAction(@RequestBody ActionDto actionDto) throws Exception{
		this.personService.updateAction(actionDto);
		return HttpStatus.OK;
	}
	
	@DeleteMapping("/actions/{id}")
	public HttpStatus deleteAction(@PathVariable String id) throws Exception{
		this.personService.deleteAction(id);
		return HttpStatus.OK;
	}
	
	/*
	 * Work
	 */
	@GetMapping("/works/action/{actionId}")
	public ResponseEntity<List<WorkDto>> findWorksByActionId(@PathVariable String actionId) throws Exception{
		return ResponseEntity.ok().body((List<WorkDto>)this.personService.findWorksByActionId(actionId));
	}
	
	@GetMapping("/works/{id}")
	public ResponseEntity<WorkDto> findWorkById(@PathVariable String id) throws Exception{
		return ResponseEntity.ok().body((WorkDto)this.personService.findWorkById(id));
	}
	
	@PostMapping("/works")
	public ResponseEntity<WorkDto> addWork(@RequestBody WorkDto workDto) throws Exception{
		return ResponseEntity.ok().body((WorkDto)this.personService.addWork(workDto));
	}
	
	@PutMapping("/works")
	public HttpStatus updateWork(@RequestBody WorkDto workDto) throws Exception{
		this.personService.updateWork(workDto);
		return HttpStatus.OK;
	}
	
	@DeleteMapping("/works/{id}")
	public HttpStatus deleteWork(@PathVariable String id) throws Exception{
		this.personService.deleteWork(id);
		return HttpStatus.OK;
	}

}
