package com.culnou.mumu.myway.domain.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.culnou.mumu.myway.domain.model.Person;
import com.culnou.mumu.myway.domain.model.PersonId;
import com.culnou.mumu.myway.domain.model.Vision;
import com.culnou.mumu.myway.domain.model.VisionId;
import com.culnou.mumu.myway.domain.model.VisionRepository;
import com.culnou.mumu.myway.domain.model.VisionType;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VisionRepositoryTest {
	
	//具象クラスをサービス名で切り替えられるようにする。
	@Qualifier("visionMongoRepository")
	@Autowired
	private VisionRepository visionRepository;
	@Qualifier("visionMongoQuery")
	@Autowired
	private VisionQuery visionQuery;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		visionRepository.removeAll();
	}

	@Test
	public void testSaveAndFindOneVision() throws Exception{
		String name = "111";
		PersonId personId = new PersonId("111");
		Person person = new Person(personId, name);
		
		VisionId visionId = visionRepository.nextIdentity();
		VisionType visionType = VisionType.BUSINESS;
		String content = "111";
		
		Vision vision = person.createVision(visionId, visionType, content);
		visionRepository.save(vision);
		Vision readVision = visionQuery.findById(visionId);
		assertNotNull(readVision);
		assertEquals(readVision.personId(), personId);
		assertEquals(readVision.visionId(), visionId);
		assertEquals(readVision.visionType(), visionType);
		assertEquals(readVision.content(), content);
	}

}
