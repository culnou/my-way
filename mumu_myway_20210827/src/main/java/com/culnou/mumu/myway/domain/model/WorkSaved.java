package com.culnou.mumu.myway.domain.model;

import lombok.Data;

//JMSで渡すイベントメッセージ。
//Workの消費時間をActionとProjectに加算するためのもの。
@Data
public class WorkSaved {
	
	private String personId;
	private String projectId;
	private String actionId;
	private String workId;
	private int expendedTime;

}
