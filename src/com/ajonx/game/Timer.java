package com.ajonx.game;

public class Timer {
	public double timer;
	public double ticks = 0;

	public Timer(double timer) {
		this.timer = timer;
	}

	public boolean tick(double delta) {
		if (ticks >= timer) {
			ticks = 0;
			return true;
		}
		ticks += delta;
		return false;
	}

	public double percent() {
		return ticks / timer;
	}
}