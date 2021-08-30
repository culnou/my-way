package com.culnou.mumu.myway.infrastructure.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.culnou.mumu.myway.domain.model.ActionId;


public interface WorkMongoDataRepository extends MongoRepository<WorkDocument, String> {
	
	@Query("{ 'projectId' : ?0}")
	List<WorkDocument> findWorksByActionId(ActionId actionId);

}
