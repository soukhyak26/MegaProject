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
                 };

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

  return config;
  }