package com.example.kafkatest.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="person", schema="logs")
@Getter
@Setter
public class Person {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="first")
    private String firstName;

    @Column(name="last")
    private String lastName;

    @Column(name="age")
    private Integer age;

    @Column(name="money")
    private Double money;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", money=" + money +
                '}';
    }
}
