package com.culnou.mumu.myway.domain.model;

import java.util.List;

public interface WorkRepository {
	
	//識別子オブジェクトはリポジトリが生成する。
	public WorkId nextIdentity() throws Exception;
		
	public void save(Work work) throws Exception;
	
	public void saveAll(List<Work> works) throws Exception;
		
	public void remove(Work work) throws Exception;
		
	public void removeAll(List<Work> works) throws Exception;
	
	public Work workOfId(WorkId workId) throws Exception;
	
	public List<Work> worksOfAction(ActionId actionId) throws Exception;

}
