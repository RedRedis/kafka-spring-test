package com.example.kafkatest.controller;

import com.example.kafkatest.entities.Person;
import com.example.kafkatest.service.KafkaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produce")
public class KafkaController {

    private static final Logger logger = LoggerFactory.getLogger(KafkaController.class);
    private final KafkaService kafkaService;

    @Autowired
    public KafkaController(KafkaService kafkaService) {
        this.kafkaService = kafkaService;
    }

    @PostMapping("/send")
    public ResponseEntity<Person> produceMassage(@RequestBody Person person) {
        logger.info("Received from postman: {}", person);
        kafkaService.produce(person);
        return ResponseEntity.ok().body(person);
    }
}
