package com.culnou.mumu.myway.infrastructure.query;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.culnou.mumu.myway.domain.model.ActionId;

public interface WorkMongoDataQuery extends MongoRepository<WorkQueryDocument, String> {
	
	@Query("{ 'projectId' : ?0}")
	List<WorkQueryDocument> findWorksByActionId(ActionId actionId);

}
