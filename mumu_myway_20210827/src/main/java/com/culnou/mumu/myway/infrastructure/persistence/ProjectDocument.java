package com.culnou.mumu.myway.infrastructure.persistence;

import org.springframework.data.mongodb.core.mapping.Document;

import com.culnou.mumu.myway.domain.model.Goal;
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
	private Goal goal;

}
