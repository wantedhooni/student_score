package com.revy.freewheelin.model.score;

import com.revy.freewheelin.model.student.Student;
import com.revy.freewheelin.model.subject.Subject;

public interface ScoreService {
    void deleteByStudent(Student student);

    void deleteBySubject(Subject targetSubject);

    Score save(Score score);

    void delete(Score score);
}
