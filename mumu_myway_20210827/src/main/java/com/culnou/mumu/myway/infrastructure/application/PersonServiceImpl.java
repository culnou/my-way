package com.culnou.mumu.myway.infrastructure.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.myway.application.PersonService;
import com.culnou.mumu.myway.controller.PersonDto;
import com.culnou.mumu.myway.controller.ProjectDto;
import com.culnou.mumu.myway.controller.UserDto;
import com.culnou.mumu.myway.controller.VisionDto;
import com.culnou.mumu.myway.domain.model.Person;
import com.culnou.mumu.myway.domain.model.PersonFactory;
import com.culnou.mumu.myway.domain.model.PersonId;

import com.culnou.mumu.myway.domain.model.PersonRepository;
import com.culnou.mumu.myway.domain.model.Project;
import com.culnou.mumu.myway.domain.model.ProjectId;
import com.culnou.mumu.myway.domain.model.ProjectRepository;
import com.culnou.mumu.myway.domain.model.ProjectType;
import com.culnou.mumu.myway.domain.model.User;
import com.culnou.mumu.myway.domain.model.Vision;
import com.culnou.mumu.myway.domain.model.VisionId;
import com.culnou.mumu.myway.domain.model.VisionRepository;

@Service("personServiceImpl")
@Transactional
public class PersonServiceImpl implements PersonService {
	
	@Qualifier("personMongoRepository")
	@Autowired
	private PersonRepository personRepository;
	@Qualifier("visionMongoRepository")
	@Autowired
	private VisionRepository visionRepository;
	@Qualifier("projectMongoRepository")
	@Autowired
	private ProjectRepository projectRepository;

	@Override
	public void assignPerson(UserDto user) throws Exception {
		// TODO Auto-generated method stub
		UserDto userDto = (UserDto)user;
		User usr = new User(userDto.getId(), userDto.getFirstName(), userDto.getLastName(), userDto.getFullName(), userDto.getEmail());
		Person person = PersonFactory.creatPerson(usr);
        personRepository.save(person);
	}

	@Override
	public PersonDto createPerson(UserDto user) throws Exception {
		// TODO Auto-generated method stub
		
		UserDto userDto = (UserDto)user;
		User usr = new User(userDto.getId(), userDto.getFirstName(), userDto.getLastName(), userDto.getFullName(), userDto.getEmail());
		Person person = PersonFactory.creatPerson(usr);
        personRepository.save(person);
		return new PersonDto(person.personId().id(), person.fullName().firstName(), person.fullName().lastName(), person.email().address());
		
		
	}
	@Override
	public void updatePerson(PersonDto person) throws Exception {
		// TODO Auto-generated method stub
		PersonId personId = new PersonId(person.getId());
		Person readPerson = personRepository.personOfId(personId);
		readPerson.setPhilosophy(person.getPhilosophy());
		readPerson.setPurpose(person.getPurpose());
		readPerson.setActionGuideline(person.getActionGuideline());
		personRepository.save(readPerson);
	}
	
	@Override
	public PersonDto findPersonById(String id) throws Exception {
		// TODO Auto-generated method stub
		PersonId personId = new PersonId(id);
		Person person = personRepository.personOfId(personId);
		PersonDto personDto = new PersonDto(person.personId().id(), person.fullName().firstName(), person.fullName().lastName(), person.email().address());
		if(person.philosophy() != null) {
			personDto.setPhilosophy(person.philosophy());
		}
		if(person.purpose() != null) {
			personDto.setPurpose(person.purpose());
		}
		if(person.actionGuideline() != null) {
			personDto.setActionGuideline(person.actionGuideline());
		}
		return personDto;
	}

	@Override
	public void deletePerson(String id) throws Exception {
		// TODO Auto-generated method stub
		PersonId personId = new PersonId(id);
		Person person = personRepository.personOfId(personId);
		personRepository.remove(person);
		
		
	}

	@Override
	public List<VisionDto> findVisionsByPersonId(String personId) throws Exception {
		// TODO Auto-generated method stub
		PersonId pid = new PersonId(personId);
		List<Vision> readVisions = visionRepository.visionsOfPerson(pid);
		List<VisionDto> visionDtos = new ArrayList<>();
		for(Vision vision : readVisions) {
			VisionDto dto = new VisionDto();
			dto.setId(vision.visionId().id());
			dto.setPersonId(vision.personId().id());
			dto.setVisionId(vision.visionId().id());
			dto.setVisionType(vision.visionType());
			dto.setTitle(vision.title());
			dto.setContent(vision.content());
			visionDtos.add(dto);
		}
		return visionDtos;
	}

	@Override
	public VisionDto findVisionById(String id) throws Exception {
		// TODO Auto-generated method stub
		VisionId visionId = new VisionId(id);
		Vision vision = visionRepository.visionOfId(visionId);
		if(vision == null) {
			throw new Exception("The vision may not exist.");
		}
		VisionDto dto = new VisionDto();
		dto.setId(vision.visionId().id());
		dto.setPersonId(vision.personId().id());
		dto.setVisionId(vision.visionId().id());
		dto.setVisionType(vision.visionType());
		dto.setTitle(vision.title());
		dto.setContent(vision.content());
		return dto;
	}

