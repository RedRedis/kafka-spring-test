package com.example.kafkatest.entities;

import javax.persistence.*;

@Entity
@Table(name="postman", schema="logs")
public class Message {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name="value")
    String value;

    public Message() {
    }

    public Message(String value) {
        this.value = value;
    }
}
