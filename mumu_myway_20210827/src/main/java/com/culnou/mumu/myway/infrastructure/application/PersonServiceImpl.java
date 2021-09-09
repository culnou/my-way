package com.culnou.mumu.myway.infrastructure.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.myway.application.PersonService;
import com.culnou.mumu.myway.controller.PersonDto;
import com.culnou.mumu.myway.controller.UserDto;
import com.culnou.mumu.myway.controller.VisionDto;
import com.culnou.mumu.myway.domain.model.Person;
import com.culnou.mumu.myway.domain.model.PersonFactory;
import com.culnou.mumu.myway.domain.model.PersonId;

import com.culnou.mumu.myway.domain.model.PersonRepository;
import com.culnou.mumu.myway.domain.model.User;
import com.culnou.mumu.myway.domain.model.Vision;
import com.culnou.mumu.myway.domain.model.VisionId;
import com.culnou.mumu.myway.domain.model.VisionRepository;

@Service("personServiceImpl")
@Transactional
public class PersonServiceImpl implements PersonService {
	
	@Qualifier("personMongoRepository")
	@Autowired
	private PersonRepository personRepository;
	@Qualifier("visionMongoRepository")
	@Autowired
	private VisionRepository visionRepository;
	

	@Override
	public void assignPerson(UserDto user) throws Exception {
		// TODO Auto-generated method stub
		UserDto userDto = (UserDto)user;
		User usr = new User(userDto.getId(), userDto.getFirstName(), userDto.getLastName(), userDto.getFullName(), userDto.getEmail());
		Person person = PersonFactory.creatPerson(usr);
        personRepository.save(person);
	}

	@Override
	public PersonDto createPerson(UserDto user) throws Exception {
		// TODO Auto-generated method stub
		
		UserDto userDto = (UserDto)user;
		User usr = new User(userDto.getId(), userDto.getFirstName(), userDto.getLastName(), userDto.getFullName(), userDto.getEmail());
		Person person = PersonFactory.creatPerson(usr);
        personRepository.save(person);
		return new PersonDto(person.personId().id(), person.fullName().firstName(), person.fullName().lastName(), person.email().address());
		
		
	}
	@Override
	public void updatePerson(PersonDto person) throws Exception {
		// TODO Auto-generated method stub
		PersonId personId = new PersonId(person.getId());
		Person readPerson = personRepository.personOfId(personId);
		readPerson.setPhilosophy(person.getPhilosophy());
		readPerson.setPurpose(person.getPurpose());
		readPerson.setActionGuideline(person.getActionGuideline());
		personRepository.save(readPerson);
	}
	
	@Override
	public PersonDto findPersonById(String id) throws Exception {
		// TODO Auto-generated method stub
		PersonId personId = new PersonId(id);
		Person person = personRepository.personOfId(personId);
		PersonDto personDto = new PersonDto(person.personId().id(), person.fullName().firstName(), person.fullName().lastName(), person.email().address());
		personDto.setPhilosophy(person.philosophy());
		personDto.setPurpose(person.purpose());
		personDto.setActionGuideline(person.actionGuideline());
		return personDto;
	}

	@Override
	public void deletePerson(String id) throws Exception {
		// TODO Auto-generated method stub
		PersonId personId = new PersonId(id);
		Person person = personRepository.personOfId(personId);
		personRepository.remove(person);
		
		
	}

	@Override
	public List<VisionDto> findVisionsByPersonId(String personId) throws Exception {
		// TODO Auto-generated method stub
		PersonId pid = new PersonId(personId);
		List<Vision> readVisions = visionRepository.visionsOfPerson(pid);
		List<VisionDto> visionDtos = new ArrayList<>();
		for(Vision vision : readVisions) {
			VisionDto dto = new VisionDto();
			dto.setId(vision.visionId().id());
			dto.setPersonId(vision.personId().id());
			dto.setVisionId(vision.visionId().id());
			dto.setVisionType(vision.visionType());
			dto.setContent(vision.content());
			visionDtos.add(dto);
		}
		return visionDtos;
	}

	@Override
	public VisionDto findVisionById(String id) throws Exception {
		// TODO Auto-generated method stub
		VisionId visionId = new VisionId(id);
		Vision vision = visionRepository.visionOfId(visionId);
		VisionDto dto = new VisionDto();
		dto.setId(vision.visionId().id());
		dto.setPersonId(vision.personId().id());
		dto.setVisionId(vision.visionId().id());
		dto.setVisionType(vision.visionType());
		dto.setContent(vision.content());
		return dto;
	}

	@Override
	public VisionDto addVision(VisionDto visionDto) throws Exception {
		// TODO Auto-generated method stub
		//個人の取得
		PersonId personId = new PersonId(visionDto.getPersonId());
		Person person = personRepository.personOfId(personId);
		//個人の存在チェック
		if(person == null) {
			throw new Exception("The person may not exist.");
		}
		//ビジョンタイプのチェック
		if(visionDto.getVisionType() == null) {
			throw new Exception("The visionType may not exist.");
		}
		//識別子オブジェクトの生成
		VisionId visionId = visionRepository.nextIdentity();
		//ビジョンの生成
		Vision vision = person.createVision(visionId, visionDto.getVisionType(), visionDto.getContent());
		//ビジョンの保存
		visionRepository.save(vision);
		
		visionDto.setId(vision.visionId().id());
		visionDto.setVisionId(vision.visionId().id());
		return visionDto;
	}

	@Override
	public void updateVision(VisionDto vision) throws Exception {
		// TODO Auto-generated method stub
		VisionId visionId = new VisionId(vision.getVisionId());
		Vision readVision = visionRepository.visionOfId(visionId);
		if(readVision == null) {
			throw new Exception("The vision may not exist.");
		}
		readVision.setContent(vision.getContent());
		visionRepository.save(readVision);
	}

	@Override
	public void deleteVision(String id) throws Exception {
		// TODO Auto-generated method stub
		VisionId visionId = new VisionId(id);
		Vision readVision = visionRepository.visionOfId(visionId);
		if(readVision == null) {
			throw new Exception("The vision may not exist.");
		}
		visionRepository.remove(readVision);
		
	}

	

	

	

	

}
