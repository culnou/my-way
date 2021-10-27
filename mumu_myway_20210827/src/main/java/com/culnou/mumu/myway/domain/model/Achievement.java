package com.culnou.mumu.myway.domain.model;

import java.util.Date;

public class Achievement {
	private String id;
	private Date execTime;
	private String result;
	private String awareness;
	
	public Achievement(String id, Date execTime, String result) {
		this.setId(id);
		this.setExecTime(execTime);
		this.setResult(result);
	}
	
	public void setId(String id) {
		if(id == null) {
			throw new IllegalArgumentException("The id may not be set to null.");
		}
		this.id = id;
	}
	
	public void setExecTime(Date execTime) {
		if(execTime == null) {
			throw new IllegalArgumentException("The execTime may not be set to null.");
		}
		this.execTime = execTime;
	}
	
	public void setResult(String result) {
		if(result == null) {
			throw new IllegalArgumentException("The result may not be set to null.");
		}
		this.result = result;
	}
	
	public void setAwareness(String awareness) {
		if(awareness == null) {
			throw new IllegalArgumentException("The awareness may not be set to null.");
		}
		this.awareness = awareness;
	}
	
	public String id() {
		return this.id;
	}
	
	public Date execTime() {
		return this.execTime;
	}
	
	public String result() {
		return this.result;
	}
	
	
	public String awareness() {
		return this.awareness;
	}
	
	

}
