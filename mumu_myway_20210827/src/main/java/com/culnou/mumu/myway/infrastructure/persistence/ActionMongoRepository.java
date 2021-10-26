package com.culnou.mumu.myway.infrastructure.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.myway.domain.model.AbstractActionRepository;
import com.culnou.mumu.myway.domain.model.Action;
import com.culnou.mumu.myway.domain.model.ActionId;
import com.culnou.mumu.myway.domain.model.PersonId;
import com.culnou.mumu.myway.domain.model.ProjectId;



@Service("actionMongoRepository")
@Transactional
public class ActionMongoRepository extends AbstractActionRepository {
	
	@Autowired
	private ActionMongoDataRepository actionRepository;

	@Override
	public ActionId nextIdentity() throws Exception {
		// TODO Auto-generated method stub
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        
		return new ActionId(str);
	}

	@Override
	public void save(Action action) throws Exception {
		// TODO Auto-generated method stub
		ActionDocument doc = this.convertActionToActionDocument(action);
		actionRepository.save(doc);

	}

	@Override
	public void remove(Action action) throws Exception {
		// TODO Auto-generated method stub
		ActionDocument doc = this.convertActionToActionDocument(action);
		actionRepository.delete(doc);

	}

	
	

	@Override
	public void saveAll(List<Action> actions) throws Exception {
		// TODO Auto-generated method stub
		for(Action action : actions) {
			this.save(action);
		}
		
	}

	@Override
	public void removeAll(List<Action> actions) throws Exception {
		// TODO Auto-generated method stub
		for(Action action : actions) {
			this.remove(action);
		}
		
	}

	@Override
	public Action actionOfId(ActionId actionId) throws Exception {
		// TODO Auto-generated method stub
		Optional<ActionDocument> readDoc = actionRepository.findById(actionId.id());
		if (readDoc.isPresent()){
			ActionDocument doc = readDoc.get();
			return this.convertActionDocumentToAction(doc);
		}else {
			return null;
		}
	}

	@Override
	public List<Action> actionsOfProject(ProjectId projectId) throws Exception {
		// TODO Auto-generated method stub
		List<ActionDocument> docs = actionRepository.findActionsByProjectId(projectId);
		return convertActionDocumentsToActions(docs);
	}
	
	//アクションのルーティン化のため追加。2021/10/26
	@Override
	public List<Action> actionsOfRoutine(PersonId personId) throws Exception {
		// TODO Auto-generated method stub
		List<ActionDocument> docs = actionRepository.findActionsByPersonIdAndRoutine(personId, true);
		return convertActionDocumentsToActions(docs);
	}
	private ActionDocument convertActionToActionDocument(Action action) {
		ActionDocument doc = new ActionDocument();
		doc.setId(action.actionId().id());
		doc.setPersonId(action.personId());
		doc.setProjectId(action.projectId());
		doc.setActionId(action.actionId());
		doc.setName(action.name());
		doc.setDescription(action.description());
		doc.setExpendedTime(action.expendedTime());
		//アクションのルーティン化のため追加。2021/10/26
		doc.setRoutine(action.isRoutine());
		return doc;
	}
	
	private Action convertActionDocumentToAction(ActionDocument doc) {
		Action action = this.convertFrom(doc);
		return action;
		
	}
	
	private List<Action> convertActionDocumentsToActions(List<ActionDocument> docs) {
		List<Action> actions = new ArrayList<>();
		for(ActionDocument doc : docs) {
			Action action = convertActionDocumentToAction(doc);
			actions.add(action);
		}
		return actions;
	}

	

}
