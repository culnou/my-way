package com.culnou.mumu.myway.infrastructure.persistence;

import org.springframework.data.mongodb.core.mapping.Document;

import com.culnou.mumu.myway.domain.model.Email;
import com.culnou.mumu.myway.domain.model.FullName;
import com.culnou.mumu.myway.domain.model.PersonId;

import lombok.Data;

@Document(collection = "persons")
@Data
public class PersonDocument {
	private String id;
	private PersonId personId;
	private FullName fullName;
	private Email email;
	private String philosophy;
	private String purpose;
	private String actionGuideline;

}
