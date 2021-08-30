package com.culnou.mumu.myway.domain.model;



import com.culnou.mumu.myway.infrastructure.persistence.ProjectDocument;


public abstract class AbstractProjectRepository implements ProjectRepository {

		
	protected Project convertFrom(ProjectDocument doc) {
		Project project = new Project(doc.getPersonId(), doc.getVisionId(), doc.getProjectId(), doc.getName(), doc.getDescription(), doc.getProjectType());
		project.setExpendedTime(doc.getExpendedTime());
		if(doc.getGoal() != null) {
			project.defineGoal(doc.getGoal());
		}
		return project;
	}

}
