# kafka-sample
zookeeper-server-start.bat "c:\dev\kafka\kafka_2.12-1.1.1\config\zookeeper.properties"
kafka-server-start.bat "c:\dev\kafka\kafka_2.12-1.1.1\config\server.properties"
kafka-console-producer.bat --broker-list 127.0.0.1:9092 --topic myTopic
http://localhost:8080/kafka/send?msg=testesteste