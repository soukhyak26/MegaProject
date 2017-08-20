app register --name http-source --type source --uri maven://com.affaince:spring-cloud-source:jar:0.1-SNAPSHOT

app register --name transform-processor --type processor --uri maven://com.affaince:spring-cloud-processor:jar:0.1-SNAPSHOT

app register --name spring-cloud-sink --type sink --uri maven://com.affaince:spring-cloud-sink:jar:0.1-SNAPSHOT

stream create --definition "http-source --server.port=9080 |transform-processor|spring-cloud-sink" --name affiance-stream

stream deploy --name affiance-stream