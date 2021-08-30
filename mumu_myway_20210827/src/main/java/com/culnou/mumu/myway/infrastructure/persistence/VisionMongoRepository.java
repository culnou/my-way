package com.culnou.mumu.myway.infrastructure.persistence;




import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.myway.domain.model.AbstractVisionRepository;
import com.culnou.mumu.myway.domain.model.PersonId;
import com.culnou.mumu.myway.domain.model.Vision;
import com.culnou.mumu.myway.domain.model.VisionId;

import com.culnou.mumu.myway.domain.model.VisionType;

@Service("visionMongoRepository")
@Transactional
public class VisionMongoRepository extends AbstractVisionRepository {
	
	@Autowired
	private VisionMongoDataRepository visionRepository;

	@Override
	public VisionId nextIdentity() throws Exception {
		// TODO Auto-generated method stub
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        
		return new VisionId(str);
	}
    
	@Override
	public void save(Vision vision) throws Exception {
		// TODO Auto-generated method stub
		VisionDocument doc = this.convertVisionToVisionDocument(vision);
        visionRepository.save(doc);
	}

	@Override
	public void remove(Vision vision) throws Exception {
		// TODO Auto-generated method stub
		VisionDocument doc = this.convertVisionToVisionDocument(vision);
		visionRepository.delete(doc);
	}

	@Override
	public void removeAll(List<Vision> visions) throws Exception {
		// TODO Auto-generated method stub
		for(Vision vision : visions) {
			this.remove(vision);
		}

	}
	

	@Override
	public void saveAll(List<Vision> visions) throws Exception {
		// TODO Auto-generated method stub
		for(Vision vision : visions) {
			this.save(vision);
		}
		
	}

	@Override
	public Vision visionOfId(VisionId visionId) throws Exception {
		// TODO Auto-generated method stub
		Optional<VisionDocument> readDoc = visionRepository.findById(visionId.id());
		if (readDoc.isPresent()){
			VisionDocument doc = readDoc.get();
			return this.convertFrom(doc);
		}else {
			return null;
		}
	}

	@Override
	public List<Vision> visionsOfPerson(PersonId personId) throws Exception {
		// TODO Auto-generated method stub
		List<VisionDocument> docs = visionRepository.findVisionsByPersonId(personId);
		//検証用
		System.out.println("******* person " + docs.get(0).getPersonId().id());
		return convertVisionDocumentsToVisions(docs);
	}

	@Override
	public List<Vision> visionsOfVisionType(VisionType visionType) throws Exception {
		// TODO Auto-generated method stub
		List<VisionDocument> docs = visionRepository.findVisionsByVisionType(visionType);
		//検証用
		System.out.println("******* visionType " + docs.get(0).getVisionType());
		return convertVisionDocumentsToVisions(docs);
	}
	
	private VisionDocument convertVisionToVisionDocument(Vision vision) {
		VisionDocument doc = new VisionDocument();
		doc.setId(vision.visionId().id());
		doc.setPersonId(vision.personId());
		doc.setVisionId(vision.visionId());
		doc.setVisionType(vision.visionType());
		doc.setContent(vision.content());
		return doc;
	}
	
	private Vision convertVisionDocumentToVision(VisionDocument doc) {
		return this.convertFrom(doc);
	}
	
	private List<Vision> convertVisionDocumentsToVisions(List<VisionDocument> docs) {
		List<Vision> visions = new ArrayList<>();
		for(VisionDocument doc : docs) {
			Vision vision = convertVisionDocumentToVision(doc);
			visions.add(vision);
		}
		return visions;
	}
	
}
