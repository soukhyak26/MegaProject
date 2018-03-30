Feature: verify category creation and product creation and configuration on read side

Scenario: create categories
* def result = call read('classpath:domains/product/category/define_product_category.feature')

Scenario: verify productcategory configuration from read side
* def readResult = call read('classpath:domains/product/category/verify_product_category.feature')

Scenario: create product
* def result = call read('classpath:domains/product/create/create_product.feature')

Scenario: verify product from read side
* def readResult = call read('classpath:domains/product/create/verify_product.feature')