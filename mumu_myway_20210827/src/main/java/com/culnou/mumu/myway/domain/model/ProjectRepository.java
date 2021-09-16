package com.culnou.mumu.myway.domain.model;

import java.util.List;

public interface ProjectRepository {
	
	//識別子オブジェクトはリポジトリが生成する。
	public ProjectId nextIdentity() throws Exception;
		
		
	//コマンド。
	public void save(Project project) throws Exception;
			
	public void saveAll(List<Project> projects) throws Exception;
	
	public void remove(Project project) throws Exception;
			
	public void removeAll(List<Project> projects) throws Exception;
	
	//クエリ。
    public Project projectOfId(ProjectId projectId) throws Exception;
    
    public List<Project> projectsOfVision(VisionId visionId) throws Exception;
	
	public List<Project> projectsOfProjectType(ProjectType projectType) throws Exception;
	
	public List<Project> projectsOfVisionAndProjectType(VisionId visionId, ProjectType projectType) throws Exception;
	

}
