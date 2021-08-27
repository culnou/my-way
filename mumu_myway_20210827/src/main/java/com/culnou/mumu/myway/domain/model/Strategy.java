package com.culnou.mumu.myway.domain.model;

import java.io.Serializable;

public class Strategy implements Serializable, Cloneable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String name;
	private String content;
	
	public Strategy(String name, String content) {
		this.setName(name);
		this.setContent(content);
	}
	
	protected void setName(String name) {
		if(name == null) {
			throw new IllegalArgumentException("The name may not be set to null.");
		}
		this.name = name;
	}
	
	public String name() {
		return this.name;
	}
	
	protected void setContent(String content) {
		if(content == null) {
			throw new IllegalArgumentException("The content may not be set to null.");
		}
		this.content = content;
	}
	
	public String content() {
		return this.content;
	}
	
	@Override
	public Strategy clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new Strategy(this.name(), this.content());
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			Strategy strategy = (Strategy)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(strategy.name().equals(this.name()) && strategy.content().equals(this.content())){
				equality = true;
			}
		}
		return equality;
	}	

}
