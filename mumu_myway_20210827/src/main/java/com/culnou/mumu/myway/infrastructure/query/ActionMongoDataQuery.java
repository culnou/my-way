package com.culnou.mumu.myway.infrastructure.query;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.culnou.mumu.myway.domain.model.ProjectId;


public interface ActionMongoDataQuery extends MongoRepository<ActionQueryDocument, String> {

	@Query("{ 'projectId' : ?0}")
	List<ActionQueryDocument> findActionsByProjectId(ProjectId projectId);
	
}
