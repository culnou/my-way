package com.culnou.mumu.myway.controller;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
@Data
public class AchievementDto {
	private String id;
	@JsonFormat(pattern="yyyy/MM/dd HH:mm:ss")
	private Date execTime;
	private String result;
	private String awareness;

}
