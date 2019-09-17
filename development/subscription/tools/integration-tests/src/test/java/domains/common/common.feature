@ignore
Feature: Caller of common date functions to define all required dates in the lifecycle
Background:
* def result = call read('classpath:domains/common/common-datefunctions.feature')

* def businessAccountStartDate = result.unformattedStartOfYearString()
* print "businessAccountStartDate: " + businessAccountStartDate

* def businessAccountStartDateFormatted = result.formattedStartOfYearString()
* print "businessAccountStartDateFormatted: " + businessAccountStartDateFormatted

* def businessAccountEndDate = result.unformattedEndOfYearString()
* print "businessAccountEndDate: " + businessAccountEndDate

* def businessAccountEndDateFormatted = result.formattedEndOfYearString()
* print "businessAccountEndDateFormatted: " + businessAccountEndDateFormatted

* def businessAccountProvisionDate = businessAccountStartDate
* print "businessAccountProvisionDate: " + businessAccountStartDate

* def businessYear = result.businessYear()
* print "business year: " + businessYear

* def objFirstMonthDate = result.firstDayOfAMonth(businessYear,0)
* def firstMonthForecastStartDate = result.unformattedDateString(objFirstMonthDate)
* print "firstMonthForecastStartDate: " + firstMonthForecastStartDate

* def firstMonthForecastStartDateFormatted = result.formattedDateString(objFirstMonthDate)
* print "firstMonthForecastStartDateFormatted: " + firstMonthForecastStartDateFormatted

* def firstMonthForecastEndDate = result.unformattedDateString(result.lastDayOfAMonth(businessYear,0))
* print "firstMonthForecastEndDate: " + firstMonthForecastEndDate

* def firstMonthForecastEndDateFormatted = result.formattedDateString(result.lastDayOfAMonth(businessYear,0))
* print "firstMonthForecastEndDateFormatted: " + firstMonthForecastEndDateFormatted

* def secondMonthForecastStartDate = result.unformattedDateString(result.firstDayOfAMonth(businessYear,1))
* print "secondMonthForecastStartDate: " + secondMonthForecastStartDate

* def secondMonthForecastStartDateFormatted = result.formattedDateString(result.firstDayOfAMonth(businessYear,1))
* print "secondMonthForecastStartDateFormatted: " + secondMonthForecastStartDateFormatted

* def secondMonthForecastEndDate = result.unformattedDateString(result.lastDayOfAMonth(businessYear,1))
* print "secondMonthForecastEndDate: " + secondMonthForecastEndDate

* def secondMonthForecastEndDateFormatted = result.formattedDateString(result.lastDayOfAMonth(businessYear,1))
* print "secondMonthForecastEndDateFormatted: " + secondMonthForecastEndDateFormatted

* def thirdMonthForecastStartDate = result.unformattedDateString(result.firstDayOfAMonth(businessYear,2))
* print "thirdMonthForecastStartDate: " + thirdMonthForecastStartDate

* def thirdMonthForecastStartDateFormatted = result.formattedDateString(result.firstDayOfAMonth(businessYear,2))
* print "thirdMonthForecastStartDateFormatted: " + thirdMonthForecastStartDateFormatted

* def thirdMonthForecastEndDate = result.unformattedDateString(result.lastDayOfAMonth(businessYear,2))
* print "thirdMonthForecastEndDate: " + thirdMonthForecastEndDate

* def thirdMonthForecastEndDateFormatted = result.formattedDateString(result.lastDayOfAMonth(businessYear,2))
* print "thirdMonthForecastEndDateFormatted: " + thirdMonthForecastEndDateFormatted

* def fourthMonthForecastStartDate = result.unformattedDateString(result.firstDayOfAMonth(businessYear,3))
* print "fourthMonthForecastStartDate: " + fourthMonthForecastStartDate

* def fourthMonthForecastStartDateFormatted = result.formattedDateString(result.firstDayOfAMonth(businessYear,3))
* print "fourthMonthForecastStartDate: " + fourthMonthForecastStartDate

