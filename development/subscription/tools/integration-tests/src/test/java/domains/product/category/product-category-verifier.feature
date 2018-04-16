@ignore
Feature: Category creation and verification

Scenario: create categories
* call read('classpath:domains/product/category/product-category-create.feature')

Scenario: verify productcategory configuration from read side
* call read('classpath:domains/product/category/product-category-read.feature')

