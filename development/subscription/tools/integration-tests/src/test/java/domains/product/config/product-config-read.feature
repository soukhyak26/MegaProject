@ignore
Feature: read product configuration

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Scenario:
Given url productReadUrl
And path 'product/configuration/' + __arg.productId
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/config/readconfigresponse.json')