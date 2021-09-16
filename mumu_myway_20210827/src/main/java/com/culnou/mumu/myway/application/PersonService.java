package com.culnou.mumu.myway.application;

import java.util.List;

import com.culnou.mumu.myway.controller.PersonDto;
import com.culnou.mumu.myway.controller.ProjectDto;
import com.culnou.mumu.myway.controller.UserDto;
import com.culnou.mumu.myway.controller.VisionDto;
import com.culnou.mumu.myway.domain.model.ProjectType;


public interface PersonService {
	
	public void assignPerson(UserDto user) throws Exception;
	
	public void updatePerson(PersonDto person) throws Exception;
	
	public PersonDto findPersonById(String id) throws Exception;
	
	public void deletePerson(String id) throws Exception;
	
	public List<VisionDto> findVisionsByPersonId(String personId) throws Exception;
	
	public VisionDto findVisionById(String id) throws Exception;
	
    public void updateVision(VisionDto visionDto) throws Exception;
	
	public void deleteVision(String id) throws Exception;
	
    public void updateProject(ProjectDto projectDto) throws Exception;
	
	public void deleteProject(String id) throws Exception;
	
	public ProjectDto findProjectById(String id) throws Exception;
	
	public List<ProjectDto> findProjectsByVisionIdAndProjectType(String visionId, ProjectType projectType) throws Exception;
	
	//ファクトリーメソッド
	public PersonDto createPerson(UserDto user) throws Exception;
	
	public VisionDto addVision(VisionDto visionDto) throws Exception;
	
	public ProjectDto addProject(ProjectDto projectDto) throws Exception;
	
	
	

}
