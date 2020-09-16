package com.ajonx.game;

public class Time {
	public static final long SECOND = 1000000000L;

	private static double delta;

	public static long getTime() {
		return System.nanoTime();
	}
	
	public static void setDelta(double d) {
		delta = d;
	}

	// Returns the time between frames in seconds
	public static double getFrameTimeInSeconds() {
		return delta;
	}
}