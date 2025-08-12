package org.wikikafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.wikikafka.consumer.entities.WikimediaData;
import org.wikikafka.consumer.repository.WikimediaDataRepository;

@Service
public class KafkaDatabaseConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);

    @Autowired
    private WikimediaDataRepository wikimediaDataRepository;
    @KafkaListener(topics = "${spring.kafka.topic.name}",
    groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String eventMessage) {
        LOGGER.info("Received event: {}", eventMessage);
        wikimediaDataRepository.save(new WikimediaData(eventMessage));
    }
}
