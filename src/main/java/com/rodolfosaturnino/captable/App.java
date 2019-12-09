package com.rodolfosaturnino.captable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rodolfosaturnino.captable.controller.CapTable;
import com.rodolfosaturnino.captable.domain.Company;
import com.rodolfosaturnino.captable.domain.Output;
import com.rodolfosaturnino.captable.domain.Ownership;
import com.rodolfosaturnino.captable.errors.ErrorMessage;
import com.rodolfosaturnino.captable.exception.InvalidFileException;
import com.rodolfosaturnino.captable.exception.NoInputException;

public class App {

	private static final String OUTPUT_FILE = "./output.json";
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
			throw new NoInputException(ErrorMessage.INVALID_INPUT);
		}
		String csv = args[0];
		String date = null;
		if (args.length > 1) {
			date = args[1];
		}
		String output = null;
		if (args.length > 2) {
			output = args[2];
		}

		processInput(csv, date);
		writeOutput(capTable.getOwners(), date, output);

	}

	private void processInput(String csv, String date) throws IOException {
		FileInputStream inputStream = null;
		Scanner scanner = null;
		try {
			inputStream = new FileInputStream(csv);
			scanner = new Scanner(inputStream);
			capTable.readFile(scanner, date, null);

			if (scanner.ioException() != null) {
				throw scanner.ioException();
			}
		} catch (FileNotFoundException e) {
			throw new InvalidFileException(ErrorMessage.VALID_FILE);
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (scanner != null) {
				scanner.close();
			}
		}
	}

	private void writeOutput(Map<String, Ownership> owners, String date,
			String outputFile) {
		if (date == null) {
			date = LocalDate.now().toString();
		}
		Output output = new Output(date, Company.getInstance().getCashRaised(),
				Company.getInstance().getSharesSold());
		output.setOwnership(getOwnersJSON(owners));

		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(new File(OUTPUT_FILE), output);
			String json = mapper.writerWithDefaultPrettyPrinter()
					.writeValueAsString(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private List<Ownership> getOwnersJSON(Map<String, Ownership> owners) {
		List<Ownership> list = new ArrayList<>();
		for (Ownership owner : owners.values()) {
			owner.setOwnership(getOwnership(owner));
			list.add(owner);
		}

		return list;
	}

	private double getOwnership(Ownership owner) {
		double owns = ((double) owner.getShares() / Company.getInstance()
				.getSharesSold()) * 100.00;

		BigDecimal bd = new BigDecimal(owns).setScale(2, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
}
