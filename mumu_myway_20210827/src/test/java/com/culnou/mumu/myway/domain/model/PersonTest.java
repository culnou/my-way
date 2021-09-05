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
	public void testInitializeByNullPersonId() {
		PersonId personId = null;
		FullName fullName = new FullName("taro", "yamada");
		Email email = new Email("ss@ss.com");
		Person person = new Person(personId, fullName, email);
		//実行されない。
		person.fullName();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInitializeByNullFullName() {
		PersonId personId = new PersonId("111");
		FullName fullName = null;
		Email email = new Email("ss@ss.com");
		Person person = new Person(personId, fullName, email);
		//実行されない。
		person.fullName();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testInitializeByNullEmail() {
		PersonId personId = new PersonId("111");
		FullName fullName = new FullName("taro", "yamada");
		Email email = null;
		Person person = new Person(personId, fullName, email);
		//実行されない。
		person.fullName();
	}
	//識別子の不変性が保持されるか検証する
	@Test(expected = IllegalStateException.class)
	public void testInvariantOfIdentifier() {
		PersonId personId = new PersonId("111");
		FullName fullName = new FullName("taro", "yamada");
		Email email = new Email("ss@ss.com");
		Person person = new Person(personId, fullName, email);
		PersonId personId2 = new PersonId("222");
		//識別子は再度設定できない。
		person.setPersonId(personId2);
		//実行されない。
		person.fullName();
	}
	
	//識別子オブジェクトが正しく設定される
	@Test
	public void testPersonId() {
		PersonId personId1 = new PersonId("111");
		FullName fullName = new FullName("taro", "yamada");
		Email email = new Email("ss@ss.com");
		Person person = new Person(personId1, fullName, email);
		PersonId personId2 = person.personId();
		assertEquals(personId1, personId2);
	}
	
	//ファクトリのテスト
	@Test(expected = IllegalArgumentException.class)
	public void testCreateVisionByNullVisionType() throws Exception{
		PersonId personId1 = new PersonId("111");
		FullName fullName = new FullName("taro", "yamada");
		Email email = new Email("ss@ss.com");
		Person person = new Person(personId1, fullName, email);
		VisionId visionId = new VisionId("111");
		String content = "111";
		Vision vision = person.createVision(visionId, null, content);
		//実行されない。
		vision.content();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testCreateVisionByNullContent() throws Exception{
		PersonId personId1 = new PersonId("111");
		FullName fullName = new FullName("taro", "yamada");
		Email email = new Email("ss@ss.com");
		Person person = new Person(personId1, fullName, email);
		VisionId visionId = new VisionId("111");
		VisionType visionType = VisionType.BUSINESS;
		Vision vision = person.createVision(visionId, visionType, null);
		//実行されない。
		vision.content();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateVisionByNullVisonId() throws Exception{
		PersonId personId1 = new PersonId("111");
		FullName fullName = new FullName("taro", "yamada");
		Email email = new Email("ss@ss.com");
		Person person = new Person(personId1, fullName, email);
		VisionType visionType = VisionType.BUSINESS;
		String content = "111";
		Vision vision = person.createVision(null, visionType, content);
		//実行されない。
		vision.content();
	}
	
	@Test
	public void testCreateVision() throws Exception{
		PersonId personId = new PersonId("111");
		FullName fullName = new FullName("taro", "yamada");
		Email email = new Email("ss@ss.com");
		Person person = new Person(personId, fullName, email);
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
