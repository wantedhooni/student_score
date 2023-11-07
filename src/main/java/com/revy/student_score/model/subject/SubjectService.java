package com.revy.student_score.model.subject;

import java.util.Optional;

public interface SubjectService {
    Optional<Subject> findOneByName(String name);

    Subject save(Subject subject);

    Optional<Subject> findById(Long subjectId);

    void delete(Subject subject);

    Subject getById(Long subjectId);
}
