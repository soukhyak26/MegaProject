@ignore
Feature: define product configuration

Scenario: Retrieve desired products  from platform for setting the configuration
Given url productReadUrl
And path 'product/name/Colgate 300 gms'
When method get
Then status 200
And match response == read('classpath:domains/product/config/retrieveproductresponse1.json')

* def derivedProductId1 = response[0].productId

Scenario: Retrieve desired products  from platform for setting the configuration
Given url productReadUrl
And path 'product/name/Lux 200 gms'
When method get
Then status 200
And match response == read('classpath:domains/product/config/retrieveproductresponse2.json')
* def derivedProductId2 = response[0].productId

Scenario:
Given url platformProductUrl
And path 'product/config/' + derivedProductId1
And request read('classpath:domains/product/config/configrequest.json')
When method PUT
Then status 200

Scenario:
Given url platformProductUrl
And path 'product/config/' + derivedProductId2
And request read('classpath:domains/product/config/configrequest.json')
When method PUT
Then status 200