package com.rodolfosaturnino.captable.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.rodolfosaturnino.captable.domain.Company;
import com.rodolfosaturnino.captable.domain.Ownership;

public class CapTable {

	private static final String COMMA = ",";
	private static final String INPUT_PATTERN = "^\\d{4}-\\d{2}-\\d{2},\\d{4},\\d+.\\d+,.*";
	Map<String, Ownership> owners = new HashMap<String, Ownership>(); 

	public void readFile(Scanner scanner, String date) {
		while (scanner.hasNext()) {
	        readLine(scanner.nextLine(), date);
	    }
	}

	public boolean readLine(String nextLine, String date) {
		if(nextLine.matches(INPUT_PATTERN)) {
			String[] strings = nextLine.split(COMMA);
			
			if(isPurchasedDateBeforeDueDate(strings[0], date)){
				addNewShareSell(strings[1], strings[2], strings[3]);
				return true;
			}
		}
		return false;
	}

	public Ownership addNewShareSell(String shares, String cash, String name) {
		Long purchasedShares = Long.valueOf(shares);
		BigDecimal cashPaid = new BigDecimal(cash);
		Company.getInstance().sellShares(purchasedShares, cashPaid);
		Ownership owner = null;
		if(owners.containsKey(name)){
			owner = owners.get(name);
			owner.addAnotherBuy(purchasedShares, cashPaid, Company.getInstance().getSharesSold());
		} else {
			owner = new Ownership(name, purchasedShares, cashPaid, purchasedShares);
			owners.put(name, owner);
		}
		return owner;
	}

	private boolean isPurchasedDateBeforeDueDate(String string, String date) {
		LocalDate purchasedDate = LocalDate.parse(string);
		LocalDate dueDate = LocalDate.now(); 
		if(date != null) {
			dueDate = LocalDate.parse(date);
		}
	
		if(!purchasedDate.isAfter(dueDate)) {
				return true;
		}
		return false;
	}

}
