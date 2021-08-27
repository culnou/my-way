package com.culnou.mumu.myway.infrastructure.query;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.culnou.mumu.myway.domain.model.PersonId;
import com.culnou.mumu.myway.domain.model.VisionType;


public interface VisionMongoDataQuery extends MongoRepository<VisionQueryDocument, String> {

	@Query("{ 'visionType' : ?0 }")
	List<VisionQueryDocument> findVisionsByVisionType(VisionType visionType);
	
	
	@Query("{ 'personId' : ?0}")
	List<VisionQueryDocument> findVisionsByPersonId(PersonId personId);
	
	
	
}
