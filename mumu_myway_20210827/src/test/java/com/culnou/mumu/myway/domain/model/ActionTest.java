package com.culnou.mumu.myway.domain.model;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



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
		action.addExpendedTime(-1);
		//実行されない。
		action.name();
	}
	//識別子の不変性が保持されるか検証する
	@Test(expected = IllegalStateException.class)
	public void testInvariantOfIdentifier() {
		PersonId personId = new PersonId("111");
		ActionId actionId = new ActionId("111");
		ProjectId projectId = new ProjectId("111");
		String name = "111";
		String description = "111";
		Action action = new Action(personId, projectId, actionId, name, description);
		//識別子は再度設定できない。
		ActionId actionId2 = new ActionId("222");
		action.setActionId(actionId2);
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
	
	//消費時間の加算のテスト
	@Test
	public void testAddExpendedTime() {
		PersonId personId = new PersonId("111");
		ActionId actionId = new ActionId("111");
		ProjectId projectId = new ProjectId("111");
		String name = "111";
		String description = "111";
		Action action = new Action(personId, projectId, actionId, name, description);
		action.addExpendedTime(1);
		action.addExpendedTime(2);
		assertEquals(action.expendedTime(), 3);
	}
	
    @Test(expected = Exception.class)
	public void testAddExpendedTimeByNegativeResult() {
		PersonId personId = new PersonId("111");
		ActionId actionId = new ActionId("111");
		ProjectId projectId = new ProjectId("111");
		String name = "111";
		String description = "111";
		Action action = new Action(personId, projectId, actionId, name, description);
		action.addExpendedTime(1);
		System.out.println("*** action.expendedTime " + action.expendedTime());
		action.addExpendedTime(-2);
		//実行されない。
		System.out.println("*** action.expendedTime " + action.expendedTime());
	}
    @Test(expected = IllegalArgumentException.class)
	public void testSetExpendedTimeByNegative() {
    	PersonId personId = new PersonId("111");
		ActionId actionId = new ActionId("111");
		ProjectId projectId = new ProjectId("111");
		String name = "111";
		String description = "111";
		Action action = new Action(personId, projectId, actionId, name, description);
		action.setExpendedTime(-2);
		//実行されない。
		System.out.println("*** action.expendedTime " + action.expendedTime());
    }
    //ファクトリーメソッドのテスト
    @Test
    public void testDefineWOrk() throws Exception{
    	PersonId personId = new PersonId("111");
		ActionId actionId = new ActionId("111");
		ProjectId projectId = new ProjectId("111");
		String name = "111";
		String description = "111";
		Action action = new Action(personId, projectId, actionId, name, description);
		WorkId workId = new WorkId("111");
		Date startTime = new Date();
		Date endTime = new Date();
		Work work = action.defineWork(workId, name, description,startTime, endTime);
		assertEquals(work.personId(), personId);
		assertEquals(work.workId(), workId);
		assertEquals(work.actionId(), actionId);
		assertEquals(work.name(), name);
		assertEquals(work.description(), description);
    }

}
