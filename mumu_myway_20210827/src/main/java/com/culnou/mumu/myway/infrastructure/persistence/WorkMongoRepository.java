package com.culnou.mumu.myway.infrastructure.persistence;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.myway.domain.model.Work;
import com.culnou.mumu.myway.domain.model.WorkId;
import com.culnou.mumu.myway.domain.model.WorkRepository;
@Service("workMongoRepository")
@Transactional
public class WorkMongoRepository implements WorkRepository {

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
	public void removeAll() throws Exception {
		// TODO Auto-generated method stub
		workRepository.deleteAll();

	}
	
	private WorkDocument convertWorkToWorkDocument(Work work) {
		WorkDocument doc = new WorkDocument();
		doc.setId(work.workId().id());
		doc.setPersonId(work.personId());
		doc.setActionId(work.actionId());
		doc.setWorkId(work.workId());
		doc.setName(work.name());
		doc.setDescription(work.description());
		doc.setWorkStatus(work.status());
		doc.setExpendedTime(work.expendedTime());
		return doc;
	}

}
