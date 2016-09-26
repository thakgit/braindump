package com.bd.main;

import com.bd.net.NeuralNet;

public class BrainDump implements Runnable {

	private Thread thread;
	private boolean running = false;

	public static int UPDATES_PER_SEC = 200;

	private NeuralNet net;

	public synchronized void start() {
		if (!running) {
			thread = new Thread(this);
			thread.start();
			running = true;
		}
	}

	public synchronized void stop() {
		if (running) {
			thread.interrupt();
			running = false;
			System.exit(0);
		}
	}

	private void init() {
		net = new NeuralNet();
		net.init();
	}

	@Override
	public void run() {
		init();

		double timePerTick = 1000000000 / UPDATES_PER_SEC;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;

		while (running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;

			if (delta >= 1) {
				net.update();
				delta--;
			}

			if (timer >= 1000000000) {
				timer = 0;
			}
		}

		stop();
	}

	public static void main(String[] args) {
		new BrainDump().start();
	}
}
