#app register --name forecaster --type processor --uri maven://com.affaince:spring-cloud-processor:jar:0.1-SNAPSHOT
app import file:///E:\apps\affaince\development\PredictionEngine\spring-cloud-dataflow-myshell\stream-apps.properties
stream create forecastingStream --definition "http --port=9000 --spring.cloud.stream.kafka.binder.brokers=139.59.92.36:9092 --spring.cloud.stream.kafka.binder.zkNodes=139.59.92.36:2181 --spring.cloud.stream.bindings.output.contentType='application/json' | forecaster --spring.cloud.stream.bindings.input.contentType='application/json' | spring-cloud-sink --spring.cloud.stream.bindings.input.contentType='application/json'"  --deploy


