package com.culnou.mumu.myway.infrastructure.persistence;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.myway.domain.model.Action;
import com.culnou.mumu.myway.domain.model.ActionId;
import com.culnou.mumu.myway.domain.model.ActionRepository;

@Service("actionMongoRepository")
@Transactional
public class ActionMongoRepository implements ActionRepository {
	
	@Autowired
	private ActionMongoDataRepository actionRepository;

	@Override
	public ActionId nextIdentity() throws Exception {
		// TODO Auto-generated method stub
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        
		return new ActionId(str);
	}

	@Override
	public void save(Action action) throws Exception {
		// TODO Auto-generated method stub
		ActionDocument doc = this.convertActionToActionDocument(action);
		actionRepository.save(doc);

	}

	@Override
	public void remove(Action action) throws Exception {
		// TODO Auto-generated method stub
		ActionDocument doc = this.convertActionToActionDocument(action);
		actionRepository.delete(doc);

	}

	@Override
	public void removeAll() throws Exception {
		// TODO Auto-generated method stub
		actionRepository.deleteAll();

	}
	
	private ActionDocument convertActionToActionDocument(Action action) {
		ActionDocument doc = new ActionDocument();
		doc.setId(action.actionId().id());
		doc.setPersonId(action.personId());
		doc.setProjectId(action.projectId());
		doc.setActionId(action.actionId());
		doc.setName(action.name());
		doc.setDescription(action.description());
		doc.setExpendedTime(action.expendedTime());
		return doc;
	}

}
