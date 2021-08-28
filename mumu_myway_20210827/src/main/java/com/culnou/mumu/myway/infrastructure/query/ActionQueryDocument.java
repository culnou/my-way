package com.culnou.mumu.myway.infrastructure.query;

import org.springframework.data.mongodb.core.mapping.Document;

import com.culnou.mumu.myway.domain.model.ActionId;
import com.culnou.mumu.myway.domain.model.PersonId;
import com.culnou.mumu.myway.domain.model.ProjectId;


import lombok.Data;

@Document(collection = "actions")
@Data
public class ActionQueryDocument {
	private String id;
	private PersonId personId;
	private ProjectId projectId;
	private ActionId actionId;
	private String name;
	private String description;
	private int expendedTime;

}
