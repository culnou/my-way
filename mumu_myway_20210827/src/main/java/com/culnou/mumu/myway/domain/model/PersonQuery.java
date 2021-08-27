package com.culnou.mumu.myway.domain.model;

public interface PersonQuery {
	
	//findByIdはクエリだがリポジトリで定義する。
	//集約間、ドメインモデルとアプリケーションサービスの間、モジュール間の受け渡しには、識別子はStringのidではなく、不変性が保証された識別子オブジェクトを使う。
	public Person findById(PersonId personId) throws Exception;

}
