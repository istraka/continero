package cz.continero.services;


public interface ContineroNumbers {
	public static ContineroNumbers getService() {
		return ContineroNumbersImpl.getInstance();
	}

	public String numberToString(int value);
}
