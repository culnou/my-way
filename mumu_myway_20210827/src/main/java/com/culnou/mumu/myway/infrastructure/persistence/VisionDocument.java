package com.culnou.mumu.myway.infrastructure.persistence;

import org.springframework.data.mongodb.core.mapping.Document;

import com.culnou.mumu.myway.domain.model.PersonId;

import com.culnou.mumu.myway.domain.model.VisionId;
import com.culnou.mumu.myway.domain.model.VisionType;

import lombok.Data;

@Document(collection = "visions")
@Data
public class VisionDocument {
	
	private String id;
	private PersonId personId;
	private VisionId visionId;
	private VisionType visionType;
	private String title;
	private String content;
	
	

}
