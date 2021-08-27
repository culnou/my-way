package com.culnou.mumu.myway.domain.model;

import java.io.Serializable;


public class ActionId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	public ActionId(String id) {
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
	public ActionId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new ActionId(this.id());
	}	
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			ActionId actionId = (ActionId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(actionId.id().equals(this.id())){
				equality = true;
			}
		}
		return equality;
	}

}
