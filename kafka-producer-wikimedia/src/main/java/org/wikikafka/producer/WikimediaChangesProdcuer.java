package org.wikikafka.producer;

import com.launchdarkly.eventsource.EventSource;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import com.launchdarkly.eventsource.background.BackgroundEventSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.beans.EventHandler;
import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class WikimediaChangesProdcuer {
    private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaChangesProdcuer.class);

    private KafkaTemplate<String, String> kafkaTemplate;

    public WikimediaChangesProdcuer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topicName) throws InterruptedException {

        // to read real time stream data from wikimedia, we use event source

        BackgroundEventHandler eventHandler = new WikimediaChangeHandler(kafkaTemplate,topicName);
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";
        BackgroundEventSource.Builder builder = new BackgroundEventSource.Builder(eventHandler,
                new EventSource.Builder(URI.create(url)));

        BackgroundEventSource eventSource = builder.build();
        eventSource.start();
        TimeUnit.MINUTES.sleep(10);
    }
}
