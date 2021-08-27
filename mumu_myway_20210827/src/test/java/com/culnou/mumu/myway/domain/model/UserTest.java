package com.culnou.mumu.myway.domain.model;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.culnou.mumu.myway.domain.model.User;

public class UserTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	//NULLを防いで正しく初期化できる（セッターのテストも兼ねる）
	@Test(expected = IllegalArgumentException.class)
	public void testInitializeByName() {
		String name = "111";
		User user = new User(null, name);
		//実行されない。
		user.name();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testInitializeById() {
		String id = "111";
		User user = new User(id, null);
		//実行されない。
		user.name();
	}

}
