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
public class WorkRepositoryTest {
	
	@Qualifier("workMongoRepository")
	@Autowired
	private WorkRepository workRepository;
	
	
	@Qualifier("actionMongoRepository")
	@Autowired
	private ActionRepository actionRepository;
	
	//テスト用エンティティ
	private List<Work> testWorks = new ArrayList<>();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		workRepository.removeAll(this.testWorks);
	}

	@Test
	public void testSaveAndFindOneWork() throws Exception{
		PersonId personId = new PersonId("111");
		ActionId actionId = new ActionId("111");
		ProjectId projectId = new ProjectId("111");
		String name = "111";
		String description = "111";
		Action action = new Action(personId, projectId, actionId, name, description);
		Work work = action.defineWork(new WorkId("work111"), "111", "111");
		work.changeStatus(WorkStatus.DOING);
		workRepository.save(work);
		this.testWorks.add(work);
		WorkId workId = new WorkId("work111");
		Work readWork = workRepository.workOfId(workId);
		assertEquals(readWork.personId(), personId);
		assertEquals(readWork.workId(), workId);
		assertEquals(readWork.actionId(), actionId);
		assertEquals(readWork.name(), "111");
		assertEquals(readWork.description(), "111");
		assertEquals(readWork.status(), WorkStatus.DOING);
		
	}
	
	@Test
	public void testWorksOfAction() throws Exception{
		List<Work> saveWorks = new ArrayList<>();
		PersonId personId = new PersonId("111");
		ActionId actionId = new ActionId("Action001");
		ProjectId projectId = new ProjectId("111");
		String name = "111";
		String description = "111";
		Action action = new Action(personId, projectId, actionId, name, description);
		WorkId workId = new WorkId("work001");
		Work work = action.defineWork(workId, "111", "111");
		work.changeStatus(WorkStatus.DOING);
		saveWorks.add(work);
		this.testWorks.add(work);
		WorkId workId2 = new WorkId("work002");
		Work work2 = action.defineWork(workId2, "111", "111");
		saveWorks.add(work2);
		this.testWorks.add(work2);
		workRepository.saveAll(saveWorks);
		List<Work> works = workRepository.worksOfAction(actionId);
		assertEquals(works.size(), 2);
		assertEquals(works.get(0).workId(), workId);
	}

}
