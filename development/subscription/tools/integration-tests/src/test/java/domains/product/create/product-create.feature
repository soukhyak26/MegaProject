@ignore
Feature: create product for the financial year

Scenario:
Given url platformProductUrl
And path 'product/category/name/Large ToothPaste'
When method get
Then status 200
And match response == {categoryId:'#notnull', categoryName:'Large ToothPaste', description:'#notnull', parentCategoryId:'#notnull'}

Scenario:
Given url platformProductUrl
And path 'product/register'
And request read('classpath:domains/product/create/createproductrequest.json')
When method post
Then status 201

