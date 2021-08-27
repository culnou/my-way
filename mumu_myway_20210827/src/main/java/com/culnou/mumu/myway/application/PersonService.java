package com.culnou.mumu.myway.application;

public interface PersonService {
	
	public void assignPerson(Dto user) throws Exception;
	
	public Dto findPersonById(String id) throws Exception;
	
	public void removeAllPerson() throws Exception;

}
