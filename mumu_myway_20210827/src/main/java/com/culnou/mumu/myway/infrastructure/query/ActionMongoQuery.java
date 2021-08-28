package com.culnou.mumu.myway.infrastructure.query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.myway.domain.model.AbstractActionQuery;
import com.culnou.mumu.myway.domain.model.Action;
import com.culnou.mumu.myway.domain.model.ActionId;
import com.culnou.mumu.myway.domain.model.ProjectId;


@Service("actionMongoQuery")
@Transactional
public class ActionMongoQuery extends AbstractActionQuery {
	
	@Autowired
	private ActionMongoDataQuery actionQuery;

	@Override
	public Action findById(ActionId actionId) throws Exception {
		// TODO Auto-generated method stub
		Optional<ActionQueryDocument> readDoc = actionQuery.findById(actionId.id());
		if (readDoc.isPresent()){
			ActionQueryDocument doc = readDoc.get();
			return this.convertActionQueryDocumentToAction(doc);
		}else {
			return null;
		}
	}

	@Override
	public List<Action> findActionsOfProject(ProjectId projectId) throws Exception {
		// TODO Auto-generated method stub
		List<ActionQueryDocument> docs = actionQuery.findActionsByProjectId(projectId);
		//検証用
		System.out.println("******* project " + docs.get(0).getProjectId().id());
		return convertActionQueryDocumentsToActions(docs);
	}
	
	private Action convertActionQueryDocumentToAction(ActionQueryDocument doc) {
		Action action = this.convertFrom(doc);
		return action;
		
	}
	
	private List<Action> convertActionQueryDocumentsToActions(List<ActionQueryDocument> docs) {
		List<Action> actions = new ArrayList<>();
		for(ActionQueryDocument doc : docs) {
			Action action = convertActionQueryDocumentToAction(doc);
			actions.add(action);
		}
		return actions;
	}

}
