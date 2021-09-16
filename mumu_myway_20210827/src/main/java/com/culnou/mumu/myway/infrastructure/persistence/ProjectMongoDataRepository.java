package com.culnou.mumu.myway.infrastructure.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;


import com.culnou.mumu.myway.domain.model.ProjectType;
import com.culnou.mumu.myway.domain.model.VisionId;


public interface ProjectMongoDataRepository extends MongoRepository<ProjectDocument, String> {
	
	@Query("{ 'projectType' : ?0 }")
	List<ProjectDocument> findProjectsByProjectType(ProjectType projectType);
	
	@Query("{ 'visionId' : ?0}")
	List<ProjectDocument> findProjectsByVisionId(VisionId visionId);
	
	@Query("{'$and':[ {'visionId': ?0}, {'projectType': ?0} ] }")
	public List<ProjectDocument> findProjectsByVisionIdAndProjectType(VisionId visionId, ProjectType projectType);

}
