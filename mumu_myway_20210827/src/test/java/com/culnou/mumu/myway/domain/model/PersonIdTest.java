package com.culnou.mumu.myway.domain.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.culnou.mumu.myway.domain.model.PersonId;

public class PersonIdTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	//自己カプセル化のセッターの例外テスト：NULLを防いで正しく初期化できる
	@Test(expected = IllegalArgumentException.class)
	public void testSetId() {
		String id = null;
		PersonId personId = new PersonId(id);
		//実行されない。
		personId.id();
	}
	
	//値オブジェクトの不変性のテスト。
	@Test
	public void testInvariant() {
		//自身とクローンの等価性検証。
		PersonId personId = new PersonId("111");
		PersonId clone = personId.clone();
		assertEquals(personId, clone);
		//副作用のないメソッドの実行
		System.out.println(personId.id());
		//メソッドを実行することによって自身の状態が変化していないか検証。
		assertEquals(personId, clone);
	}
	
	//値オブジェクトの等価性のテスト。
	@Test
	public void testEquality() {
		PersonId id1 = new PersonId("111");
		PersonId id2 = new PersonId("111");
		assertEquals(id1, id2);
	}
	
	//値オブジェクトの交換可能性のテスト。
	@Test
	public void testExchangeability() {
		PersonId id1 = new PersonId("111");
		PersonId id2 = new PersonId("222");
		PersonId id3 = new PersonId("222");
		assertNotEquals(id1, id3);
		//id1をid2に置き換える。
		id1 = id2;
		assertEquals(id1, id3);
	}

}