	@Override
	public VisionDto addVision(VisionDto visionDto) throws Exception {
		// TODO Auto-generated method stub
		//個人の取得
		PersonId personId = new PersonId(visionDto.getPersonId());
		Person person = personRepository.personOfId(personId);
		//個人の存在チェック
		if(person == null) {
			throw new Exception("The person may not exist.");
		}
		//ビジョンタイプのチェック
		if(visionDto.getVisionType() == null) {
			throw new Exception("The visionType may not exist.");
		}
		//識別子オブジェクトの生成
		VisionId visionId = visionRepository.nextIdentity();
		//ビジョンの生成
		Vision vision = person.createVision(visionId, visionDto.getVisionType(), visionDto.getTitle(), visionDto.getContent());
		//ビジョンの保存
		visionRepository.save(vision);
		
		visionDto.setId(vision.visionId().id());
		visionDto.setVisionId(vision.visionId().id());
		return visionDto;
	}

	@Override
	public void updateVision(VisionDto vision) throws Exception {
		// TODO Auto-generated method stub
		VisionId visionId = new VisionId(vision.getVisionId());
		Vision readVision = visionRepository.visionOfId(visionId);
		if(readVision == null) {
			throw new Exception("The vision may not exist.");
		}
		readVision.setContent(vision.getContent());
		visionRepository.save(readVision);
	}

	@Override
	public void deleteVision(String id) throws Exception {
		// TODO Auto-generated method stub
		VisionId visionId = new VisionId(id);
		Vision readVision = visionRepository.visionOfId(visionId);
		if(readVision == null) {
			throw new Exception("The vision may not exist.");
		}
		visionRepository.remove(readVision);
		
	}

	@Override
	public void updateProject(ProjectDto projectDto) throws Exception {
		// TODO Auto-generated method stub
		ProjectId projectId = new ProjectId(projectDto.getProjectId());
		Project readProject = projectRepository.projectOfId(projectId);
		if(readProject == null) {
			throw new Exception("The project may not exist.");
		}
		readProject.setName(projectDto.getName());
		readProject.setDescription(projectDto.getDescription());
		readProject.setCriteria(projectDto.getCriteria());
		readProject.setDeadline(projectDto.getDeadline());
		readProject.setIndicator(projectDto.getIndicator());
		readProject.setTerm(projectDto.getTerm());
		readProject.addExpendedTime(projectDto.getExpendedTime());
		projectRepository.save(readProject);
	}

	@Override
	public void deleteProject(String id) throws Exception {
		// TODO Auto-generated method stub
		ProjectId projectId = new ProjectId(id);
		Project readProject = projectRepository.projectOfId(projectId);
		if(readProject == null) {
			throw new Exception("The project may not exist.");
		}
		projectRepository.remove(readProject);
	}

	@Override
	public ProjectDto addProject(ProjectDto projectDto) throws Exception {
		// TODO Auto-generated method stub
		//ビジョンの取得
		VisionId visionId = new VisionId(projectDto.getVisionId());
		Vision vision = visionRepository.visionOfId(visionId);
		//ビジョンの存在チェック
		if(vision == null) {
			throw new Exception("The vision may not exist.");
		}
		//プロジェクトタイプのチェック
		if(projectDto.getProjectType() == null) {
			throw new Exception("The projectType may not exist.");
		}
		//識別子オブジェクトの生成
		ProjectId projectId = projectRepository.nextIdentity();
		//プロジェクトの生成
		Project project = vision.launchProject(projectId, projectDto.getName(), projectDto.getDescription(), projectDto.getProjectType());
		project.setCriteria(projectDto.getCriteria());
		project.setDeadline(projectDto.getDeadline());
		project.setIndicator(projectDto.getIndicator());
		project.setTerm(projectDto.getTerm());
		project.addExpendedTime(projectDto.getExpendedTime());
		//プロジェクトの保存
		projectRepository.save(project);
		
		projectDto.setId(projectId.id());
		projectDto.setProjectId(projectId.id());
		projectDto.setPersonId(project.personId().id());
		return projectDto;
	}

	@Override
	public ProjectDto findProjectById(String id) throws Exception {
		// TODO Auto-generated method stub
		ProjectId projectId = new ProjectId(id);
		Project readProject = projectRepository.projectOfId(projectId);
		if(readProject == null) {
			throw new Exception("The project may not exist.");
		}
		ProjectDto doc = new ProjectDto();
		doc.setId(readProject.projectId().id());
		doc.setProjectId(readProject.projectId().id());
		doc.setVisionId(readProject.visionId().id());
		doc.setPersonId(readProject.personId().id());
		doc.setProjectType(readProject.projectType());
		doc.setName(readProject.name());
		doc.setDescription(readProject.description());
		doc.setDeadline(readProject.deadline());
		doc.setIndicator(readProject.indicator());
		doc.setTerm(readProject.term());
		doc.setExpendedTime(readProject.expendedTime());
		doc.setCriteria(readProject.criteria());
		return doc;
	}

	@Override
	public List<ProjectDto> findProjectsByVisionIdAndProjectType(String visionId, ProjectType projectType)
			throws Exception {
		// TODO Auto-generated method stub
		List<Project> projects = projectRepository.projectsOfVisionAndProjectType(new VisionId(visionId), projectType);
		List<ProjectDto> docs = new ArrayList<>();
		for(Project readProject : projects) {
			ProjectDto doc = new ProjectDto();
			doc.setId(readProject.projectId().id());
			doc.setProjectId(readProject.projectId().id());
			doc.setVisionId(readProject.visionId().id());
			doc.setPersonId(readProject.personId().id());
			doc.setProjectType(readProject.projectType());
			doc.setName(readProject.name());
			doc.setDescription(readProject.description());
			doc.setDeadline(readProject.deadline());
			doc.setIndicator(readProject.indicator());
			doc.setTerm(readProject.term());
			doc.setExpendedTime(readProject.expendedTime());
			doc.setCriteria(readProject.criteria());
			docs.add(doc);
		}
		return docs;
	}

	

	

	

	

}
