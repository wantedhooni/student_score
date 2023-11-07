package com.revy.student_score.model.subject.impl;


import com.revy.student_score.common.enums.ErrorCode;
import com.revy.student_score.common.exception.common.CommonException;
import com.revy.student_score.model.subject.Subject;
import com.revy.student_score.model.subject.SubjectRepo;
import com.revy.student_score.model.subject.SubjectService;
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
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepo repo;


    @Override
    public Optional<Subject> findOneByName(String name) {
        Assert.hasText(name, "name must be not empty");
        return repo.findOneByName(name);
    }

    @Override
    public Subject save(Subject subject) {
        Assert.notNull(subject, "subject must be not null.");
        return repo.save(subject);
    }

    @Override
    public Optional<Subject> findById(Long subjectId) {
        Assert.notNull(subjectId, "subjectId must be not null");
        return repo.findById(subjectId);
    }

    @Override
    public void delete(Subject subject) {
        Assert.notNull(subject, "subject must be not null");
        repo.delete(subject);
    }

    @Override
    public Subject getById(Long subjectId) {
        Assert.notNull(subjectId, "subjectId must be not null");
        return this.findById(subjectId)
                .orElseThrow(() ->
                        new CommonException(ErrorCode.SUBJECT_NOT_FOUND, "과목을 찾을 수 없습니다. [%s]".formatted(subjectId)));
    }
}
