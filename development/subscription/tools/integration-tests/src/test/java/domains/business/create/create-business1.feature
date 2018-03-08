Feature: create business account for the financial year

Background:
* url demoBaseUrl

Scenario:

Given path 'business/account'
And request read('request.json')
When method post
Then status 200

* def Reader = Java.type('com.affiance.mock.BusinessMongoReader')
* def readerInstance = new Reader()
* def config = { startDate: '2018-01-01', endDate: '2018-12-31'}
* def businessAccountViewList = readerInstance.find(config)
* print 'accounts:' businessAccountViewList..merchantId
* match businessAccountViewList..merchantId == 'merchant1'

