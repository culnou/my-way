package com.culnou.mumu.myway.domain.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;



public class PersonTest {
	
	//具象クラスをサービス名で切り替えられるようにする。
	@Qualifier("visionMongoRepository")
	@Autowired
	private VisionRepository visionRepository;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	//NULLを防いで正しく初期化できる（セッターのテストも兼ねる）
	@Test(expected = IllegalArgumentException.class)
	public void testInitializeByName() {
		String name = "111";
		Person person = new Person(null, name);
		//実行されない。
		person.name();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInitializeByUserId() {
		PersonId personId = new PersonId("111");
		Person person = new Person(personId, null);
		//実行されない。
		person.name();
	}
	
	//識別子オブジェクトが正しく設定される
	@Test
	public void testPersonId() {
		String name = "111";
		PersonId personId1 = new PersonId("111");
		Person person = new Person(personId1, name);
		PersonId personId2 = person.personId();
		assertEquals(personId1, personId2);
	}
	
	//ファクトリのテスト
	@Test(expected = IllegalArgumentException.class)
	public void testCreateVisionByNullVisionType() throws Exception{
		String name = "111";
		PersonId personId = new PersonId("111");
		Person person = new Person(personId, name);
		VisionId visionId = new VisionId("111");
		String content = "111";
		Vision vision = person.createVision(visionId, null, content);
		//実行されない。
		vision.content();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testCreateVisionByNullContent() throws Exception{
		String name = "111";
		PersonId personId = new PersonId("111");
		Person person = new Person(personId, name);
		VisionId visionId = new VisionId("111");
		VisionType visionType = VisionType.BUSINESS;
		Vision vision = person.createVision(visionId, visionType, null);
		//実行されない。
		vision.content();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateVisionByNullVisonId() throws Exception{
		String name = "111";
		PersonId personId = new PersonId("111");
		Person person = new Person(personId, name);
		VisionType visionType = VisionType.BUSINESS;
		String content = "111";
		Vision vision = person.createVision(null, visionType, content);
		//実行されない。
		vision.content();
	}
	
	@Test
	public void testCreateVision() throws Exception{
		String name = "111";
		PersonId personId = new PersonId("111");
		Person person = new Person(personId, name);
		VisionId visionId = new VisionId("111");
		VisionType visionType = VisionType.BUSINESS;
		String content = "111";
		Vision vision = person.createVision(visionId, visionType, content);
		assertNotNull(vision);
		assertEquals(vision.personId(), personId);
		assertEquals(vision.visionId(), visionId);
		assertEquals(vision.visionType(), visionType);
		assertEquals(vision.content(), content);
	}
}
