package com.culnou.mumu.myway.domain.model;

public class Vision {
	
	private PersonId personId;
	private VisionId visionId;
	private VisionType visionType;
	private String content;
	
	//ファクトリーメソッドのみ生成することができるようプロテクトする。
	protected Vision(PersonId personId, VisionId visionId, VisionType visionType, String content) {
		this.setPersonId(personId);
		this.setVisionId(visionId);
		this.setVisionType(visionType);
		this.setContent(content);
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
	//識別子の不変性を保持するため、識別子のセッターは、protectedにしてクライアントから設定できないようにする。
	protected void setVisionId(VisionId visionId) {
		//識別子の不変性を保持するため、識別子が既に設定されているときは例外を発生させて設定させないようにする。
		//DDD p180参考。
		if(this.visionId != null) {
			throw new IllegalStateException();
		}
		if(visionId == null) {
			throw new IllegalArgumentException("The visionId may not be set to null.");
		}
		this.visionId = visionId;
	}
	
	public VisionId visionId() {
		return this.visionId;
	}
	
	protected void setVisionType(VisionType visionType) {
		if(visionType == null) {
			throw new IllegalArgumentException("The visionType may not be set to null.");
		}
		this.visionType = visionType;
	}
	
	public VisionType visionType() {
		return this.visionType;
	}
	
	public void setContent(String content) {
		if(content == null) {
			throw new IllegalArgumentException("The content may not be set to null.");
		}
		this.content = content;
	}
	
	public String content() {
		return this.content;
	}
	
	//ファクトリーメソッド
	public Project launchProject(ProjectId experimentId, String name, String description, ProjectType projectType) {
		if(experimentId == null) {
			throw new IllegalArgumentException("The experimentId may not be set to null.");
		}
		if(name == null) {
			throw new IllegalArgumentException("The name may not be set to null.");
		}
		if(description == null) {
			throw new IllegalArgumentException("The description may not be set to null.");
		}
		if(projectType == null) {
			throw new IllegalArgumentException("The projectType may not be set to null.");
		}
		return new Project(this.personId(), this.visionId(), experimentId, name, description, projectType);
	}
	
}
