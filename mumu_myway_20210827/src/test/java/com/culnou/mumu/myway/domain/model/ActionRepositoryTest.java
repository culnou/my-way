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
public class ActionRepositoryTest {
	
	@Qualifier("actionMongoRepository")
	@Autowired
	private ActionRepository actionRepository;
	
	
	@Qualifier("projectMongoRepository")
	@Autowired
	private ProjectRepository projectRepository;
	
	//テスト用エンティティ
	private List<Action> testActions = new ArrayList<>();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		actionRepository.removeAll(this.testActions);
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
		this.testActions.add(action);
		ActionId actionId = new ActionId("action111");
		Action readAction = actionRepository.actionOfId(actionId);
		assertEquals(readAction.personId(), personId);
		assertEquals(readAction.projectId(), projectId);
		assertEquals(readAction.actionId(), actionId);
		assertEquals(readAction.name(), "111");
		assertEquals(readAction.description(), "111");
		
	}
	
	@Test
	public void testActionsOfProject() throws Exception{
		List<Action> saveActions = new ArrayList<>();
		PersonId personId = new PersonId("111");
		VisionId visionId = new VisionId("111");
		ProjectId projectId = new ProjectId("Project001");
		String name = "111";
		String description = "111";
		Project project = new Project(personId, visionId, projectId, name, description, ProjectType.EXPERIMENT);
		ActionId actionId = new ActionId("action001");
		Action action = project.defineAction(actionId, "111", "111");
		saveActions.add(action);
		this.testActions.add(action);
		ActionId actionId2 = new ActionId("action002");
		Action action2 = project.defineAction(actionId2, "111", "111");
		saveActions.add(action2);
		this.testActions.add(action2);
		actionRepository.saveAll(saveActions);
		List<Action> actions = actionRepository.actionsOfProject(projectId);
		assertEquals(actions.size(), 2);
		assertEquals(actions.get(0).actionId(), actionId);
	}

}
