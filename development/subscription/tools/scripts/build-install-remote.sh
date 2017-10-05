#!/usr/bin/env bash
ssh root@139.59.94.173 <<EOF
kill $(ps aux | grep java | grep -v 'grep' | awk '{print $2}')
mvn clean install -f /application/affaince/development/subscription
nohup java -jar /application/affaince/development/subscription/domain-services/product/target/product-0.1-SNAPSHOT.jar &
nohup java -jar /application/affaince/development/subscription/domain-services/benefits/target/benefits-0.1-SNAPSHOT.jar &
EOF