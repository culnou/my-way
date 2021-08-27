package com.culnou.mumu.myway.infrastructure.query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.myway.domain.model.AbstractProjectQuery;
import com.culnou.mumu.myway.domain.model.Project;
import com.culnou.mumu.myway.domain.model.ProjectId;

import com.culnou.mumu.myway.domain.model.ProjectType;
import com.culnou.mumu.myway.domain.model.VisionId;
@Service("projectMongoQuery")
@Transactional
public class ProjectMongoQuery extends AbstractProjectQuery{
	
	@Autowired
	private ProjectMongoDataQuery projectQuery;

	@Override
	public List<Project> findProjectsOfProjectType(ProjectType projectType) throws Exception {
		// TODO Auto-generated method stub
		List<ProjectQueryDocument> docs = projectQuery.findProjectsByProjectType(projectType);
		//検証用
		System.out.println("******* projectType " + docs.get(0).getProjectType());
		return convertProjectQueryDocumentsToProjects(docs);
	}
	
	@Override
	public Project findById(ProjectId projectId) throws Exception {
		// TODO Auto-generated method stub
		Optional<ProjectQueryDocument> readDoc = projectQuery.findById(projectId.id());
		if (readDoc.isPresent()){
			ProjectQueryDocument doc = readDoc.get();
			return this.convertProjectQueryDocumentToProject(doc);
		}else {
			return null;
		}
	}
	
	@Override
	public List<Project> findProjectsOfVision(VisionId visionId) throws Exception {
		// TODO Auto-generated method stub
		List<ProjectQueryDocument> docs = projectQuery.findProjectsByVisionId(visionId);
		//検証用
		System.out.println("******* vision " + docs.get(0).getVisionId().id());
		return convertProjectQueryDocumentsToProjects(docs);
	}
	
	private Project convertProjectQueryDocumentToProject(ProjectQueryDocument doc) {
		Project experiment = this.convertFrom(doc);
		if(doc.getGoal() != null) {
			experiment.defineGoal(doc.getGoal());
		}
		return experiment;
		
	}
	
	private List<Project> convertProjectQueryDocumentsToProjects(List<ProjectQueryDocument> docs) {
		List<Project> experiments = new ArrayList<>();
		for(ProjectQueryDocument doc : docs) {
			Project experiment = convertProjectQueryDocumentToProject(doc);
			experiments.add(experiment);
		}
		return experiments;
	}

	



	

}
