@ignore
Feature: Product lifecycle including category definition,product registration, product configuration

Scenario: Category creation and verification
* call read('classpath:domains/product/category/product-category-verifier.feature')

Scenario: Product creation and verification
* call read('classpath:domains/product/create/product-create-verifier.feature')

Scenario: Product configuration and verification
* call read('classpath:domains/product/config/product-config-verifier.feature')
