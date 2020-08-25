package cz.continero.services;


public class ContineroNumbersFactory {

	private static ContineroNumbers INSTANCE = null;

	public static ContineroNumbers get() {
		if (INSTANCE == null) {
			INSTANCE = new ContineroNumbersImpl();
		}
		return INSTANCE;
	}
}
