package com.culnou.mumu.myway.domain.model;

public interface PersonRepository {
	//コマンド。
	public void save(Person person) throws Exception;
	
	
	//コマンド。
	public void remove(Person person) throws Exception;
	
	//コマンド。
	public void removeAll() throws Exception;

}
