package com.culnou.mumu.myway.infrastructure.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectMongoDataRepository extends MongoRepository<ProjectDocument, String> {

}
