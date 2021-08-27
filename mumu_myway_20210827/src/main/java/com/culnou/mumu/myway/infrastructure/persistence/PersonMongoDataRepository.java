package com.culnou.mumu.myway.infrastructure.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;



public interface PersonMongoDataRepository extends MongoRepository<PersonDocument, String> {

}
