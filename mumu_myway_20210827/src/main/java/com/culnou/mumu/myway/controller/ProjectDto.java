package com.culnou.mumu.myway.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.culnou.mumu.myway.domain.model.ProjectType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
@Data
public class ProjectDto {

	private String id;
	private String personId;
	private String visionId;
	private String projectId;
	private String name;
	private String description;
	private ProjectType projectType;
	@JsonFormat(pattern="yyyy/MM/dd HH:mm:ss")
	private Date deadline;
	private int term;
	private String indicator;
	private String criteria;
	private int expendedTime;
	private List<AchievementDto> achievements = new ArrayList<>();
}
