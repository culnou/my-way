package com.culnou.mumu.myway.domain.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
public class VisionRepositoryTest {
	
	//具象クラスをサービス名で切り替えられるようにする。
	@Qualifier("visionMongoRepository")
	@Autowired
	private VisionRepository visionRepository;

	//テスト用エンティティ
	private List<Vision> testVisions = new ArrayList<>();
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		//テスト用エンティティの削除
		visionRepository.removeAll(testVisions);
	}

	@Test
	public void testSaveAndFindOneVision() throws Exception{
		PersonId personId = new PersonId("111");
		FullName fullName = new FullName("taro", "yamada");
		Email email = new Email("ss@ss.com");
		Person person = new Person(personId, fullName, email);
		
		VisionId visionId = visionRepository.nextIdentity();
		VisionType visionType = VisionType.BUSINESS;
		String content = "111";
		
		Vision vision = person.createVision(visionId, visionType, content);
		visionRepository.save(vision);
		
		this.testVisions.add(vision);
		
		Vision readVision = visionRepository.visionOfId(visionId);
		assertNotNull(readVision);
		assertEquals(readVision.personId(), personId);
		assertEquals(readVision.visionId(), visionId);
		assertEquals(readVision.visionType(), visionType);
		assertEquals(readVision.content(), content);
	}
	
	@Test
	public void testVisionsOfPerson() throws Exception{
		List<Vision> saveVisions = new ArrayList<>();
		
		PersonId personId = new PersonId("111");
		FullName fullName = new FullName("taro", "yamada");
		Email email = new Email("ss@ss.com");
		Person person = new Person(personId, fullName, email);
		
		VisionId visionId = visionRepository.nextIdentity();
		VisionType visionType = VisionType.BUSINESS;
		String content = "111";
		
		Vision vision = person.createVision(visionId, visionType, content);
		saveVisions.add(vision);
		testVisions.add(vision);
		
		VisionId visionId2 = visionRepository.nextIdentity();
		Vision vision2 = person.createVision(visionId2, visionType, content);
		saveVisions.add(vision2);
		testVisions.add(vision2);
		visionRepository.saveAll(saveVisions);
		List<Vision> visions = visionRepository.visionsOfPerson(personId);
		assertEquals(visions.size(), 2);
		assertEquals(visions.get(0).personId(), personId);
	}
	
	@Test
	public void testVisionsOfVisionType() throws Exception{
		
		PersonId personId = new PersonId("111");
		FullName fullName = new FullName("taro", "yamada");
		Email email = new Email("ss@ss.com");
		Person person = new Person(personId, fullName, email);
		
		VisionId visionId = visionRepository.nextIdentity();
		VisionType visionType = VisionType.CAPABILITY;
		String content = "111";
		
		Vision vision = person.createVision(visionId, visionType, content);
		visionRepository.save(vision);
		testVisions.add(vision);
		
		VisionId visionId2 = visionRepository.nextIdentity();
		Vision vision2 = person.createVision(visionId2, visionType, content);
		visionRepository.save(vision2);
		testVisions.add(vision2);
		
		List<Vision> visions = visionRepository.visionsOfVisionType(VisionType.CAPABILITY);
		assertEquals(visions.size(), 2);
		assertEquals(visions.get(0).visionType(), VisionType.CAPABILITY);
	}

}
