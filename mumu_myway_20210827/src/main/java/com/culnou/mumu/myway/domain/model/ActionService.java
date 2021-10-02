package com.culnou.mumu.myway.domain.model;



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
	@Qualifier("projectMongoRepository")
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	@Qualifier("actionJmsSender")
	private ActionSender actionSender;
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
		
		//非同期処理でアクションを削除。
		ActionRemoved actionRemvoed = new ActionRemoved();
		actionRemvoed.setPersonId(action.personId().id());
		actionRemvoed.setActionId(action.actionId().id());
		actionRemvoed.setExpendedTime(action.expendedTime());
		actionSender.removeAction(actionRemvoed);
		
		//非同期処理に変更。2021/10/2
		/*
		//関連する作業の削除
		List<Work> works = workRepository.worksOfAction(actionId);
		workRepository.removeAll(works);
		
		//関連するプロジェクトの消費時間の減算
		Project project = projectRepository.projectOfId(action.projectId());
		project.addExpendedTime(-action.expendedTime());
		
		//アクションの削除
		actionRepository.remove(action);
		*/
	}

}
