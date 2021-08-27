package com.culnou.mumu.myway.infrastructure.query;

import org.springframework.data.mongodb.core.mapping.Document;

import com.culnou.mumu.myway.domain.model.PersonId;
import com.culnou.mumu.myway.domain.model.Strategy;
import com.culnou.mumu.myway.domain.model.VisionId;
import com.culnou.mumu.myway.domain.model.VisionType;

import lombok.Data;
@Document(collection = "visions")
@Data
public class VisionQueryDocument {
	
	private String id;
	private PersonId personId;
	private VisionId visionId;
	private VisionType visionType;
	private String content;
	private VisionId result;
	private Strategy strategy;

}
