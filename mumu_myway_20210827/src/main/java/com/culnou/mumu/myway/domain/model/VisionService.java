package com.culnou.mumu.myway.domain.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VisionService {
	@Qualifier("visionMongoRepository")
	@Autowired
	private VisionRepository visionRepository;
	@Qualifier("projectMongoRepository")
	@Autowired
	private ProjectRepository projectRepository;
	
	public void removeVison(VisionId visionId) throws Exception{
		//visionId null チェック
		if(visionId == null) {
			throw new IllegalArgumentException("The visiionId may not be set to null.");
		}
		//Visionの存在チェック
		Vision vision = visionRepository.visionOfId(visionId);
		if(vision == null) {
			throw new IllegalArgumentException("The visiion may not exist.");
		}
		//Visionに対するProjectの存在チェック（ビジネスロジック）
		List<Project> projects = projectRepository.projectsOfVision(visionId);
		if(projects.size() > 0) {
			throw new ProjectExistException("Some associated projects of the visiion exist.");
		}
		//Visionの削除
		visionRepository.remove(vision);
	}

	
}
