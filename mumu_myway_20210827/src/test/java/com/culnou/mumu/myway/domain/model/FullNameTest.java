package com.culnou.mumu.myway.domain.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FullNameTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	//バリエーション
	@Test(expected = IllegalArgumentException.class)
	public void testInitializeByNullFirstName() {
		FullName fullName = new FullName(null, "yamada");
		//実行されない。
		fullName.firstName();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testInitializeByEmptyFirstName() {
		FullName fullName = new FullName("", "yamada");
		//実行されない。
		fullName.firstName();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testInitializeByNullLastName() {
		FullName fullName = new FullName("taro", null);
		//実行されない。
		fullName.lastName();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testInitializeByEmptyLastName() {
		FullName fullName = new FullName("taro", "");
		//実行されない。
		fullName.lastName();
	}
	@Test
	public void testInitialize() {
		String firstName = "taro";
		String lastName = "yamada";
		FullName fullName = new FullName(firstName, lastName);
		assertEquals(fullName.firstName(), firstName);
		assertEquals(fullName.lastName(), lastName);
	}
	@Test(expected = IllegalArgumentException.class)
	public void testWithMiddleInitialByNull() {
		String firstName = "taro";
		String lastName = "yamada";
		FullName fullName = new FullName(firstName, lastName);
		fullName.withMiddleInitial(null);
		//実行されない。
		fullName.middle();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testWithMiddleInitialByEmpty() {
		String firstName = "taro";
		String lastName = "yamada";
		FullName fullName = new FullName(firstName, lastName);
		fullName.withMiddleInitial("");
		//実行されない。
		fullName.middle();
	}
	@Test
	public void testWithMiddleInitial() {
		String firstName = "taro";
		String lastName = "yamada";
		String middle = "LLLL";
		FullName fullName = new FullName(firstName, lastName);
		FullName fullNameWithMiddle = fullName.withMiddleInitial(middle);
		assertEquals(fullNameWithMiddle.firstName(), firstName);
		assertEquals(fullNameWithMiddle.lastName(), lastName);
		assertEquals(fullNameWithMiddle.middle(), "L");
	}
	//値オブジェクトの不変性および等価性のテスト。
	@Test
	public void testInvariant() {
		//自身とクローンの等価性検証。
		String firstName = "taro";
		String lastName = "yamada";
		FullName fullName = new FullName(firstName, lastName);
		FullName clone =fullName.clone();
		assertEquals(fullName, clone);
		//副作用のないメソッドの実行
		fullName.firstName();
		//メソッドを実行することによって自身の状態が変化していないか検証。
		assertEquals(fullName, clone);
	}
	@Test
	public void testInvariantWithMiddle() {
		//自身とクローンの等価性検証。
		String firstName = "taro";
		String lastName = "yamada";
		String middle = "LLLL";
		FullName fullName = new FullName(firstName, lastName);
		FullName fullNameWithMiddle = fullName.withMiddleInitial(middle);
		FullName clone =fullNameWithMiddle.clone();
		assertEquals(fullNameWithMiddle, clone);
		//副作用のないメソッドの実行
		fullNameWithMiddle.firstName();
		//メソッドを実行することによって自身の状態が変化していないか検証。
		assertEquals(fullNameWithMiddle, clone);
	}
	//値オブジェクトの交換可能性のテスト。
	@Test
	public void testExchangeability() {
		String firstName1 = "taro";
		String lastName1 = "yamada";
		FullName fullName1 = new FullName(firstName1, lastName1);
		String firstName2 = "taro";
		String lastName2 = "sato";
		FullName fullName2 = new FullName(firstName2, lastName2);
		String firstName3 = "taro";
		String lastName3 = "sato";
		FullName fullName3 = new FullName(firstName3, lastName3);
		assertNotEquals(fullName1, fullName3);
		fullName1 = fullName2;
		assertEquals(fullName1, fullName3);
	}
	@Test
	public void testExchangeabilityWithMiddle() {
		String firstName1 = "taro";
		String lastName1 = "yamada";
		FullName fullName1 = new FullName(firstName1, lastName1);
		String middle1 = "LLL";
		FullName middleName1 = fullName1.withMiddleInitial(middle1);
		
		String firstName2 = "taro";
		String lastName2 = "yamada";
		FullName fullName2 = new FullName(firstName2, lastName2);
		String middle2 = "PLL";
		FullName middleName2 = fullName2.withMiddleInitial(middle2);
		
		String firstName3 = "taro";
		String lastName3 = "yamada";
		FullName fullName3 = new FullName(firstName3, lastName3);
		String middle3 = "PLL";
		FullName middleName3 = fullName3.withMiddleInitial(middle3);
		
		assertNotEquals(middleName1, middleName3);
		middleName1 = middleName2;
		assertEquals(middleName1, middleName3);
	}

}
