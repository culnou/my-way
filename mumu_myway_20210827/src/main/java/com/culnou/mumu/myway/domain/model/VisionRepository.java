package com.culnou.mumu.myway.domain.model;


public interface VisionRepository {
	
	//識別子オブジェクトはリポジトリが生成する。
	public VisionId nextIdentity() throws Exception;
	
	
	//コマンド。
	public void save(Vision vision) throws Exception;
		
	//コマンド。
	public void remove(Vision vision) throws Exception;
		
	//コマンド。
	public void removeAll() throws Exception;

}
