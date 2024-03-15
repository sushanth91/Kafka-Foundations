POST     http://localhost:8080/products

{
    
  "title": "iPhone 16 +",
  "price": 9999,
  "quantity":11999
}

KAFKA TOPIC

kafka-topics.bat --create --topic product-created-events-topic --partitions 3 --replication-factor 3 --bootstrap-server localhost:9092 --config min.insync.replicas=2

kafka-topics.bat --describe --bootstrap-server localhost:9092


KAFKA CONSUMER

kafka-console-consumer.bat --topic product-created-events-topic --bootstrap-server localhost:9092

