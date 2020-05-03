package com.example.producer;

import java.util.Random;

public class Util {

	private Util() {
	}
	
	public static int getRandomNumber(int upperLimit) {
		Random rand = new Random();
		return rand.nextInt(upperLimit);
	}
}
