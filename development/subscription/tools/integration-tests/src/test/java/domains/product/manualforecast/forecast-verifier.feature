@ignore
Feature: create product  forecast for the financial year and verify if it  correctly created

Scenario: create product
* def productForecastCreationResult = call read('classpath:domains/product/manualforecast/forecast-create.feature')

Scenario: verify product creation
* call read('classpath:domains/product/manualforecast/forecast-read.feature') productForecastCreationResult.response
