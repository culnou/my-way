package com.culnou.mumu.myway.controller;

import static org.junit.Assert.*;


import java.util.List;
import java.util.UUID;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonRestControllerTest {
	
	@Autowired
	private TestRestTemplate template;
	@LocalServerPort
	private int port;
	private HttpHeaders httpHeaders;
	
	@Before
	public void setUp() throws Exception {
		httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
	}

	@After
	public void tearDown() throws Exception {
		String deleteurl = "http://localhost:" + port + "/persons";
		template.delete(deleteurl);
	}

	//PersonDtoを返すcreatePersonを作成したので不要になりました。2021/9/7
	/*
	@Test
	public void testAssignPerson() throws Exception{
		JSONObject user = new JSONObject();
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        String firstName = "taro";
        String lastName = "yamada";
        String name = "test name";
        String email = "ss@ss.com";
		user.put("id", str);
		user.put("firstName", firstName);
		user.put("lastName", lastName);
		user.put("fullName", name);
		user.put("email", email);
		//個人の割り当て
        String postUrl = "http://localhost:" + port + "/persons";
		HttpEntity<String> request = 
			      new HttpEntity<String>(user.toString(), httpHeaders);
		template.postForObject(postUrl, request, String.class);
		//個人の取得
		String getUrl = "http://localhost:" + port + "/persons/"  + str;
		PersonDto personDto = 
				template.getForObject(getUrl, PersonDto.class);
		assertNotNull(personDto);
		assertEquals(personDto.getId(), str);
		assertEquals(personDto.getFirstName(), firstName);
		//個人の削除
		String deleteUrl = "http://localhost:" + port + "/persons/"  + str;
		template.delete(deleteUrl, String.class);
	}
	*/
	
	@Test
	public void testPerson() throws Exception{
		JSONObject user = new JSONObject();
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        String firstName = "taro";
        String lastName = "yamada";
        String name = "test name";
        String email = "ss@ss.com";
		user.put("id", str);
		user.put("firstName", firstName);
		user.put("lastName", lastName);
		user.put("fullName", name);
		user.put("email", email);
		//個人の割り当て
        String postUrl = "http://localhost:" + port + "/persons";
		HttpEntity<String> request = 
			      new HttpEntity<String>(user.toString(), httpHeaders);
		PersonDto personDto = template.postForObject(postUrl, request, PersonDto.class);
		System.out.println("***** create person " + personDto.getEmail());
		
		assertNotNull(personDto);
		assertEquals(personDto.getId(), str);
		assertEquals(personDto.getFirstName(), firstName);
		
		//個人の更新
		JSONObject updatePerson = new JSONObject();
		updatePerson.put("id", str);
		updatePerson.put("firstName", firstName);
		updatePerson.put("lastName", lastName);
		updatePerson.put("fullName", name);
		updatePerson.put("email", email);
		updatePerson.put("philosophy", "PhilosophySS");
		updatePerson.put("purpose", "PurposeSS");
		updatePerson.put("actionGuideline", "ActionGuidelineSS");
		String putUrl = "http://localhost:" + port + "/persons";
		HttpEntity<String> request4 = 
			      new HttpEntity<String>(updatePerson.toString(), httpHeaders);
		template.exchange(putUrl, HttpMethod.PUT, request4, Void.class);
		
		//個人の取得
		String getUrl = "http://localhost:" + port + "/persons/"  + str;
		PersonDto readPerson = 
				template.getForObject(getUrl, PersonDto.class);
		assertNotNull(readPerson);
		assertEquals(readPerson.getPhilosophy(), "PhilosophySS");
		assertEquals(readPerson.getPurpose(), "PurposeSS");
		assertEquals(readPerson.getActionGuideline(), "ActionGuidelineSS");
		
		//個人の削除
		String deleteUrl = "http://localhost:" + port + "/persons/"  + str;
		template.delete(deleteUrl, String.class);
	}
	
	@Test
	public void testVision() throws Exception{
		JSONObject user = new JSONObject();
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        String firstName = "taro";
        String lastName = "yamada";
        String name = "test name";
        String email = "ss@ss.com";
		user.put("id", str);
		user.put("firstName", firstName);
		user.put("lastName", lastName);
		user.put("fullName", name);
		user.put("email", email);
		//個人の割り当て
        String postUrl = "http://localhost:" + port + "/persons";
		HttpEntity<String> request = 
			      new HttpEntity<String>(user.toString(), httpHeaders);
		template.postForObject(postUrl, request, String.class);
		
		//ビジョンの追加
		//Vision1
		JSONObject vision = new JSONObject();
		vision.put("personId", str);
		vision.put("visionType", "BUSINESS");
		vision.put("title", "test title");
		vision.put("content", "test content");
		String postUrl1 = "http://localhost:" + port + "/visions";
		HttpEntity<String> request2 = 
			      new HttpEntity<String>(vision.toString(), httpHeaders);
		VisionDto visionDto = template.postForObject(postUrl1, request2, VisionDto.class);
		System.out.println("**** vision1 id " + visionDto.getId() );
		//Vision2
		JSONObject vision2 = new JSONObject();
		vision2.put("personId", str);
		vision2.put("visionType", "BUSINESS");
		vision2.put("title", "test title2");
		vision2.put("content", "test content2");
		String postUrl2 = "http://localhost:" + port + "/visions";
		HttpEntity<String> request3 = 
			      new HttpEntity<String>(vision2.toString(), httpHeaders);
		VisionDto visionDto2 = template.postForObject(postUrl2, request3, VisionDto.class);
		System.out.println("**** vision2 id " + visionDto2.getId() );
		System.out.println("**** vision2 content " + visionDto2.getContent() );
		
		//ビジョンの更新
		String content = "更新されたコンテンツ";
		String title = "更新されたタイトル";
		String visionId = visionDto2.getVisionId();
		JSONObject vision3 = new JSONObject();
		vision3.put("visionId", visionId);
		vision3.put("personId", str);
		vision3.put("visionType", "BUSINESS");
		vision3.put("title", title);
		vision3.put("content", content);
		String putUrl = "http://localhost:" + port + "/visions";
		HttpEntity<String> request4 = 
			      new HttpEntity<String>(vision3.toString(), httpHeaders);
		template.exchange(putUrl, HttpMethod.PUT, request4, Void.class);
		
		//ビジョンの取得
		String getUrl = "http://localhost:" + port + "/visions/"  + visionDto2.getId();
		VisionDto readVision = 
				template.getForObject(getUrl, VisionDto.class);
		System.out.println("*** read vision2 " + readVision.getId());
		System.out.println("**** updated vision2 content : " + readVision.getId() + ":" + readVision.getContent() );
		assertNotNull(readVision);
		assertEquals(readVision.getPersonId(), str);
		assertEquals(readVision.getContent(), content);
		
		//個人のビジョンリストの取得
		String getPersonUrl = "http://localhost:" + port + "/visions/person/" + str;
		ResponseEntity<List<VisionDto>> responseEntity = template.exchange(
				getPersonUrl,
				    HttpMethod.GET,
				    null,
				    new ParameterizedTypeReference<List<VisionDto>>() {}
				  );
		List<VisionDto> dtos = responseEntity.getBody();
		for(VisionDto dto : dtos) {
			System.out.println("*** person vision" + dto.getId());
		}
		
		//プロジェクトの作成
		String addProjectUrl = "http://localhost:" + port + "/projects/";
		JSONObject prj = new JSONObject();
		prj.put("personId", str);
		prj.put("visionId", visionDto2.getVisionId());
		prj.put("projectType", "EXPERIMENT");
		prj.put("name", "111");
		prj.put("description", "111");
		prj.put("criteria", "111");
		prj.put("indicator", "111");
		prj.put("deadline", "2021/10/01 11:33:33");
		prj.put("term", 0);
		HttpEntity<String> prjreq = 
			      new HttpEntity<String>(prj.toString(), httpHeaders);
		ProjectDto projectDto = template.postForObject(addProjectUrl, prjreq, ProjectDto.class);
		assertEquals(projectDto.getVisionId(), visionDto2.getVisionId());
		
		//ビジョンの削除
		String deleteVisionUrl = "http://localhost:" + port + "/visions/"  + visionDto.getId();
		template.delete(deleteVisionUrl, String.class);
		String deleteVisionUrl2 = "http://localhost:" + port + "/visions/"  + visionDto2.getId();
		template.delete(deleteVisionUrl2, String.class);
		/*
		ResponseEntity<HttpStatus> responseEntity2 = template.exchange(
				deleteVisionUrl2,
				    HttpMethod.DELETE,
				    null,
				    HttpStatus.class
				  );
		//$$$$$$$$$ 406 NOT_ACCEPTABLEが返ります。
		System.out.println("$$$$$$$$$ " + responseEntity2.getBody());
		*/
		//個人の削除
		String deleteUrl = "http://localhost:" + port + "/persons/"  + str;
		template.delete(deleteUrl, String.class);
		
	}

}
