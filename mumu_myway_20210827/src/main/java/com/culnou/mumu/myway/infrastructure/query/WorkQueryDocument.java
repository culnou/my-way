package com.culnou.mumu.myway.infrastructure.query;

import org.springframework.data.mongodb.core.mapping.Document;

import com.culnou.mumu.myway.domain.model.ActionId;
import com.culnou.mumu.myway.domain.model.PersonId;
import com.culnou.mumu.myway.domain.model.WorkId;
import com.culnou.mumu.myway.domain.model.WorkStatus;


import lombok.Data;

@Document(collection = "works")
@Data
public class WorkQueryDocument {
	private String id;
	private PersonId personId;
	private ActionId actionId;
	private WorkId workId;
	private String name;
	private String description;
	private WorkStatus workStatus;
	private int expendedTime;

}
