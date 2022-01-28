package com.example.kafkatest.service;

import com.example.kafkatest.entities.Message;
import com.example.kafkatest.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

    @Value("${spring.kafka.topic}")
    private String topic;

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final MessageRepository messageRepository;

    @Autowired
    public KafkaService(KafkaTemplate<String, String> kafkaTemplate, MessageRepository messageRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.messageRepository = messageRepository;
    }

    public void produce(String msg) {
        kafkaTemplate.send(topic, msg);
    }

    @KafkaListener(topics = "${spring.kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String msg) {
        messageRepository.save(new Message(msg));
    }
}
