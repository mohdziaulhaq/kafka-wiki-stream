package org.wikikafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootKafkaProducerApplication implements CommandLineRunner {

    @Value("${spring.kafka.topic.name}")
    private String topicName;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootKafkaProducerApplication.class, args);
    }

    @Autowired
    private WikimediaChangesProdcuer wikimediaChangesProdcuer;
    @Override
    public void run(String... args) throws Exception {
        wikimediaChangesProdcuer.sendMessage(topicName);
    }
}