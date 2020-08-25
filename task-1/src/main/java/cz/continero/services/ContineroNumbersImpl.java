package cz.continero.services;


import java.util.Objects;

public class ContineroNumbersImpl implements ContineroNumbers {


	ContineroNumbersImpl() {}

	public String numberToString(int value) {
		Objects.requireNonNull(value);

		boolean divisibilityBy5 = (value % 5) == 0;
		boolean divisibilityBy3 = (value % 3) == 0;

		if (divisibilityBy5 && divisibilityBy3) {
			return "Testing";
		} else if (divisibilityBy5) {
			return "Agile";
		} else if (divisibilityBy3) {
			return "Software";
		} else {
			return String.valueOf(value);
		}
	}
}
