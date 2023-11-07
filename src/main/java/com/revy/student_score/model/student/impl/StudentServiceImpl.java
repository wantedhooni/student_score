package com.revy.student_score.model.student.impl;

import com.revy.student_score.common.enums.ErrorCode;
import com.revy.student_score.common.exception.NotFoundException;
import com.revy.student_score.model.student.Student;
import com.revy.student_score.model.student.StudentRepo;
import com.revy.student_score.model.student.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepo repo;

    @Override
    public Student save(Student student) {
        Assert.notNull(student, "student must be not null.");
        return repo.save(student);
    }

    @Override
    public Optional<Student> findOneByPhoneNumber(String phoneNumber) {
        Assert.hasText(phoneNumber, "Student's phoneNumber must be not empty.");
        return repo.findOneByPhoneNumber(phoneNumber);
    }

    @Override
    public Optional<Student> findById(Long id) {
        Assert.notNull(id, "Student's id must be not null.");
        return repo.findById(id);
    }

    @Override
    public void delete(Student student) {
        Assert.notNull(student, "student must be not null.");
        repo.delete(student);
    }

    @Override
    public Student getById(Long id) {
        Assert.notNull(id, "Student's id must be not null.");
        return findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorCode.SUBJECT_NOT_FOUND, "과목을 찾을 수 없습니다. [%s]".formatted(id)));
    }
}
