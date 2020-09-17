package com.ajonx.game;

public class Maths {
	public static double linearInterpolate(double s, double e, double a) {
		return s + a * (e - s);
	}
}