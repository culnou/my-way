package com.culnou.mumu.myway.domain.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;




public class ActionIdTest {

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
		ActionId actionId = new ActionId("111");
		ActionId clone = actionId.clone();
		assertEquals(actionId, clone);
		//副作用のないメソッドの実行
		System.out.println(actionId.id());
		//メソッドを実行することによって自身の状態が変化していないか検証。
		assertEquals(actionId, clone);
	}
	@Test
	public void testEquality() {
		ActionId id1 = new ActionId("111");
		ActionId id2 = new ActionId("111");
		assertEquals(id1, id2);
	}
	
	@Test
	public void testExchangeability() {
		ActionId id1 = new ActionId("111");
		ActionId id2 = new ActionId("222");
		ActionId id3 = new ActionId("222");
		assertNotEquals(id1, id3);
		//id1をid2に置き換える。
		id1 = id2;
		assertEquals(id1, id3);
	}

}
