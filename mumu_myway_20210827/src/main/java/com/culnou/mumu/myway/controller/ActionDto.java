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
	//アクションのルーティン化のため追加。2021/10/26
	private boolean routine;

}
