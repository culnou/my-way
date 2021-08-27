package com.culnou.mumu.myway.domain.model;

import java.util.List;

import com.culnou.mumu.myway.infrastructure.query.VisionQueryDocument;

public abstract class AbstractVisionQuery implements VisionQuery {

	@Override
	public Vision findById(VisionId visionId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vision> findVisionsOfPerson(PersonId personId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vision> findVisionsOfVisionType(VisionType visionType) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected Vision convertFrom(VisionQueryDocument doc){
		return new Vision(doc.getPersonId(), doc.getVisionId(), doc.getVisionType(), doc.getContent());
		
	}

}
