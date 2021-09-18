package com.culnou.mumu.myway.domain.model;

import java.util.Date;

public class Work {
	
	private PersonId personId;
	private ActionId actionId;
	private WorkId workId;
	private String name;
	private String description;
	private Date startTime;
	private Date endTime;
	private WorkStatus workStatus;
	private int expendedTime;
	
	protected Work(PersonId personId, ActionId actionId, WorkId workId, String name, String description, Date startTime, Date endTime) {
		this.setPersonId(personId);
		this.setWorkId(workId);
		this.setActionId(actionId);
		this.setName(name);
		this.setDescription(description);
		this.setStartTime(startTime);
		this.setEndTime(endTime);
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
	
	public void setStartTime(Date startTime) {
		if(startTime == null) {
			throw new IllegalArgumentException("The startTime may not be set to null.");
		}
		this.startTime = startTime;
	}
	
	public Date startTime() {
		return this.startTime;
	}
	
	public void setEndTime(Date endTime) {
		if(endTime == null) {
			throw new IllegalArgumentException("The endTime may not be set to null.");
		}
		this.endTime = endTime;
	}
	
	public Date endTime() {
		return this.endTime;
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
	//Workの場合、消費時間を加算することはないので、setExpendedTimeのみ必要。
	//よって、本メソッドは削除する。2021/09/18
	/*
	public void addExpendedTime(int expendedTime) {
		if((this.expendedTime +  expendedTime) < 0) {
			throw new IllegalArgumentException("Total expendedTime may be negative.");
		}
		this.expendedTime += expendedTime;
	}
	*/
	//クエリオブジェクトからエンティティに変換するために設ける。
	//addExpendedTimeの代わりに使用するのでprotectedからpublicに変更する。2021/09/18
	public void setExpendedTime(int expendedTime) {
		if(expendedTime < 0) {
			throw new IllegalArgumentException("The expendedTime may be negative.");
		}
		this.expendedTime = expendedTime;
	}
		
	
	public int expendedTime() {
		return this.expendedTime;
	}

}
