@ignore
Feature: Inventory management

Scenario: Register demand and verification
* call read('classpath:domains/inventory/registerdemand/product-demand-verifier.feature')

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

#Scenario: goods receipt verification
#* call read('classpath:domains/inventory/goodsreceipt/goods-receipt-verifier.feature')

#Scenario: introduce wait time
#* call read('classpath:domains/common/introduce-wait-cycles.feature')

