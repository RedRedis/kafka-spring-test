package com.example.kafkatest.service;

import com.example.kafkatest.entities.Person;
import com.example.kafkatest.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

    @Value("${spring.kafka.topic}")
    private String topic;

    private static final Logger logger = LoggerFactory.getLogger(KafkaService.class);
    private final KafkaTemplate<String, Person> kafkaTemplate;
    private final MessageRepository messageRepository;

    @Autowired
    public KafkaService(KafkaTemplate<String, Person> kafkaTemplate, MessageRepository messageRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.messageRepository = messageRepository;
    }

    public void produce(Person person) {
        logger.info("Produce message to kafka: {}", person);
        Message<Person> message = MessageBuilder
                .withPayload(person)
                .setHeader(KafkaHeaders.TOPIC, topic)
                .build();
        kafkaTemplate.send(message);
    }

    @KafkaListener(topics = "${spring.kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(Person person) {
        logger.info("Consume message from kafka: {}", person);
        messageRepository.save(person);
    }
}
