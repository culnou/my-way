package com.culnou.mumu.myway.domain.model;



import java.util.Date;

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
public class ActionServiceTest {
	
	@Autowired
	private ActionService actionService;
	
	@Qualifier("actionMongoRepository")
	@Autowired
	private ActionRepository actionRepository;
	@Qualifier("workMongoRepository")
	@Autowired
	private WorkRepository workRepository;
	private Action testAction;
	

	@Before
	public void setUp() throws Exception {
		
		
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test(expected = IllegalArgumentException.class)
	public void testActionIdNullOfRemoveAction() throws Exception{
		actionService.removeAction(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testActionNotExistOfRemoveAction() throws Exception{
		actionService.removeAction(new ActionId("111"));
	}
	
	@Test
	public void testRemoveAction() throws Exception{
		PersonId personId = new PersonId("111");
		VisionId visionId = new VisionId("111");
		ProjectId projectId = new ProjectId("111");
		String name = "111";
		String description = "111";
		Project project = new Project(personId, visionId, projectId, name, description, ProjectType.EXPERIMENT);
		Action action = project.defineAction(new ActionId("action111"), "111", "111");
		actionRepository.save(action);
		ActionId actionId = new ActionId("action111");
		testAction = actionRepository.actionOfId(actionId);
		Date startTime = new Date();
		Date endTime = new Date();
		Work work = testAction.defineWork(new WorkId("work111"), "111", "111",startTime, endTime);
		work.changeStatus(WorkStatus.DOING);
		workRepository.save(work);
		actionService.removeAction(testAction.actionId());
	}

}
