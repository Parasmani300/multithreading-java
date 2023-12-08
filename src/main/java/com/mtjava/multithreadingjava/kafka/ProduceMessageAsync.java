package com.mtjava.multithreadingjava.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.protocol.types.Field;

import java.util.concurrent.ExecutionException;

public class ProduceMessageAsync implements Runnable{
    String topic;
    String key;
    String value;

    KafkaProducer<String,String> producer;
    public ProduceMessageAsync(String topic, String key, String value,KafkaProducer<String,String> producer) {
        this.topic = topic;
        this.key = key;
        this.value = value;
        this.producer = producer;
    }

    @Override
    public void run() {
        ProducerRecord<String,String> producerRecord = new ProducerRecord<>(topic,key,value);
        producerRecord.headers().add("k1","v1".getBytes());

//        KafkaProducer<String,String> producer =  globalKafkaProducer.getKafkaProducer();
        try {
            RecordMetadata recordMetadata = producer.send(producerRecord).get();
            System.out.println("Message Produced............");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
