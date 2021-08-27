package com.culnou.mumu.myway.controller;

import static org.junit.Assert.*;


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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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

	@Test
	public void testAssignPerson() throws Exception{
		JSONObject user = new JSONObject();
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        String name = "test name";
		user.put("id", str);
		user.put("name", name);
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
		assertEquals(personDto.getName(), name);
	}

}
