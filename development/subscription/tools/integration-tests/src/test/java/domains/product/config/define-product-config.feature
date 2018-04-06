@ignore
Feature: define product configuration
Background:
* url   platformProductUrl

Scenario: Retrieve desired products  from platform for setting the configuration
Given url platformProductUrl
And path 'product/name/Colgate 300 gms'
When method get
Then status 200
And match response == call read('classpath:domains/product/config/retrieveproductresponse1.json')

Scenario:
Given url platformProductUrl
And path 'product/config/'+ response.productId
And request read('classpath:domains/product/config/configrequest.json')
When method PUT
Then status 200