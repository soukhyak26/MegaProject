@ignore
Feature: Setting of opening price and verification

Scenario: create opening price
* call read('classpath:domains/product/changeprice_noncommitted/product-changeprice.feature')

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Scenario: verify opening price
* call read('classpath:domains/product/changeprice_noncommitted/product-changeprice-read.feature')

