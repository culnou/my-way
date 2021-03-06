package com.culnou.mumu.myway.domain.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class ProjectTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	//NULLを防いで正しく初期化できる（セッターのテストも兼ねる）
	@Test(expected = IllegalArgumentException.class)
	public void testInitializeByNoPersonId() {
		PersonId personId = null;
		VisionId visionId = new VisionId("111");
		ProjectId projectId = new ProjectId("111");
		String name = "111";
		String description = "111";
		Project project = new Project(personId, visionId, projectId, name, description, ProjectType.EXPERIMENT);
		//実行されない。
		project.name();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInitializeByNoVisionId() {
		PersonId personId = new PersonId("111");
		VisionId visionId = null;
		ProjectId experimentId = new ProjectId("111");
		String name = "111";
		String description = "111";
		Project experiment = new Project(personId, visionId, experimentId, name, description, ProjectType.EXPERIMENT);
		//実行されない。
		experiment.name();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInitializeByNoExperimentId() {
		PersonId personId = new PersonId("111");
		VisionId visionId = new VisionId("111");
		ProjectId experimentId = null;
		String name = "111";
		String description = "111";
		Project experiment = new Project(personId, visionId, experimentId, name, description, ProjectType.EXPERIMENT);
		//実行されない。
		experiment.name();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInitializeByNoName() {
		PersonId personId = new PersonId("111");
		VisionId visionId = new VisionId("111");
		ProjectId experimentId = new ProjectId("111");
		String name = null;
		String description = "111";
		Project experiment = new Project(personId, visionId, experimentId, name, description, ProjectType.EXPERIMENT);
		//実行されない。
		experiment.name();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInitializeByNoDescription() {
		PersonId personId = new PersonId("111");
		VisionId visionId = new VisionId("111");
		ProjectId experimentId = new ProjectId("111");
		String name = "111";
		String description = null;
		Project experiment = new Project(personId, visionId, experimentId, name, description, ProjectType.EXPERIMENT);
		//実行されない。
		experiment.name();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInitializeByNegativeExpendedTime() {
		PersonId personId = new PersonId("111");
		VisionId visionId = new VisionId("111");
		ProjectId projectId = new ProjectId("111");
		String name = "111";
		String description = "111";
		Project project = new Project(personId, visionId, projectId, name, description, ProjectType.EXPERIMENT);
		project.addExpendedTime(-1);
		//実行されない。
	    project.name();
	}
	//識別子の不変性が保持されるか検証する
	@Test(expected = IllegalStateException.class)
	public void testInvariantOfIdentifier() {
		PersonId personId = new PersonId("111");
		VisionId visionId = new VisionId("111");
		ProjectId projectId = new ProjectId("111");
		String name = "111";
		String description = "111";
		Project project = new Project(personId, visionId, projectId, name, description, ProjectType.EXPERIMENT);
		//識別子は再度設定できない。
		ProjectId projectId2 = new ProjectId("222");
		project.setProjectId(projectId2);
		//実行されない。
		project.name();
	}
	
	//識別子オブジェクトが正しく設定される
	@Test
	public void testPersonId() {
		PersonId personId = new PersonId("111");
		VisionId visionId = new VisionId("111");
		ProjectId projectId = new ProjectId("111");
		String name = "111";
		String description = "111";
		Project project = new Project(personId, visionId, projectId, name, description, ProjectType.EXPERIMENT);
		assertEquals(project.personId(), personId);
	}
	
	@Test
	public void testVisionId() {
		PersonId personId = new PersonId("111");
		VisionId visionId = new VisionId("111");
		ProjectId experimentId = new ProjectId("111");
		String name = "111";
		String description = "111";
		Project experiment = new Project(personId, visionId, experimentId, name, description, ProjectType.EXPERIMENT);
		assertEquals(experiment.visionId(), visionId);
	}
	
	@Test
	public void testExperimentId() {
		PersonId personId = new PersonId("111");
		VisionId visionId = new VisionId("111");
		ProjectId experimentId = new ProjectId("111");
		String name = "111";
		String description = "111";
		Project experiment = new Project(personId, visionId, experimentId, name, description, ProjectType.EXPERIMENT);
		assertEquals(experiment.projectId(), experimentId);
	}
	
	@Test
	public void testName() {
		PersonId personId = new PersonId("111");
		VisionId visionId = new VisionId("111");
		ProjectId experimentId = new ProjectId("111");
		String name = "111";
		String description = "111";
		Project experiment = new Project(personId, visionId, experimentId, name, description, ProjectType.EXPERIMENT);
		assertEquals(experiment.name(), name);
	}
	
	@Test
	public void testDescription() {
		PersonId personId = new PersonId("111");
		VisionId visionId = new VisionId("111");
		ProjectId experimentId = new ProjectId("111");
		String name = "111";
		String description = "111";
		Project experiment = new Project(personId, visionId, experimentId, name, description, ProjectType.EXPERIMENT);
		assertEquals(experiment.description(), description);
	}
	
	
	
	
	//ファクトリーメソッドのテスト
	@Test
	public void testDefineAction() throws Exception{
		PersonId personId = new PersonId("111");
		VisionId visionId = new VisionId("111");
		ProjectId projectId = new ProjectId("111");
		String name = "111";
		String description = "111";
		Project project = new Project(personId, visionId, projectId, name, description, ProjectType.EXPERIMENT);
		ActionId actionId = new ActionId("111");
		Action action = project.defineAction(actionId, name, description);
		assertEquals(action.personId(), personId);
		assertEquals(action.projectId(), projectId);
		assertEquals(action.actionId(), actionId);
		assertEquals(action.name(), name);
		assertEquals(action.description(), description);
	}
	//識別子オブジェクトがNULLの場合のチェック
	@Test(expected = IllegalArgumentException.class)
	public void testDefineActionByNoActionId() throws Exception{
		PersonId personId = new PersonId("111");
		VisionId visionId = new VisionId("111");
		ProjectId projectId = new ProjectId("111");
		String name = "111";
		String description = "111";
		Project project = new Project(personId, visionId, projectId, name, description, ProjectType.EXPERIMENT);
		ActionId actionId = null;
		Action action = project.defineAction(actionId, name, description);
		//実行されない。
		action.actionId();
	}
	
	//消費時間の加算のテスト
	@Test
	public void testAddExpendedTime() {
		PersonId personId = new PersonId("111");
		VisionId visionId = new VisionId("111");
		ProjectId projectId = new ProjectId("111");
		String name = "111";
		String description = "111";
		Project project = new Project(personId, visionId, projectId, name, description, ProjectType.EXPERIMENT);
		project.addExpendedTime(1);
		project.addExpendedTime(2);
		assertEquals(project.expendedTime(), 3);
	}
	
	@Test(expected = Exception.class)
	public void testAddExpendedTimeByNegativeResult() {
		PersonId personId = new PersonId("111");
		VisionId visionId = new VisionId("111");
		ProjectId projectId = new ProjectId("111");
		String name = "111";
		String description = "111";
		Project project = new Project(personId, visionId, projectId, name, description, ProjectType.EXPERIMENT);
		project.addExpendedTime(1);
		System.out.println("*** action.expendedTime " + project.expendedTime());
		project.addExpendedTime(-2);
		//実行されない。
		System.out.println("*** action.expendedTime " + project.expendedTime());
	}
	@Test(expected = IllegalArgumentException.class)
	public void testSetExpendedTimeByNegative() {
		PersonId personId = new PersonId("111");
		VisionId visionId = new VisionId("111");
		ProjectId projectId = new ProjectId("111");
		String name = "111";
		String description = "111";
		Project project = new Project(personId, visionId, projectId, name, description, ProjectType.EXPERIMENT);
		project.setExpendedTime(-2);
		//実行されない。
		System.out.println("*** action.expendedTime " + project.expendedTime());
	}

}
