package com.culnou.mumu.myway.domain.model;

public class Project {
	
	private PersonId personId;
	private VisionId visionId;
	private ProjectId projectId;
	private String name;
	private String description;
	private ProjectType projectType;
	private Goal goal;
	private int expendedTime;
	//ファクトリーメソッドのみ生成することができるようプロテクトする。
	protected Project(PersonId personId, VisionId visionId, ProjectId experimentId, String name, String description, ProjectType projectType) {
		this.setPersonId(personId);
		this.setVisionId(visionId);
		this.setProjectId(experimentId);
		this.setName(name);
		this.setDescription(description);
		this.setProjectType(projectType);
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
	
	protected void setVisionId(VisionId visionId) {
		if(visionId == null) {
			throw new IllegalArgumentException("The visionId may not be set to null.");
		}
		this.visionId = visionId;
	}
	
	public VisionId visionId() {
		return this.visionId;
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
	
	protected void setProjectType(ProjectType projectType) {
		if(projectType == null) {
			throw new IllegalArgumentException("The projectType may not be set to null.");
		}
		this.projectType = projectType;
	}
	
	public ProjectType projectType() {
		return this.projectType;
	}
	
	public void defineGoal(Goal goal) {
		if(goal == null) {
			throw new IllegalArgumentException("The measurement may not be set to null.");
		}
		this.goal = goal;
	}
	
	public Goal goal() {
		return this.goal;
	}
	
	public void setExpendedTime(int expendedTime) {
		if(expendedTime < 0) {
			throw new IllegalArgumentException("The expendedTime may be negative.");
		}
		this.expendedTime = expendedTime;
	}
	
	public int expendedTime() {
		return this.expendedTime;
	}
	
	//ファクトリーメソッド
	public Action defineAction(ActionId actionId, String name, String description) throws Exception{
		if(actionId == null) {
			throw new IllegalArgumentException("The actionId may not be set to null.");
		}
		if(name == null) {
			throw new IllegalArgumentException("The name may not be set to null.");
		}
		if(description == null) {
			throw new IllegalArgumentException("The description may not be set to null.");
		}
		return new Action(this.personId, this.projectId, actionId, name, description);
	}

}
