Feature: Create demand file
Scenario:
* call read('classpath:domains/inventory/registerdemand/product-demand-create.feature')

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

#Scenario: Verify demand
#* call read('classpath:domains/inventory/registerdemand/product-demand-read.feature')
