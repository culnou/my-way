package com.culnou.mumu.myway.domain.model;

import static org.junit.Assert.*;

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
public class ProjectQueryTest {
	
	@Qualifier("projectMongoRepository")
	@Autowired
	private ProjectRepository projectRepository;
	
	@Qualifier("projectMongoQuery")
	@Autowired
	private ProjectQuery projectQuery;
	
	@Qualifier("visionMongoRepository")
	@Autowired
	private VisionRepository visionRepository;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		projectRepository.removeAll();
	}
	
	@Test
	public void testFindprojectsOfProjectType() throws Exception{
		
		String name = "111";
		PersonId personId = new PersonId("111");
		Person person = new Person(personId, name);
		
		VisionId visionId = visionRepository.nextIdentity();
		VisionType visionType = VisionType.BUSINESS;
		String content = "111";
		
		Vision vision = person.createVision(visionId, visionType, content);
		
		ProjectId projectId = new ProjectId("111");
		String projectName = "111";
		String description = "111";
		Project project = vision.launchProject(projectId, projectName, description, ProjectType.EXPERIMENT);
		Goal measurement = new Goal("111", "111");
		project.defineGoal(measurement);
		projectRepository.save(project);
		List<Project> projects = projectQuery.findProjectsOfProjectType(ProjectType.EXPERIMENT);
		assertEquals(projects.get(0).goal(), measurement);
		assertEquals(projects.get(0).projectType(), ProjectType.EXPERIMENT);
	}

	@Test
	public void testFindprojectsOfVision() throws Exception{
		
		String name = "111";
		PersonId personId = new PersonId("111");
		Person person = new Person(personId, name);
		
		VisionId visionId = visionRepository.nextIdentity();
		VisionType visionType = VisionType.BUSINESS;
		String content = "111";
		
		Vision vision = person.createVision(visionId, visionType, content);
		
		ProjectId projectId = new ProjectId("111");
		String projectName = "111";
		String description = "111";
		Project project = vision.launchProject(projectId, projectName, description, ProjectType.EXPERIMENT);
		Goal measurement = new Goal("111", "111");
		project.defineGoal(measurement);
		projectRepository.save(project);
		List<Project> projects = projectQuery.findProjectsOfVision(visionId);
		assertEquals(projects.get(0).goal(), measurement);
		assertEquals(projects.get(0).visionId(), visionId);
	}

}
