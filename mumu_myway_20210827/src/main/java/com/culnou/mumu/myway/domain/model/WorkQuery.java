package com.culnou.mumu.myway.domain.model;

import java.util.List;

public interface WorkQuery {
	
    public Work findById(WorkId workId) throws Exception;
	
	
	public List<Work> findWorksOfAction(ActionId actionId) throws Exception;

}
