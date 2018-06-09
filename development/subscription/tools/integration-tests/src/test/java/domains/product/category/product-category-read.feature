@ignore
Feature: read product categories configured

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

* def expectedResponse = read('classpath:domains/product/category/product_categories_response.json')

Scenario:
Given url productReadUrl
And path 'product/categories'
And header Accept = 'application/json'
When method get
Then status 200
And match response == expectedResponse
