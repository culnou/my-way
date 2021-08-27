package com.culnou.mumu.myway.infrastructure.query;

import org.springframework.data.mongodb.core.mapping.Document;

import com.culnou.mumu.myway.domain.model.PersonId;

import lombok.Data;

@Document(collection = "persons")
@Data
public class PersonQueryDocument {
	private String id;
	private PersonId personId;
	private String name;

}
