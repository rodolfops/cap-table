package com.rodolfosaturnino.captable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.rodolfosaturnino.captable.exception.InvalidFileException;
import com.rodolfosaturnino.captable.exception.NoInputException;

public class App {

	private LineHandler lineHandler;
	
	public App() {
		this.lineHandler = new LineHandler();
	}

	public static void main(String[] args) {
		App app = new App();
		app.summary(args);
	}

	public void summary(String[] args) {
//		System.out.println(args.length);
		if (args.length < 1) {
			throw new NoInputException("Please provide an input!");
		}
		String csv = args[0];
		String date = null;
		if(args.length > 1) {
			date = args[1];
		}
		Scanner scanner = null;
		try {
		    scanner = new Scanner(new File(csv));
		    lineHandler.readFile(scanner, date);
		} catch (FileNotFoundException e) {
		    throw new InvalidFileException("Please provide a valid file");
		} finally {
		    if (scanner != null) {
		        scanner.close();
		    }
		}

	}
}
