package com.culnou.mumu.myway.domain.model;

import java.util.List;

public interface PersonRepository {
	//コマンド。
	public void save(Person person) throws Exception;

	public void saveAll(List<Person> persons) throws Exception;
	
	public void remove(Person person) throws Exception;

	public void removeAll(List<Person> persons) throws Exception;
	
	//クエリ。
	public Person personOfId(PersonId personId) throws Exception;

}
