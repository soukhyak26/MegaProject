#app register --name forecaster --type processor --uri maven://com.affaince:spring-cloud-processor:jar:0.1-SNAPSHOT

stream create forecastingStream --definition "http --port=9000 --spring.cloud.stream.bindings.output.contentType='application/json' | forecaster --spring.cloud.stream.bindings.input.contentType='application/json' | spring-cloud-sink --spring.cloud.stream.bindings.input.contentType='application/json'" --deploy


