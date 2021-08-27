package com.culnou.mumu.myway.domain.model;

import java.util.List;

public interface VisionQuery {
	
	//集約間、ドメインモデルとアプリケーションサービスの間、モジュール間の受け渡しには、識別子はStringのidではなく、不変性が保証された識別子オブジェクトを使う。
	public Vision findById(VisionId visionId) throws Exception;
	
	public List<Vision> findVisionsOfPerson(PersonId personId) throws Exception;
	
	public List<Vision> findVisionsOfVisionType(VisionType visionType) throws Exception;

}
