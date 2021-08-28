package com.culnou.mumu.myway.domain.model;

import static org.junit.Assert.*;

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
public class ActionRepositoryTest {
	
	@Qualifier("actionMongoRepository")
	@Autowired
	private ActionRepository actionRepository;
	
	@Qualifier("actionMongoQuery")
	@Autowired
	private ActionQuery actionQuery;

	@Qualifier("projectMongoRepository")
	@Autowired
	private ProjectRepository projectRepository;
	

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		actionRepository.removeAll();
	}

	@Test
	public void testSaveAndFindOneproject() throws Exception{
		PersonId personId = new PersonId("111");
		VisionId visionId = new VisionId("111");
		ProjectId projectId = new ProjectId("111");
		String name = "111";
		String description = "111";
		Project project = new Project(personId, visionId, projectId, name, description, ProjectType.EXPERIMENT);
		Action action = project.defineAction(new ActionId("action111"), "111", "111");
		actionRepository.save(action);
		ActionId actionId = new ActionId("action111");
		Action readAction = actionQuery.findById(actionId);
		assertEquals(readAction.personId(), personId);
		assertEquals(readAction.projectId(), projectId);
		assertEquals(readAction.actionId(), actionId);
		assertEquals(readAction.name(), "111");
		assertEquals(readAction.description(), "111");
		
	}

}
