package com.mtjava.multithreadingjava.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class GlobalKafkaProducer {
    KafkaProducer<String,String> kafkaProducer;

    public GlobalKafkaProducer() {
    }

    public KafkaProducer<String,String> getKafkaProducer()
    {
        Properties props  = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.ACKS_CONFIG,"all");

        kafkaProducer = new KafkaProducer<>(props);
        return kafkaProducer;
    }
}
