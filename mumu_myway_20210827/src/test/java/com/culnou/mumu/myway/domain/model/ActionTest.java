package com.culnou.mumu.myway.domain.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.culnou.mumu.myway.domain.model.Action;
import com.culnou.mumu.myway.domain.model.ActionId;
import com.culnou.mumu.myway.domain.model.PersonId;
import com.culnou.mumu.myway.domain.model.ProjectId;



public class ActionTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInitializeByNoPersonId() {
		PersonId personId = null;
		ActionId actionId = new ActionId("111");
		ProjectId projectId = new ProjectId("111");
		String name = "111";
		String description = "111";
		Action action = new Action(personId, projectId, actionId, name, description);
		//実行されない。
		action.name();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testInitializeByNoProjectId() {
		PersonId personId = new PersonId("111");
		ActionId actionId = new ActionId("111");
		ProjectId projectId = null;
		String name = "111";
		String description = "111";
		Action action = new Action(personId, projectId, actionId, name, description);
		//実行されない。
		action.name();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testInitializeByNoActionId() {
		PersonId personId = new PersonId("111");
		ActionId actionId = null;
		ProjectId projectId = new ProjectId("111");
		String name = "111";
		String description = "111";
		Action action = new Action(personId, projectId, actionId, name, description);
		//実行されない。
		action.name();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testInitializeByNoName() {
		PersonId personId = new PersonId("111");
		ActionId actionId = new ActionId("111");
		ProjectId projectId = new ProjectId("111");
		String name = null;
		String description = "111";
		Action action = new Action(personId, projectId, actionId, name, description);
		//実行されない。
		action.name();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInitializeByNoDescription() {
		PersonId personId = new PersonId("111");
		ActionId actionId = new ActionId("111");
		ProjectId projectId = new ProjectId("111");
		String name = "111";
		String description = null;
		Action action = new Action(personId, projectId, actionId, name, description);
		//実行されない。
		action.name();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testInitializeByNegativeExpendedTime() {
		PersonId personId = new PersonId("111");
		ActionId actionId = new ActionId("111");
		ProjectId projectId = new ProjectId("111");
		String name = "111";
		String description = "111";
		Action action = new Action(personId, projectId, actionId, name, description);
		action.setExpendedTime(-1);
		//実行されない。
		action.name();
	}
	@Test
	public void testPersonId() {
		PersonId personId = new PersonId("111");
		ActionId actionId = new ActionId("111");
		ProjectId projectId = new ProjectId("111");
		String name = "111";
		String description = "111";
		Action action = new Action(personId, projectId, actionId, name, description);
		assertEquals(action.personId(), personId);
	}
	
	@Test
	public void testProjectId() {
		PersonId personId = new PersonId("111");
		ActionId actionId = new ActionId("111");
		ProjectId projectId = new ProjectId("111");
		String name = "111";
		String description = "111";
		Action action = new Action(personId, projectId, actionId, name, description);
		assertEquals(action.projectId(), projectId);
	}
	
	@Test
	public void testActionId() {
		PersonId personId = new PersonId("111");
		ActionId actionId = new ActionId("111");
		ProjectId projectId = new ProjectId("111");
		String name = "111";
		String description = "111";
		Action action = new Action(personId, projectId, actionId, name, description);
		assertEquals(action.actionId(), actionId);
	}

}
