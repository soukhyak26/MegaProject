@ignore
Feature: Setting of opening price and verification

Scenario: create opening price
* def productOpeningPriceResult = call read('classpath:domains/product/openingprice/product-openingprice-create.feature')

* print productOpeningPriceResult.response.productId

Scenario: verify opening price
* call read('classpath:domains/product/openingprice/product-openingprice-read.feature')  productOpeningPriceResult.response

