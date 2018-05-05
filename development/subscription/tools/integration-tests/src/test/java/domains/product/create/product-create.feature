@ignore
Feature: create product for the financial year

Scenario:
Given url platformProductUrl
And path 'product/category/name/Large ToothPaste'
And header Accept = 'application/json'
When method get
Then status 200
And match response == {categoryId:'#notnull', categoryName:'Large ToothPaste', description:'#notnull', parentCategoryId:'#ignore'}

* def categoryId = response.categoryId
* def subCategoryId = response.categoryId

Scenario:
Given url platformProductUrl
And path 'product/register'
And header Accept = 'application/json'
And request read('classpath:domains/product/create/createproductrequest.json')
When method post
Then status 201

