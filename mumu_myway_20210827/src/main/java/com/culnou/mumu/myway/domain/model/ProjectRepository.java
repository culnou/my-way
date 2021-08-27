package com.culnou.mumu.myway.domain.model;



public interface ProjectRepository {
	
	//識別子オブジェクトはリポジトリが生成する。
	public ProjectId nextIdentity() throws Exception;
		
		
	//コマンド。
	public void save(Project project) throws Exception;
			
	//コマンド。
	public void remove(Project project) throws Exception;
			
	//コマンド。
	public void removeAll() throws Exception;

}
