package com.culnou.mumu.myway.domain.model;


import java.util.List;

public interface ProjectQuery {
	
	public Project findById(ProjectId experimentId) throws Exception;
	
	public List<Project> findProjectsOfProjectType(ProjectType projectType) throws Exception;
	
	public List<Project> findProjectsOfVision(VisionId visionId) throws Exception;

}
