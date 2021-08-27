package com.culnou.mumu.myway.domain.model;

import java.io.Serializable;



public class ProjectId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	public ProjectId(String id) {
	    setId(id);	
	}
	
	protected void setId(String id) {
		//セッターで一貫性制約を保持する。
		if(id == null) {
			throw new IllegalArgumentException("The id may not be set to null.");
		}
		this.id = id;
	}
	
	public String id() {
		return this.id;
	}
	
	@Override
	public ProjectId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new ProjectId(this.id());
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			ProjectId projectId = (ProjectId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(projectId.id().equals(this.id())){
				equality = true;
			}
		}
		return equality;
	}	

}
