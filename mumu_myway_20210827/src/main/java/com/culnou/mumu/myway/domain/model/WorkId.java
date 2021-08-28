package com.culnou.mumu.myway.domain.model;

import java.io.Serializable;

public class WorkId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	public WorkId(String id) {
		this.setId(id);
	}
	
	protected void setId(String id) {
		if(id == null) {
			throw new IllegalArgumentException("The id may not be set to null.");
		}
		this.id = id;
	}
	
	public String id() {
		return this.id;
	}
	@Override
	public WorkId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new WorkId(this.id());
	}	
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			WorkId workId = (WorkId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(workId.id().equals(this.id())){
				equality = true;
			}
		}
		return equality;
	}

}
