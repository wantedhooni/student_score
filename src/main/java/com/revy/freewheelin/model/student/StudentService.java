package com.revy.freewheelin.model.student;

import java.util.Optional;

public interface StudentService {


    Student save(Student student);

    Optional<Student> findOneByPhoneNumber(String phoneNumber);

    Optional<Student> findById(Long id);

    void delete(Student student);

    Student getById(Long studentId);
}
