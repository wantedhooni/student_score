package com.revy.student_score.model.score;

import com.revy.student_score.model.student.Student;
import com.revy.student_score.model.subject.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ScoreRepo extends JpaRepository<Score, Long> {

    @Query("DELETE FROM Score score WHERE score.student = :student")
    @Modifying
    void deleteByStudent(@Param("student") Student student);

    @Query("DELETE FROM Score score WHERE score.subject = :subject")
    @Modifying
    void deleteBySubject(@Param("subject") Subject subject);
}
