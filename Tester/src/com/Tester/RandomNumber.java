package com.Tester;

	import java.util.Random;

	public class RandomNumber {

		
		public static int RandomInt(int min, int max) {
		    Random rand = new Random();

		    int randomNum = rand.nextInt((max - min) + 1) + min;

		    return randomNum;
		}
	}

