@ignore
Feature: Product lifecycle including category definition,product registration, product configuration

Scenario: Category creation and verification
* call read('classpath:domains/product/category/product-category-verifier.feature')

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Scenario: Product creation and verification
* call read('classpath:domains/product/create/product-create-verifier.feature')


Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Scenario: Product forecast creation and verification
* call read('classpath:domains/product/manualforecast/forecast-verifier.feature')

