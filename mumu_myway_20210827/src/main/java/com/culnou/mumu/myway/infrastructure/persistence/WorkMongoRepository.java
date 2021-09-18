package com.culnou.mumu.myway.infrastructure.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.myway.domain.model.AbstractWorkRepository;
import com.culnou.mumu.myway.domain.model.ActionId;
import com.culnou.mumu.myway.domain.model.Work;
import com.culnou.mumu.myway.domain.model.WorkId;

@Service("workMongoRepository")
@Transactional
public class WorkMongoRepository extends AbstractWorkRepository {

	@Autowired
	private WorkMongoDataRepository workRepository;
	
	@Override
	public WorkId nextIdentity() throws Exception {
		// TODO Auto-generated method stub
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        
		return new WorkId(str);
	}

	@Override
	public void save(Work work) throws Exception {
		// TODO Auto-generated method stub
		WorkDocument doc = this.convertWorkToWorkDocument(work);
		workRepository.save(doc);

	}

	@Override
	public void remove(Work work) throws Exception {
		// TODO Auto-generated method stub
		WorkDocument doc = this.convertWorkToWorkDocument(work);
		workRepository.delete(doc);

	}

	@Override
	public void saveAll(List<Work> works) throws Exception {
		// TODO Auto-generated method stub
		for(Work work : works) {
			this.save(work);
		}
		
	}

	@Override
	public void removeAll(List<Work> works) throws Exception {
		// TODO Auto-generated method stub
		for(Work work : works) {
			this.remove(work);
		}
	}

	@Override
	public Work workOfId(WorkId workId) throws Exception {
		// TODO Auto-generated method stub
		Optional<WorkDocument> readDoc = workRepository.findById(workId.id());
		if (readDoc.isPresent()){
			WorkDocument doc = readDoc.get();
			return this.convertDocumentToWork(doc);
		}else {
			return null;
		}
	}

	@Override
	public List<Work> worksOfAction(ActionId actionId) throws Exception {
		// TODO Auto-generated method stub
		List<WorkDocument> docs = workRepository.findWorksByActionId(actionId);
		return convertWorkDocumentsToWorks(docs);
	}
	
	private WorkDocument convertWorkToWorkDocument(Work work) {
		WorkDocument doc = new WorkDocument();
		doc.setId(work.workId().id());
		doc.setPersonId(work.personId());
		doc.setActionId(work.actionId());
		doc.setWorkId(work.workId());
		doc.setName(work.name());
		doc.setDescription(work.description());
		doc.setStartTime(work.startTime());
		doc.setEndTime(work.endTime());
		doc.setWorkStatus(work.status());
		doc.setExpendedTime(work.expendedTime());
		return doc;
	}
	
	private Work convertDocumentToWork(WorkDocument doc) {
		Work work = this.convertFrom(doc);
		return work;
		
	}
	
	private List<Work> convertWorkDocumentsToWorks(List<WorkDocument> docs) {
		List<Work> works = new ArrayList<>();
		for(WorkDocument doc : docs) {
			Work work = convertDocumentToWork(doc);
			works.add(work);
		}
		return works;
	}

	

}
