package com.culnou.mumu.myway.domain.model;



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
public class VisionServiceTest {
	
	@Autowired
	private VisionService visionService;

	@Qualifier("visionMongoRepository")
	@Autowired
	private VisionRepository visionRepository;
	
	@Qualifier("projectMongoRepository")
	@Autowired
	private ProjectRepository projectRepository;
	
	private Vision testVision;
	private Vision testVision2;
	private Project testProject;
	
	@Before
	public void setUp() throws Exception {
		PersonId personId = new PersonId("111");
		FullName fullName = new FullName("taro", "yamada");
		Email email = new Email("ss@ss.com");
		Person person = new Person(personId, fullName, email);
		
		VisionId visionId = visionRepository.nextIdentity();
		VisionType visionType = VisionType.BUSINESS;
		String content = "111";
		String title = "111";
		Vision vision = person.createVision(visionId, visionType, title, content);
		visionRepository.save(vision);
		testVision = visionRepository.visionOfId(vision.visionId());
		System.out.println("***** vision " +  testVision.visionId().id());
		
		ProjectId projectId = projectRepository.nextIdentity();
		String projectName = "111";
		String description = "111";
		Project project = vision.launchProject(projectId, projectName, description, ProjectType.EXPERIMENT);
		projectRepository.save(project);
		testProject = projectRepository.projectOfId(project.projectId());
		System.out.println("***** project " +  testProject.projectId().id());
		
		VisionId visionId2 = visionRepository.nextIdentity();
		VisionType visionType2 = VisionType.BUSINESS;
		String content2 = "111";
		String title2 = "111";
		Vision vision2 = person.createVision(visionId2, visionType2, title2, content2);
		visionRepository.save(vision2);
		testVision2 = visionRepository.visionOfId(vision2.visionId());
		System.out.println("***** vision2 " +  testVision2.visionId().id());
		
	}

	@After
	public void tearDown() throws Exception {
		visionRepository.remove(testVision);
		projectRepository.remove(testProject);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testVisionIdNotNullOfRemoveVision() throws Exception{
		visionService.removeVison(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testVisionExistOfRemoveVision() throws Exception{
		VisionId visionId = new VisionId("111");
		visionService.removeVison(visionId);
	}
	
	@Test(expected = ProjectExistException.class)
	public void testProjectOfVisionNotExistOfRemoveVision() throws Exception{
		visionService.removeVison(testVision.visionId());
		
	}
	@Test
	public void testRemoveVision() throws Exception{
		visionService.removeVison(testVision2.visionId());
	}

}
