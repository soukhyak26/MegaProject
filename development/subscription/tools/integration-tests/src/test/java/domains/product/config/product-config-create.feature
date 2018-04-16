@ignore
Feature: define product configuration

Scenario: Retrieve desired products  from platform for setting the configuration
Given url platformProductUrl
And path 'product/name/Colgate 300 gms'
When method get
Then status 200
And match response == read('classpath:domains/product/config/retrieveproductresponse1.json')

Scenario:
Given url platformProductUrl
And path 'product/config/' + derivedProductId
And request read('classpath:domains/product/config/configrequest.json')
When method PUT
Then status 200