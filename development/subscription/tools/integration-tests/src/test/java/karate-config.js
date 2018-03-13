function() {
 karate.configure('connectTimeout', 5000);
  karate.configure('readTimeout', 5000);
  var port1 = karate.properties['domains.business.server.command.port'];
  var port2 = karate.properties['domains.business.server.query.port'];
  var env = karate.env;
    if (!env) { env = 'web'; }
  if (!port1) {
    port1 = karate.env == 'web' ? 8085 : 8080;
  }
  if(!port2){
    port2 = karate.env == 'web'? 5060 : 5070;
  }
  var protocol = 'http';
  var config = { demoBaseUrl: protocol + '://127.0.0.1:' + port1,
                 demoReadUrl: protocol + '://127.0.0.1:' + port2
                 };

  karate.log('karate.env =', karate.env);
    karate.log('#####config.demoBaseUrl =', config.demoBaseUrl);
    karate.log('#####config.demoReadUrl =', config.demoReadUrl);
  return config;
  }