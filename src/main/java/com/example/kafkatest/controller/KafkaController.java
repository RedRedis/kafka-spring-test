package com.example.kafkatest.controller;

import com.example.kafkatest.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produce")
public class KafkaController {

    @Autowired
    KafkaService kafkaService;

    @PostMapping("/send")
    public ResponseEntity<String> produceMassage(@RequestBody String msg) {
        kafkaService.produce(msg);
        return ResponseEntity.ok().body("Message delivered:\n" + msg);
    }
}
