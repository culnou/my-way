package com.culnou.mumu.myway.domain.model;

import java.util.List;

public interface VisionRepository {
	
	//識別子オブジェクトはリポジトリが生成する。
	public VisionId nextIdentity() throws Exception;
	
	
	//コマンド。
	public void save(Vision vision) throws Exception;
	
	public void saveAll(List<Vision> visions) throws Exception;
		
	public void remove(Vision vision) throws Exception;
		
	public void removeAll(List<Vision> visions) throws Exception;
	
	//クエリ
	public Vision visionOfId(VisionId visionId) throws Exception;
	
	public List<Vision> visionsOfPerson(PersonId personId) throws Exception;
	
	public List<Vision> visionsOfVisionType(VisionType visionType) throws Exception;

}
