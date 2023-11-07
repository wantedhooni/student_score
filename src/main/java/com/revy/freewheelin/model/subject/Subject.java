package com.revy.freewheelin.model.subject;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "subject")
@Data
@NoArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    public Subject(String name) {
        this.name = name;
    }
}
