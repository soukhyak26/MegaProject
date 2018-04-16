@ignore
Feature: create product for the financial year and verify if it  correctly created

Scenario: create product
* def productCreationResult = call read('classpath:domains/product/create/product-create.feature')

Scenario: verify product creation
* call read('classpath:domains/product/create/product-read.feature') productCreationResult.response
