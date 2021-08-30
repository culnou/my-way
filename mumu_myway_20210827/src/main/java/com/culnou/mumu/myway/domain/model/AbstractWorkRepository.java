package com.culnou.mumu.myway.domain.model;



import com.culnou.mumu.myway.infrastructure.persistence.WorkDocument;


public abstract class AbstractWorkRepository implements WorkRepository {

		
	protected Work convertFrom(WorkDocument doc) {
		Work work = new Work(doc.getPersonId(), doc.getActionId(), doc.getWorkId(), doc.getName(), doc.getDescription());
		work.setExpendedTime(doc.getExpendedTime());
		if(doc.getWorkStatus() != null) {
			work.changeStatus(doc.getWorkStatus());
		}
		return work;
	}
	
	

}
