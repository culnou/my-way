package com.culnou.mumu.myway.domain.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProjectIdTest {

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
		ProjectId projectId = new ProjectId(id);
		//実行されない。
		projectId.id();
	}
	
	//値オブジェクトの不変性のテスト。
	@Test
	public void testInvariant() {
		//自身とクローンの等価性検証。
		ProjectId projectId = new ProjectId("111");
		ProjectId clone = projectId.clone();
		assertEquals(projectId, clone);
		//副作用のないメソッドの実行
		System.out.println(projectId.id());
		//メソッドを実行することによって自身の状態が変化していないか検証。
		assertEquals(projectId, clone);
	}
	@Test
	public void testEquality() {
		ProjectId id1 = new ProjectId("111");
		ProjectId id2 = new ProjectId("111");
		assertEquals(id1, id2);
	}
	@Test
	public void testExchangeability() {
		ProjectId id1 = new ProjectId("111");
		ProjectId id2 = new ProjectId("222");
		ProjectId id3 = new ProjectId("222");
		assertNotEquals(id1, id3);
		//id1をid2に置き換える。
		id1 = id2;
		assertEquals(id1, id3);
	}

}
