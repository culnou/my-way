package com.culnou.mumu.myway.infrastructure.persistence;

import org.springframework.data.mongodb.core.mapping.Document;

import com.culnou.mumu.myway.domain.model.ActionId;
import com.culnou.mumu.myway.domain.model.PersonId;
import com.culnou.mumu.myway.domain.model.ProjectId;

import lombok.Data;
@Document(collection = "actions")
@Data
public class ActionDocument {
	private String id;
	private PersonId personId;
	private ProjectId projectId;
	private ActionId actionId;
	private String name;
	private String description;
	private int expendedTime;
	//アクションのルーティン化のため追加。2021/10/26
	private boolean routine;

}
