package com.culnou.mumu.myway.domain.model;

public class Person {
	
	private PersonId personId;
	private FullName fullName;
	private Email email;
	
	
	//ファクトリーメソッドのみ生成することができるようプロテクトする。
	protected Person(PersonId personId, FullName fullName, Email email) {
		//自己カプセル化
		this.setPersonId(personId);
		this.setFullName(fullName);
		this.setEmail(email);
	}
	
	protected void setPersonId(PersonId personId) {
		//識別子の不変性を保持するため、識別子が既に設定されているときは例外を発生させて設定させないようにする。
		//DDD p180参考。
		if(this.personId != null) {
			throw new IllegalStateException();
		}
		if(personId == null) {
			throw new IllegalArgumentException("The personId may not be set to null.");
		}
		this.personId = personId;
	}
	
	public PersonId personId() {
		return this.personId;
	}
	
	protected void setFullName(FullName fullName) {
		if(fullName == null) {
			throw new IllegalArgumentException("The fullName may not be set to null.");
		}
		this.fullName = fullName;
	}
	
	public FullName fullName() {
		return this.fullName;
	}
	
	protected void setEmail(Email email) {
		if(email == null) {
			throw new IllegalArgumentException("The email may not be set to null.");
		}
		this.email = email;
	}
	
	public Email email() {
		return this.email;
	}
	
	
	
	//ファクトリーメソッド
	public Vision createVision(VisionId visionId, VisionType visionType, String content) throws Exception{
		if(visionId == null) {
			throw new IllegalArgumentException("The visionId may not be set to null.");
		}
		if(visionType == null) {
			throw new IllegalArgumentException("The visionType may not be set to null.");
		}
		if(content == null) {
			throw new IllegalArgumentException("The content may not be set to null.");
		}
		return new Vision(this.personId, visionId, visionType, content);
	}

}
