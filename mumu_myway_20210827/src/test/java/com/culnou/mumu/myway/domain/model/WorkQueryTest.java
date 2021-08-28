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
public class WorkQueryTest {
	
	@Qualifier("workMongoRepository")
	@Autowired
	private WorkRepository workRepository;
	
	@Qualifier("workMongoQuery")
	@Autowired
	private WorkQuery workQuery;

	@Qualifier("actionMongoRepository")
	@Autowired
	private ActionRepository actionRepository;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		workRepository.removeAll();
	}

	@Test
	public void testFindWorksOfAction() throws Exception{
		PersonId personId = new PersonId("111");
		ActionId actionId = new ActionId("111");
		ProjectId projectId = new ProjectId("111");
		String name = "111";
		String description = "111";
		Action action = new Action(personId, projectId, actionId, name, description);
		WorkId workId = new WorkId("work111");
		Work work = action.defineWork(workId, "111", "111");
		work.changeStatus(WorkStatus.DOING);
		workRepository.save(work);
		List<Work> works = workQuery.findWorksOfAction(actionId);
		assertEquals(works.get(0).workId(), workId);
	}

}
