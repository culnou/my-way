package com.culnou.mumu.myway.domain.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ActionService {
	@Qualifier("actionMongoRepository")
	@Autowired
	private ActionRepository actionRepository;
	@Qualifier("workMongoRepository")
	@Autowired
	private WorkRepository workRepository;
	public void removeAction(ActionId actionId) throws Exception{
		//actionId null チェック
		if(actionId == null) {
			throw new IllegalArgumentException("The actionId may not be set to null.");
		}
		//action存在チェック
		Action action = actionRepository.actionOfId(actionId);
		if(action == null) {
			throw new IllegalArgumentException("The action may not exist.");
		}
		//関連する作業の削除
		List<Work> works = workRepository.worksOfAction(actionId);
		workRepository.removeAll(works);
		//アクションの削除
		actionRepository.remove(action);
	}

}
