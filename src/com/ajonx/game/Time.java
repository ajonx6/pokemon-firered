package com.ajonx.game;

public class Time {
	// Number of nanoseconds in a second
	public static final long SECOND = 1000000000L;

	// Variable to store time between frames
	private static double delta;

	// Returns current time in nanoseconds
	public static long getTime() {
		return System.nanoTime();
	}
	
	// Set the time measured between frames
	public static void setDelta(double d) {
		delta = d;
	}

	// Returns the time between frames in seconds
	public static double getFrameTimeInSeconds() {
		return delta;
	}
}