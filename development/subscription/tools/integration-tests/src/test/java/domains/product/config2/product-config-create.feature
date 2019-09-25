@ignore
Feature: define product configuration

Scenario: Retrieve desired products  from platform for setting the configuration
Given url productReadUrl
And path 'product/name/Jemini Refined Oil'
When method get
Then status 200
And match response == read('classpath:domains/product/config2/retrieveproductresponse1.json')

* def derivedProductId1 = response[0].productId

Scenario:
Given url platformProductUrl
And path 'product/config/' + derivedProductId1
And request read('classpath:domains/product/config2/configrequest.json')
When method POST
Then status 201
