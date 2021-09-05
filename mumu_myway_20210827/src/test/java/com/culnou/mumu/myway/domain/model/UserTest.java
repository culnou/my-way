package com.culnou.mumu.myway.domain.model;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;


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
		String firstName = "111";
		String lastName = "111";
		String email = "111";
		User user = new User(null, firstName, lastName, name, email);
		//実行されない。
		user.fullName();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testInitializeById() {
		String id = "111";
		String firstName = "111";
		String lastName = "111";
		String email = "111";
		User user = new User(id, firstName, lastName, null, email);
		//実行されない。
		user.fullName();
	}

}
