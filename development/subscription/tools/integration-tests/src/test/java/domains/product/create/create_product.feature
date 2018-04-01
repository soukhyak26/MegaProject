@ignore
Feature: create product for the financial year
Background:
* url   platformProductUrl


Scenario:
Given url platformProductUrl
And path 'product/category/name/Large ToothPaste'
When method get
Then status 200
And match response == {categoryId:'#notnull', categoryName:'Large ToothPaste', description:'#notnull', parentCategoryId:'#notnull'}

* def categoryId = response.parentCategoryId
* def subCategoryId = response.categoryId

* print "###CategoryId:" + categoryId
* print "####subCategoryId:" + subCategoryId

Scenario:
Given url platformProductUrl
And path 'product/register'
And request read('classpath:domains/product/create/createproductrequest.json')
When method post
Then status 201

* def productId = response.id
* print "#####productId :" + productId
