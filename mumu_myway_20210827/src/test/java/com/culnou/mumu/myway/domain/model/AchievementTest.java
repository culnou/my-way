package com.culnou.mumu.myway.domain.model;



import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AchievementTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInitializeByNullId(){
		String result = "111";
		Achievement achivement = new Achievement(null, new Date(), result);
		achivement.result();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInitializeByNullExecTime(){
		String result = "111";
		Achievement achivement = new Achievement("", null, result);
		achivement.result();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInitializeByNullResult() {
		Date execTime = new Date();
		Achievement achivement = new Achievement("", execTime, null);
		achivement.result();
	}

}
