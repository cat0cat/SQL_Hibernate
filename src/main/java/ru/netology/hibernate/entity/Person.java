package ru.netology.hibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@Table(name = "persons")
@AllArgsConstructor
@NoArgsConstructor
@IdClass(PersonId.class)
public class Person {

    @Id
    @Column(nullable = false)
    private String name;

    @Id
    @Column(nullable = false)
    private String surname;

    @Id
    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private int phoneNumber;

    @Column(nullable = false)
    private String cityOfLiving;
}
