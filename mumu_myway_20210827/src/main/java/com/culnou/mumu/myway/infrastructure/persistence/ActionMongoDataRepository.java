package com.culnou.mumu.myway.infrastructure.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.culnou.mumu.myway.domain.model.PersonId;
import com.culnou.mumu.myway.domain.model.ProjectId;
import com.culnou.mumu.myway.domain.model.ProjectType;
import com.culnou.mumu.myway.domain.model.VisionId;


public interface ActionMongoDataRepository extends MongoRepository<ActionDocument, String> {
	
	@Query("{ 'projectId' : ?0}")
	List<ActionDocument> findActionsByProjectId(ProjectId projectId);
	
	//アクションのルーティン化のため追加。2021/10/26
	@Query("{'$and':[ {'personId': ?0}, {'isRoutine': ?0} ] }")
	public List<ActionDocument> findActionsByPersonIdAndRoutine(PersonId personId, boolean isRoutine);

}
