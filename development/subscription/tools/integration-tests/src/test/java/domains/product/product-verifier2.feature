@ignore
Feature: Product lifecycle including category definition,product registration, product configuration

Scenario: Product creation and verification
* call read('classpath:domains/product/create2/product-create-verifier.feature')

Scenario: Product configuration and verification
* call read('classpath:domains/product/config2/product-config-verifier.feature')

Scenario: Product forecast creation and verification
* call read('classpath:domains/product/manualforecast2/forecast-verifier.feature')

Scenario:Product opening price setting and verification
* call read('classpath:domains/product/openingprice2/product-openingprice-verifier.feature')

