package com.culnou.mumu.myway.domain.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EmailTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	//バリデーション
	@Test(expected = IllegalArgumentException.class)
	public void testInitializeByNullAddress() {
		String address = null;
		Email email = new Email(address);
		//実行されない。
		email.address();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testInitializeByNoAddress() {
		String address = "";
		Email email = new Email(address);
		//実行されない。
		email.address();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testInitializeByInvalidAddress() {
		String address = "aaaa";
		Email email = new Email(address);
		//実行されない。
		email.address();
	}
	//値オブジェクトの不変性および等価性のテスト。
	@Test
	public void testInvariant() {
		//自身とクローンの等価性検証。
		String address = "aa@aa.com";
		Email email = new Email(address);
		Email clone = email.clone();
		assertEquals(email, clone);
		//副作用のないメソッドの実行
		email.address();
		//メソッドを実行することによって自身の状態が変化していないか検証。
		assertEquals(email, clone);
	}
	//値オブジェクトの交換可能性のテスト。
	@Test
	public void testExchangeability() {
		String address1 = "aa@aa.com";
		Email email1 = new Email(address1);
		String address2 = "bb@aa.com";
		Email email2 = new Email(address2);
		String address3 = "bb@aa.com";
		Email email3 = new Email(address3);
		assertNotEquals(email1, email3);
		email1 = email2;
		assertEquals(email1, email3);
	}

}
