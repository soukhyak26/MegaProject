@ignore
Feature: Setting of opening price and verification

Scenario: create opening price
* call read('classpath:domains/product/openingprice/product-openingprice-create.feature')

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Scenario: verify opening price
* call read('classpath:domains/product/openingprice/product-openingprice-read.feature')
