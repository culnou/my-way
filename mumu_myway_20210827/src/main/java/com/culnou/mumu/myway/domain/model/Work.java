package com.culnou.mumu.myway.domain.model;

public class Work {
	
	private PersonId personId;
	private ActionId actionId;
	private WorkId workId;
	private String name;
	private String description;
	private WorkStatus workStatus;
	private int expendedTime;
	
	protected Work(PersonId personId, ActionId actionId, WorkId workId, String name, String description) {
		this.setPersonId(personId);
		this.setWorkId(workId);
		this.setActionId(actionId);
		this.setName(name);
		this.setDescription(description);
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
	
	protected void setActionId(ActionId actionId) {
		if(actionId == null) {
			throw new IllegalArgumentException("The actionId may not be set to null.");
		}
		this.actionId = actionId;
	}
	
	public ActionId actionId() {
		return this.actionId;
	}
	
	protected void setWorkId(WorkId workId) {
		if(this.workId != null) {
			throw new IllegalStateException();
		}
		if(workId == null) {
			throw new IllegalArgumentException("The workId may not be set to null.");
		}
		this.workId = workId;
	}
	
	public WorkId workId() {
		return this.workId;
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
	
	public void changeStatus(WorkStatus workStatus) {
		if(workStatus == null) {
			throw new IllegalArgumentException("The workStatus may not be set to null.");
		}
		this.workStatus = workStatus;
	}
	
	public WorkStatus status() {
		return this.workStatus;
	}
	
	public void addExpendedTime(int expendedTime) {
		if((this.expendedTime +  expendedTime) < 0) {
			throw new IllegalArgumentException("Total expendedTime may be negative.");
		}
		this.expendedTime += expendedTime;
	}
	
	//クエリオブジェクトからエンティティに変換するために設ける。
	protected void setExpendedTime(int expendedTime) {
		if(expendedTime < 0) {
			throw new IllegalArgumentException("The expendedTime may be negative.");
		}
		this.expendedTime = expendedTime;
	}
		
	
	public int expendedTime() {
		return this.expendedTime;
	}

}
