package com.culnou.mumu.myway.domain.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProjectService {
	
	@Qualifier("projectMongoRepository")
	@Autowired
	private ProjectRepository projectRepository;
	@Qualifier("actionMongoRepository")
	@Autowired
	private ActionRepository actionRepository;
	
	public void removeProject(ProjectId projectId) throws Exception{
		//nullチェック
		if(projectId == null) {
			throw new IllegalArgumentException("The projectId may not be set to null.");
		}
		//プロジェクトの存在チェック
		Project project = projectRepository.projectOfId(projectId);
		if(project == null) {
			throw new IllegalArgumentException("The project may not exist.");
		}
		//関連づけられたアクションの存在チェック
		List<Action> actions = actionRepository.actionsOfProject(projectId);
		if(actions.size() > 0) {
			throw new ActionExistException("Some associated actions of the visiion exist.");
		}
		//プロジェクトの削除
		projectRepository.remove(project);
	}

}
