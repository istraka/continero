package cz.continero;


import cz.continero.services.ContineroNumbers;

import java.util.Collections;
import java.util.stream.IntStream;

public class Main {
	public static void main(String[] argv) {
		IntStream.rangeClosed(1,100)
				.boxed()
				.sorted(Collections.reverseOrder())
				.forEach(Main::printContineroNumber);
	}

	public static void printContineroNumber(int value) {
		System.out.println(ContineroNumbers.getService().numberToString(value));
	}
}
