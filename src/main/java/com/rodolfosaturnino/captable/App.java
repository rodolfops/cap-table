package com.rodolfosaturnino.captable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import com.rodolfosaturnino.captable.controller.CapTable;
import com.rodolfosaturnino.captable.exception.InvalidFileException;
import com.rodolfosaturnino.captable.exception.NoInputException;

public class App {

	private CapTable capTable;
	
	public App() {
		this.capTable = new CapTable();
	}

	public static void main(String[] args) throws IOException {
		App app = new App();
		app.summary(args);
	}

	public void summary(String[] args) throws IOException {
		if (args.length < 1) {
			throw new NoInputException("Please provide an input!");
		}
		String csv = args[0];
		String date = null;
		if(args.length > 1) {
			date = args[1];
		}
		
		processInput(csv, date);

	}

	private void processInput(String csv, String date) throws IOException {
		FileInputStream inputStream = null;
		Scanner scanner = null;
		try {
			inputStream = new FileInputStream(csv);
		    scanner = new Scanner(inputStream);
		    capTable.readFile(scanner, date);
		    
		    if (scanner.ioException() != null) {
		        throw scanner.ioException();
		    }
		} catch (FileNotFoundException e) {
		    throw new InvalidFileException("Please provide a valid file");
		} finally {
			if (inputStream != null) {
		        inputStream.close();
		    }
			if (scanner != null) {
		        scanner.close();
		    }
		}
	}
}
