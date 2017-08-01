package loreEngine.utils;

public class Time {

	public static double getTimeNanoSeconds() {
		return System.nanoTime();
	}
	
	public static double getTimeMicroSeconds() {
		return System.nanoTime() / 1000.0;
	}
	
	public static double getTimeMiliSeconds() {
		return System.nanoTime() / 1000000.0;
	}
	
	public static double getTimeSeconds() {
		return System.nanoTime() / 1000000000.0;
	}
	
}
