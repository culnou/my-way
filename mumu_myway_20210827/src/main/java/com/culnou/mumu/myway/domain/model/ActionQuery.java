package com.culnou.mumu.myway.domain.model;

import java.util.List;

public interface ActionQuery {
	
    public Action findById(ActionId actionId) throws Exception;
	
	
	public List<Action> findActionsOfProject(ProjectId projectId) throws Exception;

}
