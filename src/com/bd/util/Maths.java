package com.bd.util;

import java.util.Random;

public class Maths {
	
	private static Random rand = new Random();

	public static double sigmoid(double x) {
		return x / (1 + Math.abs(x));
	}
	
	public static double randDouble(double min, double max) {
		double randomNum = rand.nextDouble() * (max - min) + min;
		return randomNum;
	}

	public static double normalise(double x, double min, double max, double datamin, double datamax) {
		return ((x - datamin) / (datamax - datamin)) * (max - min) + min;
	}

	public static double denormalise(double x, double min, double max) {
		return x * (max - min) + min;
	}
}
