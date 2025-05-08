package com.example.service;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    public void sendMessage(String message, String partition) {
        kafkaTemplate.send("TOPIC_VINEET", partition, message);
    }

    @Bean
    public NewTopic createTopic() {
        return TopicBuilder.name("TOPIC_VINEET")
                .partitions(3)
                .replicas(1)
                .build();
    }
}