* def fourthMonthForecastEndDate = result.unformattedDateString(result.lastDayOfAMonth(businessYear,3))
* print "fourthMonthForecastEndDate: " + fourthMonthForecastEndDate

* def fourthMonthForecastEndDateFormatted = result.formattedDateString(result.lastDayOfAMonth(businessYear,3))
* print "fourthMonthForecastEndDateFormatted: " + fourthMonthForecastEndDateFormatted

* def fifthMonthForecastStartDate = result.unformattedDateString(result.firstDayOfAMonth(businessYear,4))
* print "fifthMonthForecastStartDate: " + fifthMonthForecastStartDate

* def fifthMonthForecastStartDateFormatted = result.formattedDateString(result.firstDayOfAMonth(businessYear,4))
* print "fifthMonthForecastStartDateFormatted: " + fifthMonthForecastStartDateFormatted

* def fifthMonthForecastEndDate = result.unformattedDateString(result.lastDayOfAMonth(businessYear,5))
* print "fifthMonthForecastEndDate: " + fifthMonthForecastEndDate

* def fifthMonthForecastEndDateFormatted = result.formattedDateString(result.lastDayOfAMonth(businessYear,5))
* print "fifthMonthForecastEndDateFormatted: " + fifthMonthForecastEndDateFormatted

* def sixthMonthForecastStartDate = result.unformattedDateString(result.firstDayOfAMonth(businessYear,5))
* print "sixthMonthForecastStartDate: " + sixthMonthForecastStartDate

* def sixthMonthForecastStartDateFormatted = result.formattedDateString(result.firstDayOfAMonth(businessYear,5))
* print "sixthMonthForecastStartDateFormatted: " + sixthMonthForecastStartDateFormatted

* def sixthMonthForecastEndDate = result.unformattedDateString(result.lastDayOfAMonth(businessYear,5))
* print "sixthMonthForecastEndDate: " + sixthMonthForecastEndDate

* def sixthMonthForecastEndDateFormatted = result.formattedDateString(result.lastDayOfAMonth(businessYear,5))
* print "sixthMonthForecastEndDateFormatted: " + sixthMonthForecastEndDateFormatted

* def seventhMonthForecastStartDate = result.unformattedDateString(result.firstDayOfAMonth(businessYear,6))
* print "seventhMonthForecastStartDate: " + seventhMonthForecastStartDate

* def seventhMonthForecastStartDateFormatted = result.formattedDateString(result.firstDayOfAMonth(businessYear,6))
* print "seventhMonthForecastStartDateFormatted: " + seventhMonthForecastStartDateFormatted

* def seventhMonthForecastEndDate = result.unformattedDateString(result.lastDayOfAMonth(businessYear,6))
* print "seventhMonthForecastEndDate: " + seventhMonthForecastEndDate

* def seventhMonthForecastEndDateFormatted = result.formattedDateString(result.lastDayOfAMonth(businessYear,6))
* print "seventhMonthForecastEndDateFormatted: " + seventhMonthForecastEndDateFormatted

* def eighthMonthForecastStartDate = result.unformattedDateString(result.firstDayOfAMonth(businessYear,7))
* print "eighthMonthForecastStartDate: " + eighthMonthForecastStartDate

* def eighthMonthForecastStartDateFormatted = result.formattedDateString(result.firstDayOfAMonth(businessYear,7))
* print "eighthMonthForecastStartDateFormatted: " + eighthMonthForecastStartDateFormatted

* def eighthMonthForecastEndDate = result.unformattedDateString(result.lastDayOfAMonth(businessYear,7))
* print "eighthMonthForecastEndDate: " + eighthMonthForecastEndDate

* def eighthMonthForecastEndDateFormatted = result.formattedDateString(result.lastDayOfAMonth(businessYear,7))
* print "eighthMonthForecastEndDateFormatted: " + eighthMonthForecastEndDateFormatted

