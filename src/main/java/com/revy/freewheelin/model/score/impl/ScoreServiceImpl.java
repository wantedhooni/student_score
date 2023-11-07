package com.revy.freewheelin.model.score.impl;

import com.revy.freewheelin.model.score.Score;
import com.revy.freewheelin.model.score.ScoreRepo;
import com.revy.freewheelin.model.score.ScoreService;
import com.revy.freewheelin.model.student.Student;
import com.revy.freewheelin.model.subject.Subject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ScoreServiceImpl implements ScoreService {

    private final ScoreRepo repo;

    @Override
    public void deleteByStudent(Student student) {
        Assert.notNull(student, "student must be not nul..");
        repo.deleteByStudent(student);
    }

    @Override
    public void deleteBySubject(Subject subject) {
        Assert.notNull(subject, "subject must be not nul..");
        repo.deleteBySubject(subject);
    }

    @Override
    public Score save(Score score) {
        Assert.notNull(score, "score must be not nul..");
        return repo.save(score);
    }

    @Override
    public void delete(Score score) {
        Assert.notNull(score, "score must be not nul..");
        repo.delete(score);
    }
}
