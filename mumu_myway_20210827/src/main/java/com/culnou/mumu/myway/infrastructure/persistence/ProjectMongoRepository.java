package com.culnou.mumu.myway.infrastructure.persistence;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.myway.domain.model.AbstractProjectRepository;
import com.culnou.mumu.myway.domain.model.Project;
import com.culnou.mumu.myway.domain.model.ProjectId;

import com.culnou.mumu.myway.domain.model.ProjectType;
import com.culnou.mumu.myway.domain.model.VisionId;

@Service("projectMongoRepository")
@Transactional
public class ProjectMongoRepository extends AbstractProjectRepository {

	@Autowired
	private ProjectMongoDataRepository projectRepository;

	@Override
	public ProjectId nextIdentity() throws Exception {
		// TODO Auto-generated method stub
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        
		return new ProjectId(str);
	}

	@Override
	public void save(Project project) throws Exception {
		// TODO Auto-generated method stub
		ProjectDocument doc = this.convertProjectToProjectDocument(project);
		projectRepository.save(doc);
	}
	
	@Override
	public void saveAll(List<Project> projects) throws Exception {
		// TODO Auto-generated method stub
		for(Project project : projects) {
			this.save(project);
		}

	}

	@Override
	public void remove(Project project) throws Exception {
		// TODO Auto-generated method stub
		ProjectDocument doc = this.convertProjectToProjectDocument(project);
		projectRepository.delete(doc);
	}

	@Override
	public void removeAll(List<Project> projects) throws Exception {
		// TODO Auto-generated method stub
		for(Project project : projects) {
			this.remove(project);
		}
		
	}
	
	@Override
	public Project projectOfId(ProjectId projectId) throws Exception {
		// TODO Auto-generated method stub
		Optional<ProjectDocument> readDoc = projectRepository.findById(projectId.id());
		if (readDoc.isPresent()){
			ProjectDocument doc = readDoc.get();
			return this.convertProjectDocumentToProject(doc);
		}else {
			return null;
		}
	}

	@Override
	public List<Project> projectsOfVision(VisionId visionId) throws Exception {
		// TODO Auto-generated method stub
		List<ProjectDocument> docs = projectRepository.findProjectsByVisionId(visionId);
		return convertProjectDocumentsToProjects(docs);
	}

	@Override
	public List<Project> projectsOfProjectType(ProjectType projectType) throws Exception {
		// TODO Auto-generated method stub
		List<ProjectDocument> docs = projectRepository.findProjectsByProjectType(projectType);
		return convertProjectDocumentsToProjects(docs);
	}
	
	@Override
	public List<Project> projectsOfVisionAndProjectType(VisionId visionId, ProjectType projectType) throws Exception {
		// TODO Auto-generated method stub
		List<ProjectDocument> docs = projectRepository.findProjectsByVisionIdAndProjectType(visionId, projectType);
		return convertProjectDocumentsToProjects(docs);
	}
	private ProjectDocument convertProjectToProjectDocument(Project project) {
		ProjectDocument doc = new ProjectDocument();
		doc.setId(project.projectId().id());
		doc.setPersonId(project.personId());
		doc.setVisionId(project.visionId());
		doc.setProjectId(project.projectId());
		doc.setName(project.name());
		doc.setDescription(project.description());
		doc.setProjectType(project.projectType());
		doc.setDeadline(project.deadline());
		doc.setTerm(project.term());
		doc.setIndicator(project.indicator());
		doc.setCriteria(project.criteria());
		doc.setExpendedTime(project.expendedTime());
		return doc;
	}
	
	private Project convertProjectDocumentToProject(ProjectDocument doc) {
		Project experiment = this.convertFrom(doc);
		return experiment;
		
	}
	
	private List<Project> convertProjectDocumentsToProjects(List<ProjectDocument> docs) {
		List<Project> projects = new ArrayList<>();
		for(ProjectDocument doc : docs) {
			Project project = convertProjectDocumentToProject(doc);
			projects.add(project);
		}
		return projects;
	}

	
	
	
}
