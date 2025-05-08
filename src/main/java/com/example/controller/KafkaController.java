package com.example.controller;

import com.example.service.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaController {
    @Autowired
    private Producer producer;

    @PostMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam String message, String partition) {
        producer.sendMessage(message, partition);
        return ResponseEntity.ok("Message sent to Kafka: " + message);
    }
}
