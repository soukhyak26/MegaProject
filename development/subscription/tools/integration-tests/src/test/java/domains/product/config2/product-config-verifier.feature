@ignore
Feature: Product configuration and verification

Scenario: define product configuration
* call read('classpath:domains/product/config2/product-config-create.feature')

Scenario: verify product configuration
* call read('classpath:domains/product/config2/product-config-read.feature')

