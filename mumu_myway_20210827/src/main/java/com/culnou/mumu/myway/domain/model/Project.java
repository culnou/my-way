package com.culnou.mumu.myway.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Project {
	
	private PersonId personId;
	private VisionId visionId;
	private ProjectId projectId;
	private String name;
	private String description;
	private ProjectType projectType;
	private Date deadline;
	private int term;
	private String indicator;
	private String criteria;
	private int expendedTime;
	private List<Achievement> achivements = new ArrayList<>();
	
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
		if(this.projectId != null) {
			throw new IllegalStateException();
		}
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
	
	public void setDeadline(Date deadline) {
		if(deadline == null) {
			throw new IllegalArgumentException("The deadline may not be set to null.");
		}
		this.deadline = deadline;
	}
	
	public Date deadline() {
		return this.deadline;
	}
	
	public void setTerm(int term) {
		if(term < 0) {
			throw new IllegalArgumentException("The term may be negative.");
		}
		this.term = term;
	}
	
	public int term() {
		return this.term;
	}
	
	public void setIndicator(String indicator) {
		if(indicator == null) {
			throw new IllegalArgumentException("The indicator may not be set to null.");
		}
		this.indicator = indicator;
	}
	
	public String indicator() {
		return this.indicator;
	}
	
	public void setCriteria(String criteria) {
		if(criteria == null) {
			throw new IllegalArgumentException("The criteria may not be set to null.");
		}
		this.criteria =criteria;
	}
	
	public String criteria() {
		return this.criteria;
	}
	
	//負の数字はNG。
	public void addExpendedTime(int expendedTime) {
		if((this.expendedTime +  expendedTime) < 0) {
			throw new IllegalArgumentException("Total expendedTime may be negative.");
		}
		this.expendedTime += expendedTime;
	}
	
	//クエリオブジェクトからエンティティに変換するために設ける。
	//PersonalServiceのupdateで使用できるようにprotectedからpublicに変更する。2021/10/24
	public void setExpendedTime(int expendedTime) {
		if(expendedTime < 0) {
			throw new IllegalArgumentException("The expendedTime may be negative.");
		}
		this.expendedTime = expendedTime;
	}
	
	public int expendedTime() {
		return this.expendedTime;
	}
	
	public void setAchievement(Achievement achievement) {
		if(achievement == null) {
			throw new IllegalArgumentException("The achievement may not be set to null.");
		}
		this.achivements.add(achievement);
	}
	
	public void setAchievements(List<Achievement> achievements) {
		if(achievements == null) {
			throw new IllegalArgumentException("The achievements may not be set to null.");
		}
		this.achivements = achievements;
	}
	
	public List<Achievement> achievements(){
		return this.achivements;
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
