package com.culnou.mumu.myway.domain.model;

public interface WorkRepository {
	
	//識別子オブジェクトはリポジトリが生成する。
	public WorkId nextIdentity() throws Exception;
		
	public void save(Work work) throws Exception;
		
	public void remove(Work work) throws Exception;
		
	public void removeAll() throws Exception;

}