* def ninethMonthForecastStartDate = result.unformattedDateString(result.firstDayOfAMonth(businessYear,8))
* print "ninethMonthForecastStartDate: " + ninethMonthForecastStartDate

* def ninethMonthForecastStartDateFormatted = result.formattedDateString(result.firstDayOfAMonth(businessYear,8))
* print "ninethMonthForecastStartDateFormatted: " + ninethMonthForecastStartDateFormatted

* def ninethMonthForecastEndDate = result.unformattedDateString(result.lastDayOfAMonth(businessYear,8))
* print "ninethMonthForecastEndDate: " + ninethMonthForecastEndDate

* def ninethMonthForecastEndDateFormatted = result.formattedDateString(result.lastDayOfAMonth(businessYear,8))
* print "ninethMonthForecastEndDateFormatted: " + ninethMonthForecastEndDateFormatted

* def tenthMonthForecastStartDate = result.unformattedDateString(result.firstDayOfAMonth(businessYear,9))
* print "tenthMonthForecastStartDate: " + tenthMonthForecastStartDate

* def tenthMonthForecastStartDateFormatted = result.formattedDateString(result.firstDayOfAMonth(businessYear,9))
* print "tenthMonthForecastStartDateFormatted: " + tenthMonthForecastStartDateFormatted

* def tenthMonthForecastEndDate = result.unformattedDateString(result.lastDayOfAMonth(businessYear,9))
* print "tenthMonthForecastEndDate: " + tenthMonthForecastEndDate

* def tenthMonthForecastEndDateFormatted = result.formattedDateString(result.lastDayOfAMonth(businessYear,9))
* print "tenthMonthForecastEndDateFormatted: " + tenthMonthForecastEndDateFormatted

* def eleventhMonthForecastStartDate = result.unformattedDateString(result.firstDayOfAMonth(businessYear,10))
* print "eleventhMonthForecastStartDate: " + eleventhMonthForecastStartDate

* def eleventhMonthForecastStartDateFormatted = result.formattedDateString(result.firstDayOfAMonth(businessYear,10))
* print "eleventhMonthForecastStartDateFormatted: " + eleventhMonthForecastStartDateFormatted

* def eleventhMonthForecastEndDate = result.unformattedDateString(result.lastDayOfAMonth(businessYear,10))
* print "eleventhMonthForecastEndDate: " + eleventhMonthForecastEndDate

* def eleventhMonthForecastEndDateFormatted = result.formattedDateString(result.lastDayOfAMonth(businessYear,10))
* print "eleventhMonthForecastEndDateFormatted: " + eleventhMonthForecastEndDateFormatted

* def twelvethMonthForecastStartDate = result.unformattedDateString(result.firstDayOfAMonth(businessYear,11))
* print "twelvethMonthForecastStartDate: " + twelvethMonthForecastStartDate

* def twelvethMonthForecastStartDateFormatted = result.formattedDateString(result.firstDayOfAMonth(businessYear,11))
* print "twelvethMonthForecastStartDateFormatted: " + twelvethMonthForecastStartDateFormatted

* def twelvethMonthForecastEndDate = result.unformattedDateString(result.lastDayOfAMonth(businessYear,11))
* print "twelvethMonthForecastEndDate: " + twelvethMonthForecastEndDate

* def twelvethMonthForecastEndDateFormatted = result.formattedDateString(result.lastDayOfAMonth(businessYear,11))
* print "twelvethMonthForecastEndDateFormatted: " + twelvethMonthForecastEndDateFormatted

* def firstPaymentDate = result.unformattedDateString(result.firstDayOfAMonth(businessYear,0))
* print "firstPaymentDate: " + firstPaymentDate

* def secondPaymentDate = result.unformattedDateString(result.firstDayOfAMonth(businessYear,2))
* print "secondPaymentDate: " + secondPaymentDate

* def thirdPaymentDate = result.unformattedDateString(result.firstDayOfAMonth(businessYear,4))
* print "thirdPaymentDate: " + thirdPaymentDate


Scenario: This line is required please do not delete - or the functions cannot be called