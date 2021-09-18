package com.culnou.mumu.myway.application;

import static org.junit.Assert.*;

import java.util.Date;
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

import com.culnou.mumu.myway.controller.PersonDto;
import com.culnou.mumu.myway.controller.ProjectDto;
import com.culnou.mumu.myway.controller.UserDto;
import com.culnou.mumu.myway.controller.VisionDto;
import com.culnou.mumu.myway.domain.model.ProjectType;
import com.culnou.mumu.myway.domain.model.VisionType;
@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonServiceTest {
	
	@Qualifier("personServiceImpl")
	@Autowired
	PersonService personService;

	PersonDto testPerson;
	
	
	@Before
	public void setUp() throws Exception {
		UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        UserDto userDto = new UserDto(id, "personTest001","personTest001","personTest001","ss@ss.com");
		personService.assignPerson(userDto);
		testPerson = (PersonDto)personService.findPersonById(id);
	}

	@After
	public void tearDown() throws Exception {
		personService.deletePerson(testPerson.getId());
	}

	@Test
	public void testAssignPerson() throws Exception{
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        UserDto userDto = new UserDto(str, "personTest001","personTest001","personTest001","ss@ss.com");
		personService.assignPerson(userDto);
		PersonDto personDto = (PersonDto)personService.findPersonById(str);
		assertNotNull(personDto);
		assertEquals(personDto.getId(), userDto.getId());
		assertEquals(personDto.getFirstName(), userDto.getFirstName());
		personService.deletePerson(str);
	}
	@Test
	public void testCreatePerson() throws Exception{
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        UserDto userDto = new UserDto(str, "personTest001","personTest001","personTest001","ss@ss.com");
        PersonDto personDto = personService.createPerson(userDto);
		assertNotNull(personDto);
		assertEquals(personDto.getId(), userDto.getId());
		assertEquals(personDto.getFirstName(), userDto.getFirstName());
		personService.deletePerson(str);
	}
	
	@Test
	public void testUpdatePerson() throws Exception{
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        UserDto userDto = new UserDto(str, "personTest001","personTest001","personTest001","ss@ss.com");
        PersonDto personDto = personService.createPerson(userDto);
        personDto.setPhilosophy("Philosophy1");
        personDto.setPurpose("Purpose1");
        personDto.setActionGuideline("ActionGuideline1");
        personService.updatePerson(personDto);
        PersonDto readPerson = personService.findPersonById(personDto.getId());
        assertEquals(readPerson.getPhilosophy(), "Philosophy1");
        assertEquals(readPerson.getPurpose(), "Purpose1");
        assertEquals(readPerson.getActionGuideline(), "ActionGuideline1");
        personService.deletePerson(str);
	}
	//正常テスト
	@Test
	public void testAddVison() throws Exception{
		VisionDto visionDto = new VisionDto();
		visionDto.setContent("vision001");
		visionDto.setTitle("vision001");
		visionDto.setPersonId(testPerson.getId());
		visionDto.setVisionType(VisionType.BUSINESS);
		VisionDto resultDto = personService.addVision(visionDto);
		System.out.println("*** vision id " + resultDto.getVisionId());
		assertEquals(resultDto.getPersonId(), testPerson.getId());
		personService.deleteVision(resultDto.getVisionId());
	}
	//個人が存在しない場合のテスト
	@Test(expected = Exception.class)
	public void testAddVisonByNoPersonExist() throws Exception{
		VisionDto visionDto = new VisionDto();
		visionDto.setContent("vision001");
		visionDto.setTitle("vision001");
		visionDto.setPersonId("");
		visionDto.setVisionType(VisionType.BUSINESS);
		VisionDto resultDto = personService.addVision(visionDto);
		//実行されない。
		resultDto.getId();
	}
	//ビジョンタイプが存在しない場合のテスト
	@Test(expected = Exception.class)
	public void testAddVisionByNoVisionTypeExist() throws Exception{
		VisionDto visionDto = new VisionDto();
		visionDto.setContent("vision001");
		visionDto.setTitle("vision001");
		visionDto.setPersonId(testPerson.getId());
		VisionDto resultDto = personService.addVision(visionDto);
		//実行されない。
		resultDto.getId();
	}
	@Test
	public void testUpdateVision() throws Exception{
		VisionDto visionDto = new VisionDto();
		visionDto.setContent("vision001");
		visionDto.setTitle("vision001");
		visionDto.setPersonId(testPerson.getId());
		visionDto.setVisionType(VisionType.BUSINESS);
		VisionDto resultDto = personService.addVision(visionDto);
		visionDto.setTitle("vision002");
		resultDto.setContent("Vision002");
		personService.updateVision(resultDto);
		VisionDto readVision = personService.findVisionById(resultDto.getId());
		assertEquals(readVision.getContent(), resultDto.getContent());
		personService.deleteVision(resultDto.getVisionId());
	}
	@Test(expected = Exception.class)
	public void testUpdateVisionByNoVisionExist() throws Exception{
		VisionDto visionDto = new VisionDto();
		visionDto.setVisionId("");
		personService.updateVision(visionDto);
	}
	
	@Test
	public void testFindVisionsByPersonId() throws Exception{
		VisionDto visionDto = new VisionDto();
		visionDto.setContent("vision001");
		visionDto.setTitle("vision001");
		visionDto.setPersonId(testPerson.getId());
		visionDto.setVisionType(VisionType.BUSINESS);
		personService.addVision(visionDto);
		VisionDto visionDto2 = new VisionDto();
		visionDto2.setContent("vision001");
		visionDto2.setTitle("vision001");
		visionDto2.setPersonId(testPerson.getId());
		visionDto2.setVisionType(VisionType.BUSINESS);
		personService.addVision(visionDto2);
		List<VisionDto> visions = personService.findVisionsByPersonId(testPerson.getId());
		assertEquals(visions.size(), 2);
		personService.deleteVision(visionDto.getId());
		personService.deleteVision(visionDto2.getId());
	}
	
	@Test
	public void testProject() throws Exception{
		VisionDto visionDto = new VisionDto();
		visionDto.setContent("vision001");
		visionDto.setTitle("vision001");
		visionDto.setPersonId(testPerson.getId());
		visionDto.setVisionType(VisionType.BUSINESS);
		VisionDto vision = personService.addVision(visionDto);
		ProjectDto project = new ProjectDto();
		project.setPersonId(testPerson.getId());
		project.setVisionId(vision.getVisionId());
		project.setName("project01");
		project.setDescription("project01");
		project.setCriteria("project01");
		project.setIndicator("project01");
		project.setDeadline(new Date());
		project.setExpendedTime(10);
		project.setTerm(10);
		project.setProjectType(ProjectType.EXPERIMENT);
		ProjectDto readProject = personService.addProject(project);
		assertEquals(readProject.getPersonId(), testPerson.getId());
		assertEquals(readProject.getVisionId(), vision.getId());
		assertEquals(readProject.getProjectType(), ProjectType.EXPERIMENT);
		assertEquals(readProject.getName(), "project01");
		readProject.setName("project02");
		personService.updateProject(readProject);
		ProjectDto foudProject = personService.findProjectById(project.getId());
		assertEquals(foudProject.getName(), "project02");
		List<ProjectDto> projects = personService.findProjectsByVisionIdAndProjectType(readProject.getVisionId(), readProject.getProjectType());
		assertEquals(projects.size(), 1);
		//personService.deleteProject(foudProject.getId());
		personService.deleteVision(visionDto.getId());
	}

}
