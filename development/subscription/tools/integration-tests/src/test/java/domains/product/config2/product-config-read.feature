@ignore
Feature: read product configuration

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Scenario: Retrieve desired products  from platform for setting the configuration
Given url productReadUrl
And path 'product/name/Jemini Refined Oil'
When method get
Then status 200
And match response == read('classpath:domains/product/config2/retrieveproductresponse1.json')

* def derivedProductId1 = response[0].productId

Scenario:
Given url productReadUrl
And path 'product/configuration/' + derivedProductId1
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/config2/readconfigresponse.json')
