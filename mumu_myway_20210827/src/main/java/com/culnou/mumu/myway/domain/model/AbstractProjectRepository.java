package com.culnou.mumu.myway.domain.model;



import java.util.ArrayList;
import java.util.List;

import com.culnou.mumu.myway.infrastructure.persistence.AchievementDocument;
import com.culnou.mumu.myway.infrastructure.persistence.ProjectDocument;


public abstract class AbstractProjectRepository implements ProjectRepository {

		
	protected Project convertFrom(ProjectDocument doc) {
		Project project = new Project(doc.getPersonId(), doc.getVisionId(), doc.getProjectId(), doc.getName(), doc.getDescription(), doc.getProjectType());
		project.setExpendedTime(doc.getExpendedTime());
		if(doc.getDeadline() != null) {
			project.setDeadline(doc.getDeadline());
		}
		project.setTerm(doc.getTerm());
		if(doc.getIndicator() != null) {
			project.setIndicator(doc.getIndicator());
		}
		if(doc.getCriteria() != null) {
			project.setCriteria(doc.getCriteria());
		}
		if(doc.getAchievements() != null) {
			List<Achievement> acvs = new ArrayList<>();
			for(AchievementDocument a : doc.getAchievements()) {
				Achievement ac = new Achievement(a.getId(), a.getExecTime(), a.getResult());
				if(a.getAwareness() != null) {
					ac.setAwareness(a.getAwareness());
				}
				acvs.add(ac);
			}
			project.setAchievements(acvs);
		}
		return project;
	}

}
