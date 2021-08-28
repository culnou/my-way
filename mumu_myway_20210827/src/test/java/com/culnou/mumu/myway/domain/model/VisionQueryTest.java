package com.culnou.mumu.myway.domain.model;

import static org.junit.Assert.*;

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
public class VisionQueryTest {
	
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
	public void testFindVisionsOfVisionType() throws Exception{
		
		String name = "111";
		PersonId personId = new PersonId("111");
		Person person = new Person(personId, name);
		
		VisionId visionId = visionRepository.nextIdentity();
		VisionType visionType = VisionType.BUSINESS;
		String content = "111";
		
		Vision vision = person.createVision(visionId, visionType, content);
		visionRepository.save(vision);
		
		VisionId visionId2 = visionRepository.nextIdentity();
		Vision vision2 = person.createVision(visionId2, visionType, content);
		visionRepository.save(vision2);
		
		
		List<Vision> visions = visionQuery.findVisionsOfVisionType(VisionType.BUSINESS);
		assertEquals(visions.size(), 2);
		assertEquals(visions.get(0).visionType(), VisionType.BUSINESS);
	}
	
	@Test
	public void testFindVisionsOfPerson() throws Exception{
		
		String name = "111";
		PersonId personId = new PersonId("111");
		Person person = new Person(personId, name);
		
		VisionId visionId = visionRepository.nextIdentity();
		VisionType visionType = VisionType.BUSINESS;
		String content = "111";
		
		Vision vision = person.createVision(visionId, visionType, content);
		visionRepository.save(vision);
		
		VisionId visionId2 = visionRepository.nextIdentity();
		Vision vision2 = person.createVision(visionId2, visionType, content);
		visionRepository.save(vision2);
		
		
		List<Vision> visions = visionQuery.findVisionsOfPerson(personId);
		assertEquals(visions.size(), 2);
		assertEquals(visions.get(0).personId(), personId);
	}
	
	
}
