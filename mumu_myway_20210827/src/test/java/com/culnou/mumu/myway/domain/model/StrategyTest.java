package com.culnou.mumu.myway.domain.model;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.culnou.mumu.myway.domain.model.Strategy;

public class StrategyTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	//NULLを防いで正しく初期化できる（セッターのテストも兼ねる）
	@Test(expected = IllegalArgumentException.class)
	public void testInitializeByName() {
		String content = "111";
		Strategy strategy = new Strategy(null, content);
		//実行されない。
		strategy.content();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testInitializeByContent() {
		String name = "111";
		Strategy strategy = new Strategy(name, null);
		//実行されない。
		strategy.content();
	}
	
	

}
