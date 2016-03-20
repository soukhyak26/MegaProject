package com.affaince.subscription.testdata.generator;

public class TestDataUtility {

	private static int[] quantityArray = {0, 10, 20, 35, 50, 100, 150, 250, 500, 750, 1000, 2000, 5000, 10000, 15000, 20000};
	private static int randomWithRange(int min, int max)
	{
	   int range = (max - min) + 1;     
	   return (int)(Math.random() * range) + min;
	}
	
	
	public static String getRandomCaegoryId(){
		
		return "category"+randomWithRange(1, 30);
	}
	
	public static String getRandomSubCategoryId(){
		
		return "subCat"+randomWithRange(1, 4);
	}
	
	public static int getRandomQuantity(){
		
		int i =  quantityArray[randomWithRange(1, 15)];

		return i;
	}
	
	public static int getRandomQuantityUnit(){
		
		return randomWithRange(1, 100);
	}
	
}
