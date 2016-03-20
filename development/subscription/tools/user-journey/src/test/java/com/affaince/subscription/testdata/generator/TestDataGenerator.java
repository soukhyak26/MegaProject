package com.affaince.subscription.testdata.generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestDataGenerator {

	private Map<String, List<String>> substituteMap = new HashMap<>();
	private Map<String, List<String>> complimentMap = new HashMap<>();
	
	private List<Product> productList = new ArrayList<>();
	
	public void loadProductData(){
		for (int i = 0; i < 1000; i++) {
			String productId = "product"+i;
			String productName = "productName"+i;
			String categoryId = TestDataUtility.getRandomCaegoryId();
			String subCategoryId = TestDataUtility.getRandomSubCategoryId();
			int quantity = TestDataUtility.getRandomQuantity();
			int quantityUnit = TestDataUtility.getRandomQuantityUnit();
			
			productList.add(new Product(productId, productName, categoryId, subCategoryId, quantity, quantityUnit));
			
		}
		
		loadSubstituteList();
		loadCategoryList();
	}
	
	public void updateSubstituteMap(){
		for (Product product : productList) {
			String key = product.getCategoryId() + product.getSubCategoryId();
			if(this.substituteMap.containsKey(key)){
				substituteMap.get(key).add(product.getProductId());
			}else{
				substituteMap.put(key, new ArrayList<String>());
				substituteMap.get(key).add(product.getProductId());
			}
		}
	}
	
	public void loadSubstituteList(){
		updateSubstituteMap();
		for (Product product : productList) {
			//String key = product.getCategoryId()+product.getSubCategoryId();
			
			List<String> substituteList = substituteMap.get(product.getCategoryId()+product.getSubCategoryId());
			if(substituteList == null){
				substituteList = new ArrayList<>();
			}
			substituteList.remove(product.getProductId());
			
			product.addSubstituteList(substituteList);
			
		}
	}
	
	public void updateCategoryMap(){
		for (Product product : productList) {
			String currentCategoryId = product.getCategoryId();
			
			String key = currentCategoryId;
			if(this.complimentMap.containsKey(key)){
				complimentMap.get(key).add(product.getProductId());
			}else{
				complimentMap.put(key, new ArrayList<String>());
				complimentMap.get(key).add(product.getProductId());
			}
		}
	}
	
	public void loadCategoryList(){
		updateCategoryMap();
		for (Product product : productList) {
			
			List<String> categoryList = complimentMap.get(product.getCategoryId());
			
			if(categoryList == null){
				categoryList = new ArrayList<>();
			}
			categoryList.remove(product.getProductId());
			List<String> substituteList = product.getSubstitute();
			categoryList.removeAll(substituteList);
			Collections.shuffle(categoryList);
			
			for (int i = 0; i < categoryList.size() && i < 4; i++) {
				product.addComplements(categoryList.get(i));
			}
			
		}
	}
	
	public void generateData() throws IOException{
		
		final String SEPARATOR = ",";
		
		File file = new File("resources\\product.csv");
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		
		loadProductData();
		
		for (Product product : productList) {
			
			StringBuffer sb = new StringBuffer();
			sb.append(product.getProductId()+SEPARATOR);
			sb.append(product.getProductName()+SEPARATOR);
			sb.append(product.getCategoryId()+SEPARATOR);
			sb.append(product.getSubCategoryId()+SEPARATOR);
			sb.append(product.getQuantity()+SEPARATOR);
			sb.append(product.getQuantityUnit()+SEPARATOR);
			sb.append("'"+product.getSubstitute()+"'"+SEPARATOR);
			sb.append("'"+product.getComplements()+"'");
			
			writer.write(sb.toString());
			writer.newLine();
		}
		
		writer.flush();
		writer.close();
		
	}
}
