package com.rodolfosaturnino.captable;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class LineHandlerTest {

	LineHandler lineHandler;
	
	@Before
	public void init() {
		lineHandler = new LineHandler();
	}
	
	@Test
	public void handleCommentLine() {
		assertFalse(lineHandler.readLine("#",null));
	}
	
	@Test
	public void handleEmptyLine() {
		assertFalse(lineHandler.readLine("", null));
	}
	
	@Test
	public void handleRightInputWithDateNull() {
		assertTrue(lineHandler.readLine("2018-03-20,2000,40000.00,Ann Miura-Ko", "2020-12-08"));
	}
	
	@Test
	public void handleRightInputWithDateAfter() {
		assertTrue(lineHandler.readLine("2018-03-20,2000,40000.00,Ann Miura-Ko", "2020-12-08"));
	}
	
	@Test
	public void handleRightInputWithDateBefore() {
		assertFalse(lineHandler.readLine("2018-03-20,2000,40000.00,Ann Miura-Ko", "2017-03-20"));
	}
}
