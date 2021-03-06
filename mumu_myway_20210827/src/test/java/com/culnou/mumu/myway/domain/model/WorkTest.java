package com.culnou.mumu.myway.domain.model;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class WorkTest {

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
		WorkId workId = new WorkId("111");
		String name = "111";
		String description = "111";
		Date startTime = new Date();
		Date endTime = new Date();
		Work work = new Work(personId, actionId, workId, name, description, startTime, endTime);
		//実行されない。
		work.name();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testInitializeByNoActionId() {
		PersonId personId = new PersonId("111");
		ActionId actionId = null;
		WorkId workId = new WorkId("111");
		String name = "111";
		String description = "111";
		Date startTime = new Date();
		Date endTime = new Date();
		Work work = new Work(personId, actionId, workId, name, description, startTime, endTime);
		//実行されない。
		work.name();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testInitializeByNoWorkId() {
		PersonId personId = new PersonId("111");
		ActionId actionId = new ActionId("111");
		WorkId workId = null;
		String name = "111";
		String description = "111";
		Date startTime = new Date();
		Date endTime = new Date();
		Work work = new Work(personId, actionId, workId, name, description, startTime, endTime);
		//実行されない。
		work.name();
	}
	
	//識別子の不変性が保持されるか検証する
	@Test(expected = IllegalStateException.class)
	public void testInvariantOfIdentifier() {
		PersonId personId = new PersonId("111");
		ActionId actionId = new ActionId("111");
		WorkId workId = new WorkId("111");
		String name = "111";
		String description = "111";
		Date startTime = new Date();
		Date endTime = new Date();
		Work work = new Work(personId, actionId, workId, name, description, startTime, endTime);
		//識別子は再度設定できない。
		WorkId workId2 = new WorkId("222");
		work.setWorkId(workId2);
		//実行されない。
		work.name();
	}
	
	@Test
	public void testInitialize() {
		PersonId personId = new PersonId("111");
		ActionId actionId = new ActionId("111");
		WorkId workId = new WorkId("111");
		String name = "111";
		String description = "111";
		Date startTime = new Date();
		Date endTime = new Date();
		Work work = new Work(personId, actionId, workId, name, description, startTime, endTime);
		work.changeStatus(WorkStatus.DOING);
		work.setExpendedTime(1);
		assertEquals(work.personId(), personId);
		assertEquals(work.actionId(), actionId);
		assertEquals(work.workId(), workId);
		assertEquals(work.name(), name);
		assertEquals(work.description(), description);
		assertEquals(work.status(), WorkStatus.DOING);
		assertEquals(work.expendedTime(), 1);
	}
	/*
	@Test
	public void testAddExpendedTime() {
		PersonId personId = new PersonId("111");
		ActionId actionId = new ActionId("111");
		WorkId workId = new WorkId("111");
		String name = "111";
		String description = "111";
		Date startTime = new Date();
		Date endTime = new Date();
		Work work = new Work(personId, actionId, workId, name, description, startTime, endTime);
		work.addExpendedTime(1);
		work.addExpendedTime(2);
		assertEquals(work.expendedTime(), 3);
	}
	
	@Test(expected = Exception.class)
	public void testAddExpendedTimeByNegativeResult() {
		PersonId personId = new PersonId("111");
		ActionId actionId = new ActionId("111");
		WorkId workId = new WorkId("111");
		String name = "111";
		String description = "111";
		Date startTime = new Date();
		Date endTime = new Date();
		Work work = new Work(personId, actionId, workId, name, description, startTime, endTime);
		work.addExpendedTime(1);
		System.out.println("*** action.expendedTime " + work.expendedTime());
		work.addExpendedTime(-2);
		//実行されない。
		System.out.println("*** action.expendedTime " + work.expendedTime());
	}
	*/
	@Test(expected = IllegalArgumentException.class)
	public void testSetExpendedTimeByNegative() {
		PersonId personId = new PersonId("111");
		ActionId actionId = new ActionId("111");
		WorkId workId = new WorkId("111");
		String name = "111";
		String description = "111";
		Date startTime = new Date();
		Date endTime = new Date();
		Work work = new Work(personId, actionId, workId, name, description, startTime, endTime);
		work.setExpendedTime(-2);
		//実行されない。
		System.out.println("*** action.expendedTime " + work.expendedTime());
    }

}
