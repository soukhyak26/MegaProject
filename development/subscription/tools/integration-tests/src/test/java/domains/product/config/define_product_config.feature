@ignore
Feature: define product configuration
Background:
* url   platformProductUrl

* print platformProductUrl

Scenario:
Given url platformProductUrl
And path 'product/config/'
And request read('classpath:domains/product/category/categoryrequest1.json')
When method post
Then status 201

* def parentId1 = response.id

* print "#####parent ID :" + parentId1

Scenario:
Given url platformProductUrl
And path 'product/category'
And request read('classpath:domains/product/category/childcategoryrequest11.json')
When method post
Then status 201

Scenario:
Given url platformProductUrl
And path 'product/category'
And request read('classpath:domains/product/category/childcategoryrequest12.json')
When method post
Then status 201

Scenario:
Given url platformProductUrl
And path 'product/category'
And request read('classpath:domains/product/category/childcategoryrequest13.json')
When method post
Then status 201


Scenario:
Given url platformProductUrl
And path 'product/category'
And request read('classpath:domains/product/category/categoryrequest2.json')
When method post
Then status 201

* def parentId2 = response.id
* print "#####parent ID2 :" + parentId2

Scenario:
Given url platformProductUrl
And path 'product/category'
And request read('classpath:domains/product/category/childcategoryrequest21.json')
When method post
Then status 201

Scenario:
Given url platformProductUrl
And path 'product/category'
And request read('classpath:domains/product/category/childcategoryrequest22.json')
When method post
Then status 201

Scenario:
Given url platformProductUrl
And path 'product/category'
And request read('classpath:domains/product/category/childcategoryrequest23.json')
When method post
Then status 201
