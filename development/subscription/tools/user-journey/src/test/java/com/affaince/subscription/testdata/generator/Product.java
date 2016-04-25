package com.affaince.subscription.testdata.generator;

import java.util.ArrayList;
import java.util.List;

public class Product {

	private String productId;
	private String productName;
	private String categoryId;
	private String subCategoryId;
	private int quantity;
	private int quantityUnit;
	private List<String> substitute = new ArrayList<>();
	private List<String> complements =  new ArrayList<>();
	
	
	public Product(String productId, String categoryId, String subCategoryId) {
		super();
		this.productId = productId;
		this.categoryId = categoryId;
		this.subCategoryId = subCategoryId;
	}
	
	public Product(String productId, String productName, String categoryId, String subCategoryId, int quantity,
			int quantityUnit) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.categoryId = categoryId;
		this.subCategoryId = subCategoryId;
		this.quantity = quantity;
		this.quantityUnit = quantityUnit;
	}

	public String getProductId() {
		return productId;
	}


	public void setProductId(String productId) {
		this.productId = productId;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}


	public String getSubCategoryId() {
		return subCategoryId;
	}


	public void setSubCategoryId(String subCategoryId) {
		this.subCategoryId = subCategoryId;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public int getQuantityUnit() {
		return quantityUnit;
	}


	public void setQuantityUnit(int quantityUnit) {
		this.quantityUnit = quantityUnit;
	}


	public List<String> getSubstitute() {
		return substitute;
	}


	public void setSubstitute(List<String> substitute) {
		this.substitute = substitute;
	}
	
	public void addSubstitute(String product){
		this.substitute.add(product);
	}
	
	public void addSubstituteList(List<String> productList){
		this.substitute.addAll(productList);
	}

	public List<String> getComplements() {
		return complements;
	}


	public void setComplements(List<String> complements) {
		this.complements = complements;
	}
	
	public void addComplements(String product){
		this.complements.add(product);
	}
	
	public void addComplementList(List<String> productList){
		this.complements.addAll(productList);
	}
	
}