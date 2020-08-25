package cz.continero.services;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ContineroCounterTestCase {

	@Test
	public void divisibleBy3() {
		Assertions.assertEquals("Software", ContineroNumbersFactory.get().numberToString(9), "9 is divisble by 3");
	}

	@Test
	public void divisibleBy5() {
		Assertions.assertEquals("Agile", ContineroNumbersFactory.get().numberToString(25), "5 is divisble by 5");
	}

	@Test
	public void divisibleBy3and5() {
		Assertions.assertEquals("Testing", ContineroNumbersFactory.get().numberToString(45), "45 is divisble by 3 and 5");
	}

	@ParameterizedTest
	@MethodSource("getRange")
	public void range(int value) {


		boolean divisibilityBy5 = (value % 5) == 0;
		boolean divisibilityBy3 = (value % 3) == 0;
		String expected;
		if (divisibilityBy5 && divisibilityBy3) {
			expected = "Testing";
		} else if (divisibilityBy5) {
			expected = "Agile";
		} else if (divisibilityBy3) {
			expected = "Software";
		} else {
			expected = String.valueOf(value);
		}
		Assertions.assertEquals(expected, ContineroNumbersFactory.get().numberToString(value));
	}

	private static Stream<Integer> getRange() {
		return IntStream.range(-100, 101).boxed();
	}
}
