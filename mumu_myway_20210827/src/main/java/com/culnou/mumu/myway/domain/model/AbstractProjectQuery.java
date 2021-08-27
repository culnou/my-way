package com.culnou.mumu.myway.domain.model;

import java.util.List;

import com.culnou.mumu.myway.infrastructure.query.ProjectQueryDocument;

public abstract class AbstractProjectQuery implements ProjectQuery {

	@Override
	public Project findById(ProjectId experimentId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Project> findProjectsOfProjectType(ProjectType projectType) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Project> findProjectsOfVision(VisionId visionId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected Project convertFrom(ProjectQueryDocument doc) {
		return new Project(doc.getPersonId(), doc.getVisionId(), doc.getProjectId(), doc.getName(), doc.getDescription(), doc.getProjectType());
	}

}
