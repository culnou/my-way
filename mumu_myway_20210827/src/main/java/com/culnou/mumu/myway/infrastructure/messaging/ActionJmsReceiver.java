package com.culnou.mumu.myway.infrastructure.messaging;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.myway.domain.model.Action;
import com.culnou.mumu.myway.domain.model.ActionId;
import com.culnou.mumu.myway.domain.model.ActionRemoved;
import com.culnou.mumu.myway.domain.model.ActionRepository;
import com.culnou.mumu.myway.domain.model.Person;
import com.culnou.mumu.myway.domain.model.PersonId;
import com.culnou.mumu.myway.domain.model.PersonRepository;
import com.culnou.mumu.myway.domain.model.Project;
import com.culnou.mumu.myway.domain.model.ProjectRepository;
import com.culnou.mumu.myway.domain.model.Work;
import com.culnou.mumu.myway.domain.model.WorkRepository;

@Service
@Transactional
public class ActionJmsReceiver {
	@Qualifier("personMongoRepository")
	@Autowired
	private PersonRepository personRepository;
	@Qualifier("projectMongoRepository")
	@Autowired
	private ProjectRepository projectRepository;
	@Qualifier("actionMongoRepository")
	@Autowired
	private ActionRepository actionRepository;
	@Qualifier("workMongoRepository")
	@Autowired
	private WorkRepository workRepository;

	@JmsListener(destination = "removeActionQueue", containerFactory = "mumuFactory")
	public void removeAction(ActionRemoved actionRemoved) throws Exception{
		
		//個人の確認
		PersonId personId = new PersonId(actionRemoved.getPersonId());
		Person person = personRepository.personOfId(personId);
		if(person == null) {
			throw new Exception("The person may not exist.");
		}
		
		//関連するプロジェクトの減算
		Action action = actionRepository.actionOfId(new ActionId(actionRemoved.getActionId()));
		if(action == null) {
			throw new Exception("The action may not exist.");
		}
		Project project = projectRepository.projectOfId(action.projectId());
		project.addExpendedTime(-actionRemoved.getExpendedTime());
		projectRepository.save(project);
		
		//関連する作業の削除
		List<Work> works = workRepository.worksOfAction(action.actionId());
		workRepository.removeAll(works);
		
		//アクションの削除
		actionRepository.remove(action);
	}
}
