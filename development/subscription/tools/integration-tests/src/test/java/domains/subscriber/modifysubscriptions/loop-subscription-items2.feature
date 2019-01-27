@ignore
Feature: loop through each product and get its latest priceBuckets

Scenario:read subscriptionview
Given url productReadUrl
And path 'product/pricebuckets/latest/' + productId
And header Accept = 'application/json'
When method get
Then status 200