package com.culnou.mumu.myway.controller;



import lombok.Data;
@Data
public class ActionDto {
	private String id;
	private String personId;
	private String projectId;
	private String actionId;
	private String name;
	private String description;
	private int expendedTime;

}
