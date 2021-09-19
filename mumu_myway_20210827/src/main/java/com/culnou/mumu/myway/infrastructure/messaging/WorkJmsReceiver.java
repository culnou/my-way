package com.culnou.mumu.myway.infrastructure.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.myway.domain.model.Action;
import com.culnou.mumu.myway.domain.model.ActionId;
import com.culnou.mumu.myway.domain.model.ActionRepository;
import com.culnou.mumu.myway.domain.model.Person;
import com.culnou.mumu.myway.domain.model.PersonId;
import com.culnou.mumu.myway.domain.model.PersonRepository;
import com.culnou.mumu.myway.domain.model.Project;
import com.culnou.mumu.myway.domain.model.ProjectId;
import com.culnou.mumu.myway.domain.model.ProjectRepository;

import com.culnou.mumu.myway.domain.model.WorkSaved;

@Service
@Transactional
public class WorkJmsReceiver{
	@Qualifier("personMongoRepository")
	@Autowired
	private PersonRepository personRepository;
	@Qualifier("projectMongoRepository")
	@Autowired
	private ProjectRepository projectRepository;
	@Qualifier("actionMongoRepository")
	@Autowired
	private ActionRepository actionRepository;
	
	
	@JmsListener(destination = "mumuQueue", containerFactory = "mumuFactory")
	public void receiveWork(WorkSaved workSaved) throws Exception{
		//個人の確認
		PersonId personId = new PersonId(workSaved.getPersonId());
		Person person = personRepository.personOfId(personId);
		if(person == null) {
			throw new Exception("The person may not exist.");
		}
		
		//プロジェクトの消費時間の加算
		Project project = projectRepository.projectOfId(new ProjectId(workSaved.getProjectId()));
		if(project == null) {
			throw new Exception("The project may not exist.");
		}
		project.addExpendedTime(workSaved.getExpendedTime());
		projectRepository.save(project);
		
		//アクションの消費時間の加算
		Action action = actionRepository.actionOfId(new ActionId(workSaved.getActionId()));
		if(action == null) {
			throw new Exception("The action may not exist.");
		}
		action.addExpendedTime(workSaved.getExpendedTime());
		actionRepository.save(action);
		
	}


	

}
