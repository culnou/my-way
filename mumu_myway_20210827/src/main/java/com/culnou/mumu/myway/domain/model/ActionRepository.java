package com.culnou.mumu.myway.domain.model;

import java.util.List;

public interface ActionRepository {
	
	//識別子オブジェクトはリポジトリが生成する。
	public ActionId nextIdentity() throws Exception;
	
	public void save(Action action) throws Exception;
	
	public void saveAll(List<Action> actions) throws Exception;
	
	public void remove(Action action) throws Exception;
	
	public void removeAll(List<Action> actions) throws Exception;
	
	public Action actionOfId(ActionId actionId) throws Exception;
		
	public List<Action> actionsOfProject(ProjectId projectId) throws Exception;
	
	//アクションのルーティン化のため追加。2021/10/26
	public List<Action> actionsOfRoutine(PersonId personId) throws Exception;

}
