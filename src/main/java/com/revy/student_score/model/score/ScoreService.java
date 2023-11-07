package com.revy.student_score.model.score;

import com.revy.student_score.model.student.Student;
import com.revy.student_score.model.subject.Subject;

public interface ScoreService {
    void deleteByStudent(Student student);

    void deleteBySubject(Subject targetSubject);

    Score save(Score score);

    void delete(Score score);
}
