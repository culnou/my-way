package com.culnou.mumu.myway.domain.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.culnou.mumu.myway.domain.model.Goal;
import com.culnou.mumu.myway.domain.model.PersonId;
import com.culnou.mumu.myway.domain.model.Project;
import com.culnou.mumu.myway.domain.model.ProjectId;
import com.culnou.mumu.myway.domain.model.ProjectType;
import com.culnou.mumu.myway.domain.model.VisionId;

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
		project.setExpendedTime(-1);
		//実行されない。
	    project.name();
	}
	//識別子オブジェクトが正しく設定される
	@Test
	public void testPersonId() {
		PersonId personId = new PersonId("111");
		VisionId visionId = new VisionId("111");
		ProjectId experimentId = new ProjectId("111");
		String name = "111";
		String description = "111";
		Project experiment = new Project(personId, visionId, experimentId, name, description, ProjectType.EXPERIMENT);
		assertEquals(experiment.personId(), personId);
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
	
	//測定の定義のテスト
	@Test
	public void testDefineMeasurement() {
		PersonId personId = new PersonId("111");
		VisionId visionId = new VisionId("111");
		ProjectId experimentId = new ProjectId("111");
		String name = "111";
		String description = "111";
		Project experiment = new Project(personId, visionId, experimentId, name, description, ProjectType.EXPERIMENT);
		Goal measurement = new Goal("111", "111");
		experiment.defineGoal(measurement);
		//測定は値オブジェクトなので等価。
		assertEquals(experiment.goal(), measurement);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDefineNoMeasurement() {
		PersonId personId = new PersonId("111");
		VisionId visionId = new VisionId("111");
		ProjectId experimentId = new ProjectId("111");
		String name = "111";
		String description = "111";
		Project experiment = new Project(personId, visionId, experimentId, name, description, ProjectType.EXPERIMENT);
		Goal measurement = null;
		experiment.defineGoal(measurement);
		//実行されない。
		experiment.goal();
	}

}
