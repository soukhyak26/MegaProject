Feature: test set-up routine which will provide data for comparison

Background:

* url 'http://localhost:5060/business'
* expectedResult = read('request.json')
Given path 'businessaccount'
    When method get
    Then status 200
    And match response[*].startDate contains '2018-01-01'
    And match response[*].endDate contains '2018-12-31'
    And match response[*].merchantId contains 'merchant1'