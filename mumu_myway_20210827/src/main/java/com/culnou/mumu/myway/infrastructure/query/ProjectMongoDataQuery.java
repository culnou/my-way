package com.culnou.mumu.myway.infrastructure.query;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.culnou.mumu.myway.domain.model.ProjectType;
import com.culnou.mumu.myway.domain.model.VisionId;

public interface ProjectMongoDataQuery extends MongoRepository<ProjectQueryDocument, String> {
	
	@Query("{ 'projectType' : ?0 }")
	List<ProjectQueryDocument> findProjectsByProjectType(ProjectType projectType);
	
	@Query("{ 'visionId' : ?0}")
	List<ProjectQueryDocument> findProjectsByVisionId(VisionId visionId);

}
