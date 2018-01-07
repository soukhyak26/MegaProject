to start zookeeper run command zkserver
to start kafka server run command
GO to E:\apps\kafka_2.11-0.11.0.0
.\bin\windows\kafka-server-start.bat .\config\server.properties

start spring-cloud data flow server with mvn spring-boot:run
start spring cloud dataflow my shell with mvn spring-boot:run

app import file:///E:\apps\affaince\development\PredictionEngine\spring-cloud-dataflow-myshell\stream-apps.properties
stream create forecastingStream --definition "http --port=9000 --spring.cloud.stream.kafka.binder.brokers=localhost:9092 --spring.cloud.stream.kafka.binder.zkNodes=localhost:2181 --spring.cloud.stream.bindings.output.contentType='application/json' | forecaster --spring.cloud.stream.bindings.input.contentType='application/json' | spring-cloud-sink --spring.cloud.stream.bindings.input.contentType='application/json'"  --deploy

