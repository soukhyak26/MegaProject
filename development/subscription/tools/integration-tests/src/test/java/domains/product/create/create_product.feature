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


Scenario:
Given url platformProductUrl
And path 'business/account'
And request read('classpath:domains/product/create/createrequest.json')
When method post
Then status 200