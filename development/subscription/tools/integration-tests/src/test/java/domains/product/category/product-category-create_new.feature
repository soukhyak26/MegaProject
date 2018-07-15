@ignore
Feature: define product categories
* def fun = read('classpath:domains/common/loop.js')
Scenario:
* def response = fun('classpath:domains/product/category/product-category-create1.feature', 'classpath:domains/product/category/categoryrequest_total.json')


