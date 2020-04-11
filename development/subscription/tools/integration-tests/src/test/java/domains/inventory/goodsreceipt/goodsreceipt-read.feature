@ignore
Feature: Read if  registered products are ordered as per demand

Scenario:
Given url fulfillmentReadUrl
And path '/fulfillment/orders'
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/inventory/goodsreceipt/InventoryInOutRecords.json')