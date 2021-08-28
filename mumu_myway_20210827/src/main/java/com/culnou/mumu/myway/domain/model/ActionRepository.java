package com.culnou.mumu.myway.domain.model;

public interface ActionRepository {
	
	//識別子オブジェクトはリポジトリが生成する。
	public ActionId nextIdentity() throws Exception;
	
	public void save(Action action) throws Exception;
	
	public void remove(Action action) throws Exception;
	
	public void removeAll() throws Exception;

}
