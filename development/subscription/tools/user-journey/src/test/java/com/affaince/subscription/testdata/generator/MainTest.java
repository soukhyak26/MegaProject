package com.affaince.subscription.testdata.generator;

import java.io.IOException;

public class MainTest {

	public static void main(String[] args) {
		TestDataGenerator testDataGenerator  = new TestDataGenerator();
		
		try {
			testDataGenerator.generateData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
