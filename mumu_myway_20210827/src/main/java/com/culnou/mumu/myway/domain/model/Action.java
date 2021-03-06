package com.culnou.mumu.myway.domain.model;

import java.util.Date;

public class Action {
	
	private PersonId personId;
	private ProjectId projectId;
	private ActionId actionId;
	private String name;
	private String description;
	private int expendedTime;
	//アクションのルーティン化のため追加。2021/10/26
	private boolean routine;
	
	
	protected Action(PersonId personId, ProjectId projectId, ActionId actionId, String name, String description) {
		this.setPersonId(personId);
		this.setProjectId(projectId);
		this.setActionId(actionId);
		this.setName(name);
		this.setDescription(description);
		//ルーティンはデフォルトでFalseにする。2021/10/26
		this.setRoutine(false);
	}
	
	protected void setPersonId(PersonId personId) {
		if(personId == null) {
			throw new IllegalArgumentException("The personId may not be set to null.");
		}
		this.personId = personId;
	}
	
	public PersonId personId() {
		return this.personId;
	}
	
	protected void setProjectId(ProjectId projectId) {
		if(projectId == null) {
			throw new IllegalArgumentException("The projectId may not be set to null.");
		}
		this.projectId = projectId;
	}
	
	public ProjectId projectId() {
		return this.projectId;
	}
	
	protected void setActionId(ActionId actionId) {
		if(this.actionId != null) {
			throw new IllegalStateException();
		}
		if(actionId == null) {
			throw new IllegalArgumentException("The actionId may not be set to null.");
		}
		this.actionId = actionId;
	}
	
	public ActionId actionId() {
		return this.actionId;
	}
	
	public void setName(String name) {
		if(name == null) {
			throw new IllegalArgumentException("The name may not be set to null.");
		}
		this.name = name;
	}
	
	public String name() {
		return this.name;
	}
	
	public void setDescription(String description) {
		if(description == null) {
			throw new IllegalArgumentException("The description may not be set to null.");
		}
		this.description = description;
	}
	
	public String description() {
		return this.description;
	}
	
	public void addExpendedTime(int expendedTime) {
		if((this.expendedTime +  expendedTime) < 0) {
			throw new IllegalArgumentException("Total expendedTime may be negative.");
		}
		this.expendedTime += expendedTime;
	}
	
	//クエリオブジェクトからエンティティに変換するために設ける。
	//PersonalServiceのupdateで使用できるようにprotectedからpublicに変更する。2021/10/24
	public void setExpendedTime(int expendedTime) {
		if(expendedTime < 0) {
			throw new IllegalArgumentException("The expendedTime may be negative.");
		}
		this.expendedTime = expendedTime;
	}
		
	
	public int expendedTime() {
		return this.expendedTime;
	}
	
	public void setRoutine(boolean routine) {
		this.routine = routine;
	}
	
	public boolean isRoutine() {
		return this.routine;
	}
	//ファクトリーメソッド
	public Work defineWork(WorkId workId, String name, String description, Date startTime, Date endTime) throws Exception{
		if(workId == null) {
			throw new IllegalArgumentException("The workId may not be set to null.");
		}
		if(name == null) {
			throw new IllegalArgumentException("The name may not be set to null.");
		}
		if(description == null) {
			throw new IllegalArgumentException("The description may not be set to null.");
		}
		if(startTime == null) {
			throw new IllegalArgumentException("The startTime may not be set to null.");
		}
		if(endTime == null) {
			throw new IllegalArgumentException("The endTime may not be set to null.");
		}
		return new Work(this.personId, this.actionId, workId, name, description, startTime, endTime);
	}

}
