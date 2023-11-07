package com.revy.student_score.model.student;

import com.revy.student_score.common.enums.SchoolType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "student")
@Data
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "age", nullable = false)
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(name = "schoolType", nullable = false)
    private SchoolType schoolType;

    @Column(name = "phoneNumber", nullable = false, unique = true)
    private String phoneNumber;

    @Builder
    public Student(String name, Integer age, SchoolType schoolType, String phoneNumber) {
        this.name = name;
        this.age = age;
        this.schoolType = schoolType;
        this.phoneNumber = phoneNumber;
    }
}
