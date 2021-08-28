package com.culnou.mumu.myway.infrastructure.persistence;

import org.springframework.data.mongodb.core.mapping.Document;

import com.culnou.mumu.myway.domain.model.ActionId;
import com.culnou.mumu.myway.domain.model.PersonId;
import com.culnou.mumu.myway.domain.model.WorkId;
import com.culnou.mumu.myway.domain.model.WorkStatus;

import lombok.Data;
@Document(collection = "works")
@Data
public class WorkDocument {
	private String id;
	private PersonId personId;
	private ActionId actionId;
	private WorkId workId;
	private String name;
	private String description;
	private WorkStatus workStatus;
	private int expendedTime;

}
