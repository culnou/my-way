package com.culnou.mumu.myway.domain.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class WorkIdTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetId() {
		String id = null;
		ActionId actionId = new ActionId(id);
		//実行されない。
		actionId.id();
	}

	@Test
	public void testInvariant() {
		//自身とクローンの等価性検証。
		WorkId workId = new WorkId("111");
		WorkId clone = workId.clone();
		assertEquals(workId, clone);
		//副作用のないメソッドの実行
		System.out.println(workId.id());
		//メソッドを実行することによって自身の状態が変化していないか検証。
		assertEquals(workId, clone);
	}
	@Test
	public void testEquality() {
		WorkId id1 = new WorkId("111");
		WorkId id2 = new WorkId("111");
		assertEquals(id1, id2);
	}
	
	@Test
	public void testExchangeability() {
		WorkId id1 = new WorkId("111");
		WorkId id2 = new WorkId("222");
		WorkId id3 = new WorkId("222");
		assertNotEquals(id1, id3);
		//id1をid2に置き換える。
		id1 = id2;
		assertEquals(id1, id3);
	}

}
