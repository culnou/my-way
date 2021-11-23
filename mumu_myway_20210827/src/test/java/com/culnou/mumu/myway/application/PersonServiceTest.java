package com.culnou.mumu.myway.application;

import static org.junit.Assert.*;

import java.util.ArrayList;
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

import com.culnou.mumu.myway.controller.AchievementDto;
import com.culnou.mumu.myway.controller.ActionDto;
import com.culnou.mumu.myway.controller.PersonDto;
import com.culnou.mumu.myway.controller.ProjectDto;
import com.culnou.mumu.myway.controller.UserDto;
import com.culnou.mumu.myway.controller.VisionDto;
import com.culnou.mumu.myway.controller.WorkDto;

import com.culnou.mumu.myway.domain.model.ProjectType;
import com.culnou.mumu.myway.domain.model.VisionType;

import com.culnou.mumu.myway.domain.model.WorkStatus;
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
		//更新のタイミングで消費時間が変更されるこはないので、不測の更新を避けるために以下を削除する。
		//10が100000に更新されないか確認する。
		readProject.setExpendedTime(10000000);
		AchievementDto a3 = new AchievementDto();
		//自己生成したAchievementのIDが設定されるか確認する。
		a3.setId("111");
		a3.setResult("Result1");
		a3.setExecTime(new Date());
		List<AchievementDto> as = new ArrayList<>();
		as.add(a3);
		readProject.setAchievements(as);
		
		personService.updateProject(readProject);
		ProjectDto foudProject = personService.findProjectById(project.getId());
		assertEquals(foudProject.getName(), "project02");
		//addExpendedTimeだと、更新する都度、消費時間が倍に増えるのでsetExpendedTimeに変更する。テスト。2021/10/24
		//更新のタイミングで消費時間が変更されるこはないので、不測の更新を避けるために以下を削除する。→テストも削除。2021/10/28
		//assertEquals(foudProject.getExpendedTime(), readProject.getExpendedTime());
		List<ProjectDto> projects = personService.findProjectsByVisionIdAndProjectType(readProject.getVisionId(), readProject.getProjectType());
		assertEquals(projects.size(), 1);
		//personService.deleteProject(foudProject.getId());
		//personService.deleteVision(visionDto.getId());
	}
	
	@Test
	public void testWork() throws Exception{
		UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        UserDto userDto = new UserDto(id, "personTest001","personTest001","personTest001","ss@ss.com");
		personService.assignPerson(userDto);
		PersonDto testPerson1 = (PersonDto)personService.findPersonById(id);
		VisionDto visionDto = new VisionDto();
		visionDto.setContent("vision001");
		visionDto.setTitle("vision001");
		visionDto.setPersonId(testPerson1.getId());
		visionDto.setVisionType(VisionType.BUSINESS);
		VisionDto vision = personService.addVision(visionDto);
		ProjectDto project = new ProjectDto();
		project.setPersonId(testPerson1.getId());
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
		ActionDto action = new ActionDto();
		action.setPersonId(testPerson1.getId());
		action.setProjectId(readProject.getProjectId());
		action.setName("action01");
		action.setDescription("action01");
		action.setExpendedTime(10);
		ActionDto readAction = personService.addAction(action);
		WorkDto work = new WorkDto();
		work.setPersonId(testPerson1.getId());
		work.setActionId(readAction.getActionId());
		work.setName("work01");
		work.setDescription("work01");
		work.setStartTime(new Date());
		work.setEndTime(new Date());
		work.setWorkStatus(WorkStatus.DOING);
		work.setExpendedTime(20);
		personService.addWork(work);
		
		
	}
	
	@Test
	public void testDeleteAction() throws Exception{
		UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        UserDto userDto = new UserDto(id, "personTest001","personTest001","personTest001","ss@ss.com");
		personService.assignPerson(userDto);
		PersonDto testPerson1 = (PersonDto)personService.findPersonById(id);
		VisionDto visionDto = new VisionDto();
		visionDto.setContent("vision001");
		visionDto.setTitle("vision001");
		visionDto.setPersonId(testPerson1.getId());
		visionDto.setVisionType(VisionType.BUSINESS);
		VisionDto vision = personService.addVision(visionDto);
		ProjectDto project = new ProjectDto();
		project.setPersonId(testPerson1.getId());
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
		ActionDto action = new ActionDto();
		action.setPersonId(testPerson1.getId());
		action.setProjectId(readProject.getProjectId());
		action.setName("action01");
		action.setDescription("action01");
		action.setExpendedTime(10);
		ActionDto readAction = personService.addAction(action);
		WorkDto work = new WorkDto();
		work.setPersonId(testPerson1.getId());
		work.setActionId(readAction.getActionId());
		work.setName("work01");
		work.setDescription("work01");
		work.setStartTime(new Date());
		work.setEndTime(new Date());
		work.setWorkStatus(WorkStatus.DOING);
		work.setExpendedTime(20);
		personService.addWork(work);
		//addExpendedTimeだと、更新する都度、消費時間が倍に増えるのでsetExpendedTimeに変更する。テスト。2021/10/24
		ActionDto foundAction = personService.findActionById(readAction.getId());
		assertEquals(readAction.getExpendedTime(), foundAction.getExpendedTime());
		personService.deleteAction(readAction.getActionId());
		
	}

}
