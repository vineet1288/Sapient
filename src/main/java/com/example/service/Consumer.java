package com.example.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

@Service
public class Consumer {
    @KafkaListener(topicPartitions = @TopicPartition(
            topic = "TOPIC_VINEET",
            partitions = {"2", "1"}
    ), groupId = "vineet-group")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }
}
