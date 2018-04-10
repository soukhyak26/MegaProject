@ignore
Feature: verify category creation and product creation and configuration on read side

Scenario: create categories
* def result = call read('classpath:domains/product/category/product-category-create.feature')

Scenario: verify productcategory configuration from read side
* def readCategoryResult = call read('classpath:domains/product/category/product-category-read.feature')

Scenario: create product
* def result = call read('classpath:domains/product/create/product-create.feature')
* print result
* def productId = $result.productId
* print "##Global productId: " + productId

Scenario: verify product creation
* def readResult = call read('classpath:domains/product/create/product-read.feature') $result

Scenario: define product configuration
* def configResult = call read('classpath:domains/product/config/product-config-create.feature')

Scenario: verfiy product configuration
* def configReadResult = call read('classpath:domains/product/config/product-config-read.feature') $configResult

