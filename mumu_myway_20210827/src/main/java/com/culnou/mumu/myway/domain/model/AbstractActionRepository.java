package com.culnou.mumu.myway.domain.model;



import com.culnou.mumu.myway.infrastructure.persistence.ActionDocument;

public abstract class AbstractActionRepository implements ActionRepository {

		
	protected Action convertFrom(ActionDocument doc) {
		Action action = new Action(doc.getPersonId(), doc.getProjectId(), doc.getActionId(), doc.getName(), doc.getDescription());
		action.setExpendedTime(doc.getExpendedTime());
		return action;
	}

}
