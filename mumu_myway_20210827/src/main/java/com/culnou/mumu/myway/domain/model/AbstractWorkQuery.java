package com.culnou.mumu.myway.domain.model;

import com.culnou.mumu.myway.infrastructure.query.WorkQueryDocument;

public abstract class AbstractWorkQuery implements WorkQuery {

	protected Work convertFrom(WorkQueryDocument doc) {
		Work work = new Work(doc.getPersonId(), doc.getActionId(), doc.getWorkId(), doc.getName(), doc.getDescription());
		work.setExpendedTime(doc.getExpendedTime());
		return work;
	}

}
