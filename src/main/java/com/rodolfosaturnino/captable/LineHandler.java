package com.rodolfosaturnino.captable;

import java.util.Scanner;

public class LineHandler {

	private static final String INPUT_PATTERN = "^\\d{4}-\\d{2}-\\d{2},\\d{4},\\d+.\\d+,.*";

	public void readFile(Scanner scanner, String date) {
		while (scanner.hasNext()) {
	        readLine(scanner.nextLine(), date);
	    }
	}

	public boolean readLine(String nextLine, String date) {
		if(nextLine.matches(INPUT_PATTERN)) {
			String[] strings = nextLine.split(",");
			if(date == null || strings[0].compareTo(date) < 1) {
				return true;
			}
		}
		return false;
	}

}
