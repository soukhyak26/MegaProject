@ignore
Feature: create product for the financial year
Background:
* url   platformProductUrl


Scenario:
Given url platformProductUrl
And path 'category/name/Large ToothPaste'
When method get
Then status 200
And match response == { categoryId: '#ignore', categoryName: 'Large ToothPaste', description: '#ignore', parentCategoryId: '#ignore' }

* def categoryId = response.parentCategoryId
* def subCategoryId = response.categoryId

Scenario:
Given url platformProductUrl
And path 'product/register'
And request read('classpath:domains/product/create/createproductrequest.json')
When method post
Then status 201

* def productId = response.id
* print "#####productId :" + productId
