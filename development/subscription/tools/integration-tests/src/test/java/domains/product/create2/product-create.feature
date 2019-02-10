@ignore
Feature: create product for the financial year

Scenario:
Given url productReadUrl
And path 'product/category/name/Edible Oil'
And header Accept = 'application/json'
When method get
Then status 200
And match response == {categoryId:'#notnull', categoryName:'Edible Oil', description:'#notnull', parentCategoryId:null}

* def categoryId1 = response.categoryId
* def subCategoryId1 = response.categoryId

Scenario:
Given url platformProductUrl
And path 'product/register'
And header Accept = 'application/json'
And request read('classpath:domains/product/create2/createproductrequest.json')
When method post
Then status 201

* def productId1 = response.productId
