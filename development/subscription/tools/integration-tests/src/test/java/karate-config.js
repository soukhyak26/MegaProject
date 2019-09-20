function() {
 karate.configure('connectTimeout', 5000);
  karate.configure('readTimeout', 5000);
  var platform_business_port = karate.properties['domains.business.server.command.port'];
  var read_business_port = karate.properties['domains.business.server.query.port'];

  var platform_product_port = karate.properties['domains.product.server.command.port'];
  var read_product_port = karate.properties['domains.product.server.query.port'];

  var platform_subscriber_port = karate.properties['domains.subscriber.server.command.port'];
  var read_subscriber_port = karate.properties['domains.subscriber.server.query.port'];

  var platform_payments_port = karate.properties['domains.payments.server.command.port'];
  var read_payments_port = karate.properties['domains.payments.server.query.port'];

  var platform_benefits_port = karate.properties['domains.benefits.server.command.port'];
  var read_benefits_port = karate.properties['domains.benefits.server.query.port'];

  var platform_sysdate_port = karate.properties['domains.sysdate.server.command.port'];
  var read_sysdate_port = karate.properties['domains.sysdate.server.query.port'];

  var platform_deliverydispatch_port = karate.properties['domains.deliverydispatch.server.command.port'];
  var read_deliverydispatch_port = karate.properties['domains.deliverydispatch.server.query.port'];

  var env = karate.env;
    if (!env) { env = 'web'; }
  if (!platform_business_port) {
    platform_business_port = karate.env == 'web' ? 8085 : 8080;
  }
  if(!read_business_port){
    read_business_port = karate.env == 'web'? 5060 : 5070;
  }

  if (!platform_product_port) {
    platform_product_port = karate.env == 'web' ? 8082 : 8080;
  }
  if(!read_product_port){
    read_product_port = karate.env == 'web'? 5060 : 5070;
  }

  if (!platform_subscriber_port) {
    platform_subscriber_port = karate.env == 'web' ? 8081 : 8080;
  }
  if(!read_subscriber_port){
    read_subscriber_port = karate.env == 'web'? 5060 : 5070;
  }

  if (!platform_payments_port) {
    platform_payments_port = karate.env == 'web' ? 8086 : 8080;
  }
  if(!read_payments_port){
    read_payments_port = karate.env == 'web'? 5060 : 5070;
  }

  if (!platform_benefits_port) {
    platform_benefits_port = karate.env == 'web' ? 8084 : 8080;
  }
  if(!read_benefits_port){
    read_benefits_port = karate.env == 'web'? 5060 : 5070;
  }

  if (!platform_sysdate_port) {
    platform_sysdate_port = karate.env == 'web' ? 8087 : 8080;
  }
  if(!read_sysdate_port){
    read_sysdate_port = karate.env == 'web'? 5060 : 5070;
  }
  if(!platform_deliverydispatch_port ){
    platform_deliverydispatch_port= karate.env == 'web' ? 9100 : 8080;
  }
  if(!read_deliverydispatch_port ){
    read_deliverydispatch_port = karate.env == 'web'? 5060 : 5070;
  }

/* start - The code for setting global date variables to be used across features/jsons */
/* end - The code for setting global date variables to be used across features/jsons */
  var protocol = 'http';
  var config = { platformBusinessUrl: protocol + '://127.0.0.1:' + platform_business_port,
                 businessReadUrl: protocol + '://127.0.0.1:' + read_business_port,
                 platformProductUrl: protocol + '://127.0.0.1:' + platform_product_port,
                 productReadUrl: protocol + '://127.0.0.1:' + read_product_port,
                 platformSubscriberUrl: protocol + '://127.0.0.1:' + platform_subscriber_port,
                 subscriberReadUrl: protocol + '://127.0.0.1:' + read_subscriber_port,
                 platformPaymentsUrl: protocol + '://127.0.0.1:' + platform_payments_port,
                 paymentsReadUrl: protocol + '://127.0.0.1:' + read_payments_port,
                 platformBenefitsUrl: protocol + '://127.0.0.1:' + platform_benefits_port,
                 benefitsReadUrl: protocol + '://127.0.0.1:' + read_benefits_port,
                 platformSysDateUrl: protocol + '://127.0.0.1:' + platform_sysdate_port,
                 sysDateReadUrl: protocol + '://127.0.0.1:' + read_sysdate_port,
                 platformDeliveryDispatchUrl : protocol + '://127.0.0.1:' + platform_deliverydispatch_port,
                 deliveryDispatchReadUrl :  protocol + '://127.0.0.1:' + read_deliverydispatch_port,

                 };
  var result = karate.callSingle('classpath:domains/common/date.js', config);

  config.businessAccountStartDate = result.unformattedStartOfYearDate();
  config.businessAccountStartDateFormatted = result.formattedStartOfYearString();
  config.businessAccountEndDate = result.unformattedEndOfYearDate();
  config.businessAccountEndDateFormatted = result.formattedEndOfYearString();
  config.businessAccountProvisionDate = result.unformattedStartOfYearDate();
  config.businessYear = result.businessYear();
  var objFirstMonthDate = result.firstDayOfAMonth(config.businessYear,0);

  config.firstMonthForecastStartDate = result.unformattedDateString(objFirstMonthDate);
  config.firstMonthForecastStartDateFormatted = result.formattedDateString(objFirstMonthDate);
  config.firstMonthForecastEndDate = result.unformattedDateString(result.lastDayOfAMonth(config.businessYear,0));
  config.firstMonthForecastEndDateFormatted = result.formattedDateString(result.lastDayOfAMonth(config.businessYear,0));

  config.secondMonthForecastStartDate = result.unformattedDateString(result.firstDayOfAMonth(config.businessYear,1));
  config.secondMonthForecastStartDateFormatted = result.formattedDateString(result.firstDayOfAMonth(config.businessYear,1));
  config.secondMonthForecastEndDate = result.unformattedDateString(result.lastDayOfAMonth(config.businessYear,1));
  config.secondMonthForecastEndDateFormatted = result.formattedDateString(result.lastDayOfAMonth(config.businessYear,1));

  config.thirdMonthForecastStartDate = result.unformattedDateString(result.firstDayOfAMonth(config.businessYear,2));
  config.thirdMonthForecastStartDateFormatted = result.formattedDateString(result.firstDayOfAMonth(config.businessYear,2));
  config.thirdMonthForecastEndDate = result.unformattedDateString(result.lastDayOfAMonth(config.businessYear,2));
  config.thirdMonthForecastEndDateFormatted = result.formattedDateString(result.lastDayOfAMonth(config.businessYear,2));

  config.fourthMonthForecastStartDate = result.unformattedDateString(result.firstDayOfAMonth(config.businessYear,3));
  config.fourthMonthForecastStartDateFormatted = result.formattedDateString(result.firstDayOfAMonth(config.businessYear,3));
  config.fourthMonthForecastEndDate = result.unformattedDateString(result.lastDayOfAMonth(config.businessYear,3));
  config.fourthMonthForecastEndDateFormatted = result.formattedDateString(result.lastDayOfAMonth(config.businessYear,3));

  config.fifthMonthForecastStartDate = result.unformattedDateString(result.firstDayOfAMonth(config.businessYear,4));
  config.fifthMonthForecastStartDateFormatted = result.formattedDateString(result.firstDayOfAMonth(config.businessYear,4));
  config.fifthMonthForecastEndDate = result.unformattedDateString(result.lastDayOfAMonth(config.businessYear,4));
  config.fifthMonthForecastEndDateFormatted = result.formattedDateString(result.lastDayOfAMonth(config.businessYear,4));

  config.sixthMonthForecastStartDate = result.unformattedDateString(result.firstDayOfAMonth(config.businessYear,5));
  config.sixthMonthForecastStartDateFormatted = result.formattedDateString(result.firstDayOfAMonth(config.businessYear,5));
  config.sixthMonthForecastEndDate = result.unformattedDateString(result.lastDayOfAMonth(config.businessYear,5));
  config.sixthMonthForecastEndDateFormatted = result.formattedDateString(result.lastDayOfAMonth(config.businessYear,5));

  config.seventhMonthForecastStartDate = result.unformattedDateString(result.firstDayOfAMonth(config.businessYear,6));
  config.seventhMonthForecastStartDateFormatted = result.formattedDateString(result.firstDayOfAMonth(config.businessYear,6));
  config.seventhMonthForecastEndDate = result.unformattedDateString(result.lastDayOfAMonth(config.businessYear,6));
  config.seventhMonthForecastEndDateFormatted = result.formattedDateString(result.lastDayOfAMonth(config.businessYear,6));

  config.eighthMonthForecastStartDate = result.unformattedDateString(result.firstDayOfAMonth(config.businessYear,7));
  config.eighthMonthForecastStartDateFormatted = result.formattedDateString(result.firstDayOfAMonth(config.businessYear,7));
  config.eighthMonthForecastEndDate = result.unformattedDateString(result.lastDayOfAMonth(config.businessYear,7));
  config.eighthMonthForecastEndDateFormatted = result.formattedDateString(result.lastDayOfAMonth(config.businessYear,7));

  config.ninethMonthForecastStartDate = result.unformattedDateString(result.firstDayOfAMonth(config.businessYear,8));
  config.ninethMonthForecastStartDateFormatted = result.formattedDateString(result.firstDayOfAMonth(config.businessYear,8));
  config.ninethMonthForecastEndDate = result.unformattedDateString(result.lastDayOfAMonth(config.businessYear,8));
  config.ninethMonthForecastEndDateFormatted = result.formattedDateString(result.lastDayOfAMonth(config.businessYear,8));

  config.tenthMonthForecastStartDate = result.unformattedDateString(result.firstDayOfAMonth(config.businessYear,9));
  config.tenthMonthForecastStartDateFormatted = result.formattedDateString(result.firstDayOfAMonth(config.businessYear,9));
  config.tenthMonthForecastEndDate = result.unformattedDateString(result.lastDayOfAMonth(config.businessYear,9));
  config.tenthMonthForecastEndDateFormatted = result.formattedDateString(result.lastDayOfAMonth(config.businessYear,9));

  config.eleventhMonthForecastStartDate = result.unformattedDateString(result.firstDayOfAMonth(config.businessYear,10));
  config.eleventhMonthForecastStartDateFormatted = result.formattedDateString(result.firstDayOfAMonth(config.businessYear,10));
  config.eleventhMonthForecastEndDate = result.unformattedDateString(result.lastDayOfAMonth(config.businessYear,10));
  config.eleventhMonthForecastEndDateFormatted = result.formattedDateString(result.lastDayOfAMonth(config.businessYear,10));

  config.twelvethMonthForecastStartDate = result.unformattedDateString(result.firstDayOfAMonth(config.businessYear,11));
  config.twelvethMonthForecastStartDateFormatted = result.formattedDateString(result.firstDayOfAMonth(config.businessYear,11));
  config.twelvethMonthForecastEndDate = result.unformattedDateString(result.lastDayOfAMonth(config.businessYear,11));
  config.twelvethMonthForecastEndDateFormatted = result.formattedDateString(result.lastDayOfAMonth(config.businessYear,11));

  config.firstPaymentDate = result.unformattedDateString(result.firstDayOfAMonth(config.businessYear,0));
  config.secondPaymentDate = result.unformattedDateString(result.firstDayOfAMonth(config.businessYear,2));
  config.thirdPaymentDate = result.unformattedDateString(result.firstDayOfAMonth(config.businessYear,4));

  karate.log('karate.env =', karate.env);
  karate.log('#####config.platformBusinessUrl =', config.platformBusinessUrl);
  karate.log('#####config.businessReadUrl =', config.businessReadUrl);
  karate.log('#####config.platformProductUrl =', config.platformProductUrl);
  karate.log('#####config.productReadUrl =', config.productReadUrl);
  karate.log('#####config.platformSubscriberUrl =', config.platformSubscriberUrl);
  karate.log('#####config.subscriberReadUrl =', config.subscriberReadUrl);
  karate.log('#####config.platformPaymentsUrl =', config.platformPaymentsUrl);
  karate.log('#####config.paymentsReadUrl =', config.paymentsReadUrl);
  karate.log('#####config.platformBenefitsUrl =', config.platformBenefitsUrl);
  karate.log('#####config.benefitsReadUrl =', config.benefitsReadUrl);
  karate.log('#####config.platformSysDateUrl =', config.platformSysDateUrl);
  karate.log('#####config.sysDateReadUrl =', config.sysDateReadUrl);
  karate.log('#####config.platformDeliveryDispatchUrl =', config.platformDeliveryDispatchUrl);
  karate.log('#####config.deliveryDispatchReadUrl =', config.deliveryDispatchReadUrl);
  karate.log('config.businessAccountStartDate =',config.businessAccountStartDate);
  karate.log('config.businessAccountStartDateFormatted =',config.businessAccountStartDateFormatted);
  karate.log('config.businessAccountEndDate =',config.businessAccountEndDate);
  karate.log('config.businessAccountEndDateFormatted ="',config.businessAccountEndDateFormatted);
  karate.log('config.businessAccountProvisionDate =', config.businessAccountProvisionDate);
  karate.log('config.businessYear =',config.businessYear);
  karate.log('config.firstMonthForecastStartDate = ',config.firstMonthForecastStartDate);
  karate.log('config.firstMonthForecastStartDateFormatted = ',config.firstMonthForecastStartDateFormatted);
  karate.log('config.firstMonthForecastEndDate =',config.firstMonthForecastEndDate);
  karate.log('config.firstMonthForecastEndDateFormatted =',config.firstMonthForecastEndDateFormatted);
  karate.log('config.secondMonthForecastStartDate =',config.secondMonthForecastStartDate);
  karate.log('config.secondMonthForecastStartDateFormatted =',config.secondMonthForecastStartDateFormatted);
  karate.log('config.secondMonthForecastEndDate =',config.secondMonthForecastEndDate);
  karate.log('config.secondMonthForecastEndDateFormatted =',config.secondMonthForecastEndDateFormatted);
  karate.log('config.thirdMonthForecastStartDate =',config.thirdMonthForecastStartDate);
  karate.log('config.thirdMonthForecastStartDateFormatted =', config.thirdMonthForecastStartDateFormatted);
  karate.log('config.thirdMonthForecastEndDate =',config.thirdMonthForecastEndDate);
  karate.log('config.thirdMonthForecastEndDateFormatted =',config.thirdMonthForecastEndDateFormatted);
  karate.log('config.fourthMonthForecastStartDate =', config.fourthMonthForecastStartDate);
  karate.log('config.fourthMonthForecastStartDateFormatted =',config.fourthMonthForecastStartDateFormatted);
  karate.log('config.fourthMonthForecastEndDate =',config.fourthMonthForecastEndDate);
  karate.log('config.fourthMonthForecastEndDateFormatted =',config.fourthMonthForecastEndDateFormatted);
  karate.log('config.fifthMonthForecastStartDate =', config.fifthMonthForecastStartDate);
  karate.log('config.fifthMonthForecastStartDateFormatted =',config.fifthMonthForecastStartDateFormatted);
  karate.log('config.fifthMonthForecastEndDate =',config.fifthMonthForecastEndDate);
  karate.log('config.fifthMonthForecastEndDateFormatted =', config.fifthMonthForecastEndDateFormatted);
  karate.log('config.sixthMonthForecastStartDate =',config.sixthMonthForecastStartDate);
  karate.log('config.sixthMonthForecastStartDateFormatted =',config.sixthMonthForecastStartDateFormatted);
  karate.log('config.sixthMonthForecastEndDate =', config.sixthMonthForecastEndDate);
  karate.log('config.sixthMonthForecastEndDateFormatted =',config.sixthMonthForecastEndDateFormatted);
  karate.log('config.seventhMonthForecastStartDate =', config.seventhMonthForecastStartDate);
  karate.log('config.seventhMonthForecastStartDateFormatted =',config.seventhMonthForecastStartDateFormatted);
  karate.log('config.seventhMonthForecastEndDate =',config.seventhMonthForecastEndDate);
  karate.log('config.seventhMonthForecastEndDateFormatted =',config.seventhMonthForecastEndDateFormatted);
  karate.log('config.eighthMonthForecastStartDate =',config.eighthMonthForecastStartDate);
  karate.log('config.eighthMonthForecastStartDateFormatted =',config.eighthMonthForecastStartDateFormatted);
  karate.log('config.eighthMonthForecastEndDate =',config.eighthMonthForecastEndDate);
  karate.log('config.eighthMonthForecastEndDateFormatted =',config.eighthMonthForecastEndDateFormatted);
  karate.log('config.tenthMonthForecastStartDate =',config.tenthMonthForecastStartDate);
  karate.log('config.tenthMonthForecastStartDateFormatted =',config.tenthMonthForecastStartDateFormatted);
  karate.log('config.tenthMonthForecastEndDate =',config.tenthMonthForecastEndDate);
  karate.log('config.tenthMonthForecastEndDateFormatted =',config.tenthMonthForecastEndDateFormatted);
  karate.log('config.eleventhMonthForecastStartDate =',config.eleventhMonthForecastStartDate);
  karate.log('config.eleventhMonthForecastStartDateFormatted =',config.eleventhMonthForecastStartDateFormatted);
  karate.log('config.eleventhMonthForecastEndDate =',config.eleventhMonthForecastEndDate);
  karate.log('config.eleventhMonthForecastEndDateFormatted =',config.eleventhMonthForecastEndDateFormatted);
  karate.log('config.twelvethMonthForecastStartDate =',config.twelvethMonthForecastStartDate);
  karate.log('config.twelvethMonthForecastStartDateFormatted =',config.twelvethMonthForecastStartDateFormatted);
  karate.log('config.twelvethMonthForecastEndDate =',config.twelvethMonthForecastEndDate);
  karate.log('config.twelvethMonthForecastEndDateFormatted =',config.twelvethMonthForecastEndDateFormatted);
  karate.log('config.firstPaymentDate =',config.firstPaymentDate);
  karate.log('config.secondPaymentDate =',config.secondPaymentDate);
  karate.log('config.thirdPaymentDate =',config.thirdPaymentDate);

  return config;
  }

  function firstDayOfMonth(year,month){
       var targetDate = new Date(year,month,1);
        return targetDate;
  }