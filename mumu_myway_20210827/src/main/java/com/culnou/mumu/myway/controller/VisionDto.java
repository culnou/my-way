package com.culnou.mumu.myway.controller;

import com.culnou.mumu.myway.domain.model.VisionType;

import lombok.Data;
@Data
public class VisionDto {
	private String id;
	private String personId;
	private String visionId;
	private VisionType visionType;
	private String title;
	private String content;
}
