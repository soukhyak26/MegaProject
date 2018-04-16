@ignore
Feature: define product categories

Scenario:
Given url platformProductUrl
And path 'product/category'
And request read('classpath:domains/product/category/categoryrequest1.json')
When method post
Then status 201

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
