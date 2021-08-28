package com.culnou.mumu.myway.domain.model;


import com.culnou.mumu.myway.infrastructure.query.ActionQueryDocument;


public abstract class AbstractActionQuery implements ActionQuery {

	
	
	protected Action convertFrom(ActionQueryDocument doc) {
		Action action = new Action(doc.getPersonId(), doc.getProjectId(), doc.getActionId(), doc.getName(), doc.getDescription());
		action.setExpendedTime(doc.getExpendedTime());
		return action;
	}

}
