package com.culnou.mumu.myway.controller;

import java.util.Date;

import com.culnou.mumu.myway.domain.model.WorkStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
@Data
public class WorkDto {
	private String id;
	private String personId;
	private String actionId;
	private String workId;
	private String name;
	private String description;
	@JsonFormat(pattern="yyyy/MM/dd HH:mm:ss")
	private Date startTime;
	@JsonFormat(pattern="yyyy/MM/dd HH:mm:ss")
	private Date endTime;
	private WorkStatus workStatus;
	private int expendedTime;

}
