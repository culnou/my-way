package com.culnou.mumu.myway.domain.model;


import com.culnou.mumu.myway.infrastructure.persistence.VisionDocument;


public abstract class AbstractVisionRepository implements VisionRepository {

	
	
	protected Vision convertFrom(VisionDocument doc){
		return new Vision(doc.getPersonId(), doc.getVisionId(), doc.getVisionType(), doc.getContent());
		
	}

}
