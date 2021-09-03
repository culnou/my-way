package com.culnou.mumu.myway.application;

import java.util.List;

import com.culnou.mumu.myway.controller.PersonDto;
import com.culnou.mumu.myway.controller.UserDto;
import com.culnou.mumu.myway.controller.VisionDto;


public interface PersonService {
	
	public void assignPerson(UserDto user) throws Exception;
	
	public PersonDto findPersonById(String id) throws Exception;
	
	public void deletePerson(String id) throws Exception;
	
	public List<VisionDto> findVisionsByPersonId(String personId) throws Exception;
	
	public VisionDto findVisionById(String id) throws Exception;
	
	//ファクトリーメソッド
	public VisionDto addVision(VisionDto visionDto) throws Exception;
	
	public void updateVision(VisionDto visionDto) throws Exception;
	
	public void deleteVision(String id) throws Exception;
	

}
