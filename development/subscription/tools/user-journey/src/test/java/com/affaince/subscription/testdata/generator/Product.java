package com.affaince.subscription.testdata.generator;

import java.util.ArrayList;
import java.util.List;

public class Product {

	private String productId;
	private String productName;
	private String categoryId;
	private String subCategoryId;
	private int quantity;
	private String quantityUnit;
	private List<String> substitute = new ArrayList<>();
	private List<String> complements =  new ArrayList<>();
	private boolean isBranded;
	private int minProfitMargin;
	private int maxProfitMargin;
	private int minPrice;
	private int maxPrice;
	private int minPercentageIncreaseInForecast;
	private int maxPercentageIncreaseInForecast;
	private int actualsAggregationPeriodForTargetForecast=30;
	private int percentageChangeInTrend;
	private List<ProductForecastParameter> forecasts;
	private String generatedProductId;
	private ProductDetails productDetails;


	public Product(String productId, String productName, String categoryId, String subCategoryId) {
		this.productId = productId;
		this.productName = productName;
		this.categoryId = categoryId;
		this.subCategoryId = subCategoryId;
	}
	
	public Product(String productId, String productName, String categoryId, String subCategoryId, int quantity,
				   String quantityUnit) {
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


	public String getQuantityUnit() {
		return quantityUnit;
	}


	public void setQuantityUnit(String quantityUnit) {
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

	public boolean isBranded() {
		return isBranded;
	}

	public void setBranded(boolean branded) {
		isBranded = branded;
	}

	public int getMinProfitMargin() {
		return minProfitMargin;
	}

	public void setMinProfitMargin(int minProfitMargin) {
		this.minProfitMargin = minProfitMargin;
	}

	public int getMaxProfitMargin() {
		return maxProfitMargin;
	}

	public void setMaxProfitMargin(int maxProfitMargin) {
		this.maxProfitMargin = maxProfitMargin;
	}

	public int getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}

	public int getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}

	public int getMinPercentageIncreaseInForecast() {
		return minPercentageIncreaseInForecast;
	}

	public void setMinPercentageIncreaseInForecast(int minPercentageIncreaseInForecast) {
		this.minPercentageIncreaseInForecast = minPercentageIncreaseInForecast;
	}

	public int getMaxPercentageIncreaseInForecast() {
		return maxPercentageIncreaseInForecast;
	}

	public void setMaxPercentageIncreaseInForecast(int maxPercentageIncreaseInForecast) {
		this.maxPercentageIncreaseInForecast = maxPercentageIncreaseInForecast;
	}

	public int getActualsAggregationPeriodForTargetForecast() {
		return actualsAggregationPeriodForTargetForecast;
	}

	public void setActualsAggregationPeriodForTargetForecast(int actualsAggregationPeriodForTargetForecast) {
		this.actualsAggregationPeriodForTargetForecast = actualsAggregationPeriodForTargetForecast;
	}

	public int getPercentageChangeInTrend() {
		return percentageChangeInTrend;
	}

	public void setPercentageChangeInTrend(int percentageChangeInTrend) {
		this.percentageChangeInTrend = percentageChangeInTrend;
	}

	public List<ProductForecastParameter> getForecasts() {
		return forecasts;
	}

	public void setForecasts(List<ProductForecastParameter> forecasts) {
		this.forecasts = forecasts;
	}

    public void setgeneratedProductId(String generatedProductId) {
        this.generatedProductId = generatedProductId;
    }

	public String getGeneratedProductId() {
		return generatedProductId;
	}

	public void setProductDetails(ProductDetails productDetails) {
		this.productDetails = productDetails;
	}

	public ProductDetails getProductDetails() {
		return productDetails;
	}
}
