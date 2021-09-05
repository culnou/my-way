package com.culnou.mumu.myway.infrastructure.persistence;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.culnou.mumu.myway.domain.model.PersonId;
import com.culnou.mumu.myway.domain.model.ProjectId;
import com.culnou.mumu.myway.domain.model.ProjectType;
import com.culnou.mumu.myway.domain.model.VisionId;

import lombok.Data;

@Document(collection = "projects")
@Data
public class ProjectDocument {
	private String id;
	private PersonId personId;
	private VisionId visionId;
	private ProjectId projectId;
	private String name;
	private String description;
	private ProjectType projectType;
	private Date deadline;
	private int term;
	private String indicator;
	private String criteria;
	private int expendedTime;

}
