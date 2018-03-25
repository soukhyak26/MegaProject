function() {
 karate.configure('connectTimeout', 5000);
  karate.configure('readTimeout', 5000);
  var platform_business_port = karate.properties['domains.business.server.command.port'];
  var read_business_port = karate.properties['domains.business.server.query.port'];
  var env = karate.env;
    if (!env) { env = 'web'; }
  if (!platform_business_port) {
    platform_business_port = karate.env == 'web' ? 8085 : 8080;
  }
  if(!read_business_port){
    read_business_port = karate.env == 'web'? 5060 : 5070;
  }
  var protocol = 'http';
  var config = { platformUrl: protocol + '://127.0.0.1:' + platform_business_port,
                 businessReadUrl: protocol + '://127.0.0.1:' + read_business_port
                 };

  karate.log('karate.env =', karate.env);
    karate.log('#####config.platformUrl =', config.platformUrl);
    karate.log('#####config.businessReadUrl =', config.businessReadUrl);
  return config;
  }