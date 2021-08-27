package com.culnou.mumu.myway.infrastructure.query;

import org.springframework.data.mongodb.repository.MongoRepository;




public interface PersonMongoDataQuery extends MongoRepository<PersonQueryDocument, String> {
	
	
}
