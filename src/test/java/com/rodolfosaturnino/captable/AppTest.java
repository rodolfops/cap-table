package com.rodolfosaturnino.captable;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.rodolfosaturnino.captable.exception.InvalidFileException;
import com.rodolfosaturnino.captable.exception.NoInputException;

public class AppTest {
	
	App app;
	
	@Before
	public void init() {
		app = new App();
	}
	
    @Test(expected = NoInputException.class)
    public void testNoInput() throws IOException {
    	String[] input = {};
    	app.summary(input);
    }
    
    @Test(expected = InvalidFileException.class)
    public void testInvalidFileInput() throws IOException {
    	String[] input = {""};
    	app.summary(input);
    }
    
    @Test
    public void testValidFile() throws IOException {
    	String[] input = {"example1.csv"};
    	app.summary(input);
    }
    
}
