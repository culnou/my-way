package com.culnou.mumu.myway.domain.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class VisionTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	//バリデーションテスト
	//NULLを防いで正しく初期化できる（セッターのテストも兼ねる）
	@Test(expected = IllegalArgumentException.class)
	public void testInitializeByPersonId() {
		VisionId visionId = new VisionId("111");
		VisionType visionType = VisionType.BUSINESS;
		String content = "111";
		Vision vision = new Vision(null, visionId, visionType, content);
		//実行されない。
		vision.content();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testInitializeByVisionId() {
		PersonId personId = new PersonId("111");
		VisionType visionType = VisionType.BUSINESS;
		String content = "111";
		Vision vision = new Vision(personId, null, visionType, content);
		//実行されない。
		vision.content();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testInitializeByVisionType() {
		PersonId personId = new PersonId("111");
		VisionId visionId = new VisionId("111");
		String content = "111";
		Vision vision = new Vision(personId, visionId, null, content);
		//実行されない。
		vision.content();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testInitializeByContent() {
		PersonId personId = new PersonId("111");
		VisionId visionId = new VisionId("111");
		VisionType visionType = VisionType.BUSINESS;
		Vision vision = new Vision(personId, visionId, visionType, null);
		//実行されない。
		vision.content();
	}
	
	//識別子の不変性が保持されるか検証する
	@Test(expected = IllegalStateException.class)
	public void testInvariantOfIdentifier() {
		PersonId personId = new PersonId("111");
		VisionId visionId = new VisionId("111");
		VisionType visionType = VisionType.BUSINESS;
		String content = "111";
		Vision vision = new Vision(personId, visionId, visionType, content);
		//識別子は再度設定できない。
		VisionId visionId2 = new VisionId("222");
		vision.setVisionId(visionId2);
		//実行されない。
		vision.content();
	}
	//識別子オブジェクトが正しく設定される
	@Test
	public void testPersonId() {
		PersonId personId = new PersonId("111");
		VisionId visionId = new VisionId("111");
		VisionType visionType = VisionType.BUSINESS;
		String content = "111";
		Vision vision = new Vision(personId, visionId, visionType, content);
		assertEquals(vision.personId(), personId);
	}
	
	@Test
	public void testVisionId() {
		PersonId personId = new PersonId("111");
		VisionId visionId = new VisionId("111");
		VisionType visionType = VisionType.BUSINESS;
		String content = "111";
		Vision vision = new Vision(personId, visionId, visionType, content);
		assertEquals(vision.visionId(), visionId);
	}
	
	
	//列挙型が正しく設定される
	@Test
	public void testVisionType() {
		PersonId personId = new PersonId("111");
		VisionId visionId = new VisionId("111");
		VisionType visionType = VisionType.BUSINESS;
		String content = "111";
		Vision vision = new Vision(personId, visionId, visionType, content);
		assertEquals(vision.visionType(), VisionType.BUSINESS);
	}
	
	//ファクトリーメソッドのテスト
	@Test
	public void testLaunchProject() {
		PersonId personId = new PersonId("111");
		VisionId visionId = new VisionId("111");
		VisionType visionType = VisionType.BUSINESS;
		String content = "111";
		Vision vision = new Vision(personId, visionId, visionType, content);
		ProjectId experimentId = new ProjectId("111");
		String name = "111";
		String description = "111";
		Project project = vision.launchProject(experimentId, name, description, ProjectType.EXPERIMENT);
		assertEquals(project.personId(), personId);
		assertEquals(project.visionId(), visionId);
		assertEquals(project.projectId(), experimentId);
		assertEquals(project.name(), name);
		assertEquals(project.projectType(), ProjectType.EXPERIMENT);
		assertEquals(project.description(), description);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testLaunchProjectByNoProjectId() {
		PersonId personId = new PersonId("111");
		VisionId visionId = new VisionId("111");
		VisionType visionType = VisionType.BUSINESS;
		String content = "111";
		Vision vision = new Vision(personId, visionId, visionType, content);
		ProjectId experimentId = null;
		String name = "111";
		String description = "111";
		Project experiment = vision.launchProject(experimentId, name, description, ProjectType.EXPERIMENT);
		//実行されない。
		experiment.name();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testLaunchProjectByName() {
		PersonId personId = new PersonId("111");
		VisionId visionId = new VisionId("111");
		VisionType visionType = VisionType.BUSINESS;
		String content = "111";
		Vision vision = new Vision(personId, visionId, visionType, content);
		ProjectId experimentId = new ProjectId("111");
		String name = null;
		String description = "111";
		Project experiment = vision.launchProject(experimentId, name, description, ProjectType.EXPERIMENT);
		//実行されない。
		experiment.name();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testLaunchProjectByDescription() {
		PersonId personId = new PersonId("111");
		VisionId visionId = new VisionId("111");
		VisionType visionType = VisionType.BUSINESS;
		String content = "111";
		Vision vision = new Vision(personId, visionId, visionType, content);
		ProjectId experimentId = new ProjectId("111");
		String name = "111";
		String description = null;
		Project experiment = vision.launchProject(experimentId, name, description, ProjectType.EXPERIMENT);
		//実行されない。
		experiment.name();
	}

}
