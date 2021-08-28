package com.culnou.mumu.myway.infrastructure.query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.myway.domain.model.AbstractWorkQuery;

import com.culnou.mumu.myway.domain.model.ActionId;
import com.culnou.mumu.myway.domain.model.Work;
import com.culnou.mumu.myway.domain.model.WorkId;
@Service("workMongoQuery")
@Transactional
public class WorkMongoQuery extends AbstractWorkQuery {
	
	@Autowired
	private WorkMongoDataQuery workQuery;

	@Override
	public Work findById(WorkId workId) throws Exception {
		// TODO Auto-generated method stub
		Optional<WorkQueryDocument> readDoc = workQuery.findById(workId.id());
		if (readDoc.isPresent()){
			WorkQueryDocument doc = readDoc.get();
			return this.convertWorkQueryDocumentToWork(doc);
		}else {
			return null;
		}
	}

	@Override
	public List<Work> findWorksOfAction(ActionId actionId) throws Exception {
		// TODO Auto-generated method stub
		List<WorkQueryDocument> docs = workQuery.findWorksByActionId(actionId);
		//検証用
		System.out.println("******* action " + docs.get(0).getActionId().id());
		return convertWorkQueryDocumentsToWorks(docs);
	}
	
	private Work convertWorkQueryDocumentToWork(WorkQueryDocument doc) {
		Work work = this.convertFrom(doc);
		return work;
		
	}
	
	private List<Work> convertWorkQueryDocumentsToWorks(List<WorkQueryDocument> docs) {
		List<Work> works = new ArrayList<>();
		for(WorkQueryDocument doc : docs) {
			Work work = convertWorkQueryDocumentToWork(doc);
			works.add(work);
		}
		return works;
	}

}
