package com.culnou.mumu.myway.domain.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

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
public class ProjectRepositoryTest {
	
	@Qualifier("projectMongoRepository")
	@Autowired
	private ProjectRepository projectRepository;
	

	@Qualifier("visionMongoRepository")
	@Autowired
	private VisionRepository visionRepository;
	
	//テスト用エンティティ
	private List<Project> testProjects = new ArrayList<>();
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		projectRepository.removeAll(testProjects);
	}

	@Test
	public void testSaveAndFindOneproject() throws Exception{
		PersonId personId = new PersonId("111");
		FullName fullName = new FullName("taro", "yamada");
		Email email = new Email("ss@ss.com");
		Person person = new Person(personId, fullName, email);
		
		VisionId visionId = visionRepository.nextIdentity();
		VisionType visionType = VisionType.BUSINESS;
		String content = "111";
		String title = "111";
		Vision vision = person.createVision(visionId, visionType, title, content);
		
		ProjectId projectId = new ProjectId("111");
		String projectName = "111";
		String description = "111";
		Project project = vision.launchProject(projectId, projectName, description, ProjectType.EXPERIMENT);
		
		projectRepository.save(project);
		testProjects.add(project);
		
		Project readproject = projectRepository.projectOfId(projectId);
		assertEquals(readproject.personId(), personId);
		assertEquals(readproject.visionId(), visionId);
		assertEquals(readproject.projectId(), projectId);
		assertEquals(readproject.name(), projectName);
		assertEquals(readproject.description(), description);
		assertEquals(readproject.projectType(), ProjectType.EXPERIMENT);
	}
	
	@Test
	public void testProjectsOfVision() throws Exception{
		List<Project> saveProjects = new ArrayList<>();
		
		PersonId personId = new PersonId("111");
		FullName fullName = new FullName("taro", "yamada");
		Email email = new Email("ss@ss.com");
		Person person = new Person(personId, fullName, email);
		
		VisionId visionId = new VisionId("Vision001");
		VisionType visionType = VisionType.BUSINESS;
		String content = "111";
		String title = "111";
		Vision vision = person.createVision(visionId, visionType, title, content);
		
		ProjectId projectId = new ProjectId("Project001");
		String projectName = "111";
		String description = "111";
		Project project = vision.launchProject(projectId, projectName, description, ProjectType.EXPERIMENT);
		saveProjects.add(project);
		testProjects.add(project);
		ProjectId projectId2 = new ProjectId("Project002");
		Project project2 = vision.launchProject(projectId2, projectName, description, ProjectType.EXPERIMENT);
		saveProjects.add(project2);
		testProjects.add(project2);
		projectRepository.saveAll(saveProjects);
		List<Project> projects = projectRepository.projectsOfVision(visionId);
		assertEquals(projects.size(), 2);
		assertEquals(projects.get(0).visionId(), visionId);
	}
	
	@Test
	public void testProjectsOfProjectType() throws Exception{
		
		PersonId personId = new PersonId("111");
		FullName fullName = new FullName("taro", "yamada");
		Email email = new Email("ss@ss.com");
		Person person = new Person(personId, fullName, email);
		
		VisionId visionId = new VisionId("Vision002");
		VisionType visionType = VisionType.BUSINESS;
		String content = "111";
		String title = "111";
		Vision vision = person.createVision(visionId, visionType, title, content);
		
		ProjectId projectId = new ProjectId("Project003");
		String projectName = "111";
		String description = "111";
		Project project = vision.launchProject(projectId, projectName, description, ProjectType.PRACTICE);
		projectRepository.save(project);
		testProjects.add(project);
		ProjectId projectId2 = new ProjectId("Project004");
		Project project2 = vision.launchProject(projectId2, projectName, description, ProjectType.PRACTICE);
		projectRepository.save(project2);
		testProjects.add(project2);
		List<Project> projects = projectRepository.projectsOfProjectType(ProjectType.PRACTICE);
		assertEquals(projects.size(), 2);
		assertEquals(projects.get(0).projectType(), ProjectType.PRACTICE);
		assertEquals(projects.get(0).visionId(), visionId);
	}
	
	@Test
	public void testProjectsOfVisionAndProjectType() throws Exception{
		PersonId personId = new PersonId("111");
		FullName fullName = new FullName("taro", "yamada");
		Email email = new Email("ss@ss.com");
		Person person = new Person(personId, fullName, email);
		
		VisionId visionId = new VisionId("Vision002");
		VisionType visionType = VisionType.BUSINESS;
		String content = "111";
		String title = "111";
		Vision vision = person.createVision(visionId, visionType, title, content);
		
		ProjectId projectId = new ProjectId("Project003");
		String projectName1 = "Experiment Project";
		String description = "111";
		Project project = vision.launchProject(projectId, projectName1, description, ProjectType.EXPERIMENT);
		projectRepository.save(project);
		testProjects.add(project);
		ProjectId projectId2 = new ProjectId("Project004");
		String projectName2 = "Practice Project";
		Project project2 = vision.launchProject(projectId2, projectName2, description, ProjectType.PRACTICE);
		projectRepository.save(project2);
		testProjects.add(project2);
		List<Project> projects1 = projectRepository.projectsOfVisionAndProjectType(visionId, ProjectType.EXPERIMENT);
		assertEquals(projects1.size(), 1);
		assertEquals(projects1.get(0).name(), projectName1);
		List<Project> projects2 = projectRepository.projectsOfVisionAndProjectType(visionId, ProjectType.PRACTICE);
		assertEquals(projects2.size(), 1);
		assertEquals(projects2.get(0).name(), projectName2);
	}

}
