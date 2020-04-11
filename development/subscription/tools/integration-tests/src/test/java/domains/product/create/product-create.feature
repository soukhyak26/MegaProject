@ignore
Feature: create product for the financial year

Scenario:
Given url productReadUrl
And path 'product/category/name/Large ToothPaste'
And header Accept = 'application/json'
When method get
Then status 200
And match response == {categoryId:'#notnull', categoryName:'Large ToothPaste', description:'#notnull', parentCategoryId:'#ignore'}

* def categoryId1 = response.categoryId
* def subCategoryId1 = response.categoryId

Scenario:
Given url productReadUrl
And path 'product/category/name/Large Soap'
And header Accept = 'application/json'
When method get
Then status 200
And match response == {categoryId:'#notnull', categoryName:'Large Soap', description:'#notnull', parentCategoryId:'#ignore'}

* def categoryId2 = response.categoryId
* def subCategoryId2 = response.categoryId

Scenario:
Given url platformProductUrl
And path 'product/register'
And header Accept = 'application/json'
And request read('classpath:domains/product/create/createproductrequest.json')
When method post
Then status 201

* def productId1 = response.productId

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Scenario:
Given url platformProductUrl
And path 'product/config/' + productId1
And request read('classpath:domains/product/create/configrequest.json')
When method POST
Then status 201


Scenario:
Given url platformProductUrl
And path 'product/register'
And header Accept = 'application/json'
And request read('classpath:domains/product/create/createproductrequest2.json')
When method post
Then status 201

* def productId2 = response.productId

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Scenario: configure product
Given url platformProductUrl
And path 'product/config/' + productId2
And request read('classpath:domains/product/create/configrequest.json')
When method POST
Then status 201