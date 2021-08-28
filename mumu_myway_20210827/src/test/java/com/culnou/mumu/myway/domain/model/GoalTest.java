package com.culnou.mumu.myway.domain.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class GoalTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetIndicator() {
		String indicator = null;
		String criteria = "111";
		Goal mesurement = new Goal(indicator, criteria);
		//実行されない。
		mesurement.indicator();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testSetCriteria() {
		String indicator = "111";
		String criteria = null;
		Goal mesurement = new Goal(indicator, criteria);
		//実行されない。
		mesurement.indicator();
	}
	@Test
	public void testInvariant() {
		//自身とクローンの等価性検証。
		Goal measurement = new Goal("111", "111");
		Goal clone = measurement.clone();
		assertEquals(measurement, clone);
		//副作用のないメソッドの実行
		System.out.println(measurement.indicator());
		//メソッドを実行することによって自身の状態が変化していないか検証。
		assertEquals(measurement, clone);
	}
	@Test
	public void testEquality() {
		Goal mesurement1 = new Goal("111", "111");
		Goal mesurement2 = new Goal("111", "111");
		assertEquals(mesurement1, mesurement2);
	}
	@Test
	public void testExchangeability() {
		Goal mesurement1 = new Goal("111", "111");
		Goal mesurement2 = new Goal("222", "222");
		Goal mesurement3 = new Goal("222", "222");
		assertNotEquals(mesurement1, mesurement3);
		mesurement1 = mesurement2;
		assertEquals(mesurement1, mesurement3);
	}

}
