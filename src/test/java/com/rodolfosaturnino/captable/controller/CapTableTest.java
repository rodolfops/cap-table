package com.rodolfosaturnino.captable.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.rodolfosaturnino.captable.controller.CapTable;
import com.rodolfosaturnino.captable.domain.Ownership;

public class CapTableTest {

	CapTable cap;
	
	@Before
	public void init() {
		cap = new CapTable();
	}
	
	@Test
	public void handleCommentLine() {
		assertFalse(cap.readLine("#",null));
	}
	
	@Test
	public void handleEmptyLine() {
		assertFalse(cap.readLine("", null));
	}
	
	@Test
	public void handleRightInputWithDateNull() {
		assertTrue(cap.readLine("2018-03-20,2000,40000.00,Ann Miura-Ko", "2020-12-08"));
	}
	
	@Test
	public void handleRightInputWithDateAfter() {
		assertTrue(cap.readLine("2018-03-20,2000,40000.00,Ann Miura-Ko", "2020-12-08"));
	}
	
	@Test
	public void handleRightInputWithDateBefore() {
		assertFalse(cap.readLine("2018-03-20,2000,40000.00,Ann Miura-Ko", "2017-03-20"));
	}
	
	@Test
	public void addNewShareSellWithEmptyList(){
		Ownership owner = this.cap.addNewShareSell("1000", "10000.00", "Sandy Lerner");
		assertEquals(owner.getInvestor(), "Sandy Lerner");
		assertEquals(owner.getShares(), Long.valueOf("1000"));
		assertEquals(owner.getCashPaid().compareTo(new BigDecimal("10000.00")), 0);
	}
	
	@Test
	public void addNewShareSellWithOneElementList(){
		this.cap.addNewShareSell("1000", "10000.00", "Sandy Lerner");
		Ownership owner2 = this.cap.addNewShareSell("1000", "12000.00", "Sandy Lerner");
		assertEquals(owner2.getInvestor(), "Sandy Lerner");
		assertEquals(owner2.getShares(), Long.valueOf("2000"));
		assertEquals(owner2.getCashPaid().compareTo(new BigDecimal("22000.00")), 0);
	}
}
