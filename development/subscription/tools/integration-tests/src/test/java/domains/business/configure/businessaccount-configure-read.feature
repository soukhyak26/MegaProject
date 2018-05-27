@ignore
Feature: read business account configured for the financial year

Background:
* def afterScenario = function(){ karate.log('sleeping ..'); java.lang.Thread.sleep(3000); }

Scenario:
#Introduce delay
* call afterScenario

Given url businessReadUrl
And path 'business/businessaccount/configure'
And header Accept = 'application/json'
When method get
Then status 200
And match response == {"businessAccountId":"#ignore","budgetAdjustmentOptions":"ACCEPT_AUTOMATED_BUDGET_ADJUSTEMENT","fiscalYearConfig":{"startDateOfFinanicalYear":"2018-01-01","endDateOfFinancialYear":"2018-12-31","jodaStartMonthOfFinancialYear":"#ignore","jodEndMonthOfFinancialYear":"#ignore"},"taxAsPercentageOfAnnualRevenue":0.2}
