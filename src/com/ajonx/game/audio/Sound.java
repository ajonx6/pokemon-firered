package com.ajonx.game.audio;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
	public String path;
	public Clip clip;
	public boolean playing = false;
	public int numLoops = 0;

	public Sound(String path) {
		try {
			File soundFile = new File("res/sounds/" + path + ".wav");
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
			AudioFormat format = ais.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(ais);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	public Sound(Sound copy) {
		this.path = copy.path;
		this.clip = copy.clip;
		this.playing = false;
		this.numLoops = copy.numLoops;
	}

	// Resets the sound and starts it
	public void play() {
		playing = true;
		clip.setFramePosition(0);
		if (numLoops != 0) clip.loop(numLoops);
		clip.start();
	}

	public void stop() {
		playing = false;
		clip.stop();
	}

	public Sound loop() {
		return loop(Clip.LOOP_CONTINUOUSLY);
	}

	public Sound loop(int times) {
		this.numLoops = (times > 0) ? (times - 1) : times;
		return this;
	}

	public int getLength() {
		return clip.getFrameLength();
	}

	public boolean isFinished() {
		return clip.getFramePosition() >= clip.getFrameLength();
	}
}