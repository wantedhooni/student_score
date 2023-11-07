package com.revy.student_score.model.student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepo extends JpaRepository<Student, Long> {

    Optional<Student> findOneByPhoneNumber(String phoneNumber);
}
