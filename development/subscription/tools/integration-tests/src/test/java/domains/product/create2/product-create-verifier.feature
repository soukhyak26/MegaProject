@ignore
Feature: create product for the financial year and verify if it  correctly created

Scenario: create product
* call read('classpath:domains/product/create2/product-create.feature')

Scenario: verify first product creation
* call read('classpath:domains/product/create2/product-read.feature')
