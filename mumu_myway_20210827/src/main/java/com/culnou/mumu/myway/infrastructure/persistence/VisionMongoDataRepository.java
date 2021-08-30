package com.culnou.mumu.myway.infrastructure.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.culnou.mumu.myway.domain.model.PersonId;
import com.culnou.mumu.myway.domain.model.VisionType;


public interface VisionMongoDataRepository extends MongoRepository<VisionDocument, String> {
	
	@Query("{ 'visionType' : ?0 }")
	List<VisionDocument> findVisionsByVisionType(VisionType visionType);
	
	
	@Query("{ 'personId' : ?0}")
	List<VisionDocument> findVisionsByPersonId(PersonId personId);

}
