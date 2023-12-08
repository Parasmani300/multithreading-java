package com.mtjava.multithreadingjava;

import com.mtjava.multithreadingjava.kafka.GlobalKafkaProducer;
import com.mtjava.multithreadingjava.kafka.ProduceMessageAsync;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class MultiThreadingJavaApplication implements CommandLineRunner {

	@Autowired
	GlobalKafkaProducer globalKafkaProducer;

	public static void main(String[] args) {
		SpringApplication.run(MultiThreadingJavaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String topic = "sales-publisher";
		String key = "Hello";
		String value = "World";
		KafkaProducer<String,String> producer =  globalKafkaProducer.getKafkaProducer();
		ExecutorService executorService = Executors.newFixedThreadPool(3);

		for(int i = 0;i<100;i++){
			executorService.execute(new ProduceMessageAsync(topic,String.valueOf(i),String.valueOf(i),producer));
		}

		executorService.shutdown();

		while (!executorService.isTerminated()){

		}
	}
}
