package com.culnou.mumu.myway.infrastructure.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.myway.application.PersonService;
import com.culnou.mumu.myway.controller.ActionDto;
import com.culnou.mumu.myway.controller.PersonDto;
import com.culnou.mumu.myway.controller.ProjectDto;
import com.culnou.mumu.myway.controller.UserDto;
import com.culnou.mumu.myway.controller.VisionDto;
import com.culnou.mumu.myway.controller.WorkDto;
import com.culnou.mumu.myway.domain.model.Action;
import com.culnou.mumu.myway.domain.model.ActionId;

import com.culnou.mumu.myway.domain.model.ActionRepository;
import com.culnou.mumu.myway.domain.model.ActionService;
import com.culnou.mumu.myway.domain.model.Person;
import com.culnou.mumu.myway.domain.model.PersonFactory;
import com.culnou.mumu.myway.domain.model.PersonId;

import com.culnou.mumu.myway.domain.model.PersonRepository;
import com.culnou.mumu.myway.domain.model.Project;
import com.culnou.mumu.myway.domain.model.ProjectId;
import com.culnou.mumu.myway.domain.model.ProjectRepository;
import com.culnou.mumu.myway.domain.model.ProjectService;
import com.culnou.mumu.myway.domain.model.ProjectType;
import com.culnou.mumu.myway.domain.model.User;
import com.culnou.mumu.myway.domain.model.Vision;
import com.culnou.mumu.myway.domain.model.VisionId;
import com.culnou.mumu.myway.domain.model.VisionRepository;
import com.culnou.mumu.myway.domain.model.VisionService;
import com.culnou.mumu.myway.domain.model.Work;
import com.culnou.mumu.myway.domain.model.WorkId;
import com.culnou.mumu.myway.domain.model.WorkRepository;
import com.culnou.mumu.myway.domain.model.WorkSaved;
import com.culnou.mumu.myway.domain.model.WorkSender;



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
	@Qualifier("actionMongoRepository")
	@Autowired
	private ActionRepository actionRepository;
	@Qualifier("workMongoRepository")
	@Autowired
	private WorkRepository workRepository;
	@Autowired
	@Qualifier("workJmsSender")
	private WorkSender workSender;
	@Autowired
	private ActionService actionService;
	@Autowired
	private VisionService visionService;
	@Autowired
	private ProjectService projectService;

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
		/*
		VisionId visionId = new VisionId(id);
		Vision readVision = visionRepository.visionOfId(visionId);
		if(readVision == null) {
			throw new Exception("The vision may not exist.");
		}
		visionRepository.remove(readVision);
		*/
		//ドメインサービスの実行。2021/09/30
		VisionId visionId = new VisionId(id);
		visionService.removeVison(visionId);
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
		//addExpendedTimeだと、更新する都度、消費時間が倍に増えるのでsetExpendedTimeに変更する。2021/10/24
		//readProject.addExpendedTime(projectDto.getExpendedTime());
		readProject.setExpendedTime(projectDto.getExpendedTime());
		projectRepository.save(readProject);
	}

	@Override
	public void deleteProject(String id) throws Exception {
		/*
		// TODO Auto-generated method stub
		ProjectId projectId = new ProjectId(id);
		Project readProject = projectRepository.projectOfId(projectId);
		if(readProject == null) {
			throw new Exception("The project may not exist.");
		}
		projectRepository.remove(readProject);
		*/
		//ドメインサービスの実行。2021/10/01
		ProjectId projectId = new ProjectId(id);
		projectService.removeProject(projectId);
	}

	@Override
	public ProjectDto addProject(ProjectDto projectDto) throws Exception {
		// TODO Auto-generated method stub
		//個人の取得
		PersonId personId = new PersonId(projectDto.getPersonId());
		Person person = personRepository.personOfId(personId);
		if(person == null) {
			throw new Exception("The person may not exist.");
		}
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

	@Override
	public void updateAction(ActionDto actionDto) throws Exception {
		// TODO Auto-generated method stub
		ActionId actionId = new ActionId(actionDto.getId());
		Action action = actionRepository.actionOfId(actionId);
		if(action == null) {
			throw new Exception("The action may not exist.");
		}
		action.setName(actionDto.getName());
		action.setDescription(actionDto.getDescription());
		//addExpendedTimeだと、更新する都度、消費時間が倍に増えるのでsetExpendedTimeに変更する。2021/10/24
		//action.addExpendedTime(actionDto.getExpendedTime());
		action.setExpendedTime(actionDto.getExpendedTime());
		//アクションのルーティン化のため追加。2021/10/26
		action.setRoutine(actionDto.isRoutine());
		actionRepository.save(action);
	}

	@Override
	public void deleteAction(String id) throws Exception {
		// TODO Auto-generated method stub
		/*
		ActionId actionId = new ActionId(id);
		Action action = actionRepository.actionOfId(actionId);
		if(action == null) {
			throw new Exception("The action may not exist.");
		}
		actionRepository.remove(action);
		*/
		//ドメインサービスで実施する。
		ActionId actionId = new ActionId(id);
		actionService.removeAction(actionId);
	}

	@Override
	public ActionDto findActionById(String id) throws Exception {
		// TODO Auto-generated method stub
		ActionId actionId = new ActionId(id);
		Action action = actionRepository.actionOfId(actionId);
		if(action == null) {
			throw new Exception("The action may not exist.");
		}
		ActionDto doc = new ActionDto();
		doc.setId(action.actionId().id());
		doc.setActionId(action.actionId().id());
		doc.setPersonId(action.personId().id());
		doc.setProjectId(action.projectId().id());
		doc.setName(action.name());
		doc.setDescription(action.description());
		doc.setExpendedTime(action.expendedTime());
		doc.setRoutine(action.isRoutine());
		return doc;
	}

	@Override
	public List<ActionDto> findActionsByProjectId(String projectId) throws Exception {
		// TODO Auto-generated method stub
		List<Action> actions = actionRepository.actionsOfProject(new ProjectId(projectId));
		List<ActionDto> docs = new ArrayList<>();
		for(Action action: actions) {
			ActionDto doc = new ActionDto();
			doc.setId(action.actionId().id());
			doc.setActionId(action.actionId().id());
			doc.setPersonId(action.personId().id());
			doc.setProjectId(action.projectId().id());
			doc.setName(action.name());
			doc.setDescription(action.description());
			doc.setExpendedTime(action.expendedTime());
			doc.setRoutine(action.isRoutine());
			docs.add(doc);
		}
		return docs;
	}
	
	@Override
	public List<ActionDto> findRoutineActions(String personId) throws Exception {
		// TODO Auto-generated method stub
		List<Action> actions = actionRepository.actionsOfRoutine(new PersonId(personId));
		List<ActionDto> docs = new ArrayList<>();
		for(Action action: actions) {
			ActionDto doc = new ActionDto();
			doc.setId(action.actionId().id());
			doc.setActionId(action.actionId().id());
			doc.setPersonId(action.personId().id());
			doc.setProjectId(action.projectId().id());
			doc.setName(action.name());
			doc.setDescription(action.description());
			doc.setExpendedTime(action.expendedTime());
			doc.setRoutine(action.isRoutine());
			docs.add(doc);
		}
		return docs;
	}
	

	@Override
	public void updateWork(WorkDto workDto) throws Exception {
		// TODO Auto-generated method stub
		Work work = workRepository.workOfId(new WorkId(workDto.getId()));
		if(work == null) {
			throw new Exception("The work may not exist.");
		}
		work.setName(workDto.getName());
		work.setDescription(workDto.getDescription());
		work.setStartTime(workDto.getStartTime());
		work.setEndTime(workDto.getEndTime());
		work.changeStatus(workDto.getWorkStatus());
		//work.addExpendedTime(workDto.getExpendedTime());
		work.setExpendedTime(workDto.getExpendedTime());
		workRepository.save(work);
	}

	@Override
	public void deleteWork(String id) throws Exception {
		// TODO Auto-generated method stub
		WorkId workId = new WorkId(id);
		Work work = workRepository.workOfId(workId);
		if(work == null) {
			throw new Exception("The work may not exist.");
		}
		//アクションの取得
		ActionId actionId = new ActionId(work.actionId().id());
		Action action = actionRepository.actionOfId(actionId);
		//メッセージングによって消費時間を減算する。
		WorkSaved workSaved = new WorkSaved();
		workSaved.setActionId(work.actionId().id());
		workSaved.setPersonId(work.personId().id());
		workSaved.setProjectId(action.projectId().id());
		workSaved.setExpendedTime(-(work.expendedTime()));
		workSender.sendWork(workSaved);
		
		workRepository.remove(work);
	}

	@Override
	public WorkDto findWorkById(String id) throws Exception {
		// TODO Auto-generated method stub
		WorkId workId = new WorkId(id);
		Work work = workRepository.workOfId(workId);
		if(work == null) {
			throw new Exception("The work may not exist.");
		}
		WorkDto doc = new WorkDto();
		doc.setId(work.workId().id());
		doc.setPersonId(work.personId().id());
		doc.setActionId(work.actionId().id());
		doc.setWorkId(work.workId().id());
		doc.setName(work.name());
		doc.setDescription(work.description());
		doc.setStartTime(work.startTime());
		doc.setEndTime(work.endTime());
		doc.setWorkStatus(work.status());
		doc.setExpendedTime(work.expendedTime());
		return doc;
	}

	@Override
	public List<WorkDto> findWorksByActionId(String actionId) throws Exception {
		// TODO Auto-generated method stub
		List<Work> works = workRepository.worksOfAction(new ActionId(actionId));
		List<WorkDto> docs = new ArrayList<>();
		for(Work work : works) {
			WorkDto doc = new WorkDto();
			doc.setId(work.workId().id());
			doc.setPersonId(work.personId().id());
			doc.setActionId(work.actionId().id());
			doc.setWorkId(work.workId().id());
			doc.setName(work.name());
			doc.setDescription(work.description());
			doc.setStartTime(work.startTime());
			doc.setEndTime(work.endTime());
			doc.setWorkStatus(work.status());
			doc.setExpendedTime(work.expendedTime());
			docs.add(doc);
		}
		return docs;
	}

	@Override
	public ActionDto addAction(ActionDto actionDto) throws Exception {
		// TODO Auto-generated method stub
		//個人の取得
		PersonId personId = new PersonId(actionDto.getPersonId());
		Person person = personRepository.personOfId(personId);
		if(person == null) {
			throw new Exception("The person may not exist.");
		}
		//プロジェクトの取得
		ProjectId projectId = new ProjectId(actionDto.getProjectId());
		Project project = projectRepository.projectOfId(projectId);
		//プロジェクトの存在チェック
		if(project == null) {
			throw new Exception("The project may not exist.");
		}
		//識別子オブジェクトの生成
		ActionId actionId = actionRepository.nextIdentity();
		//アクションの生成
		Action action = project.defineAction(actionId, actionDto.getName(), actionDto.getDescription());
		action.addExpendedTime(actionDto.getExpendedTime());
		//アクションのルーティン化のため追加。2021/10/26
		action.setRoutine(actionDto.isRoutine());
		actionRepository.save(action);
		
		actionDto.setId(action.actionId().id());
		actionDto.setActionId(action.actionId().id());
		actionDto.setPersonId(action.personId().id());
		return actionDto;
	}

	@Override
	public WorkDto addWork(WorkDto workDto) throws Exception {
		
		// TODO Auto-generated method stub
		//個人の取得
		PersonId personId = new PersonId(workDto.getPersonId());
		Person person = personRepository.personOfId(personId);
		if(person == null) {
			throw new Exception("The person may not exist.");
		}
		//アクションの取得
		ActionId actionId = new ActionId(workDto.getActionId());
		Action action = actionRepository.actionOfId(actionId);
		//アクションの存在チェック
		if(action == null) {
			throw new Exception("The action may not exist.");
		}
		//識別子オブジェクトの生成
		WorkId workId = workRepository.nextIdentity();
		//ワークの生成
		Work work = action.defineWork(workId, workDto.getName(), workDto.getDescription(), workDto.getStartTime(), workDto.getEndTime());
		work.changeStatus(workDto.getWorkStatus());
		//work.addExpendedTime(workDto.getExpendedTime());
		work.setExpendedTime(workDto.getExpendedTime());
		workRepository.save(work);
		
		//メッセージングによって消費時間を加算する。
		WorkSaved workSaved = new WorkSaved();
		workSaved.setActionId(work.actionId().id());
		workSaved.setPersonId(work.personId().id());
		workSaved.setProjectId(action.projectId().id());
		workSaved.setExpendedTime(work.expendedTime());
		workSender.sendWork(workSaved);
		
		
		workDto.setId(work.workId().id());
		workDto.setWorkId(work.workId().id());
		workDto.setPersonId(work.personId().id());
		return workDto;
	}

	

	

	

	

	

}
