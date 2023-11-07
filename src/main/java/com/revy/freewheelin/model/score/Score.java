package com.revy.freewheelin.model.score;

import com.revy.freewheelin.model.student.Student;
import com.revy.freewheelin.model.subject.Subject;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "score",
        uniqueConstraints =
                {@UniqueConstraint(name = "UK_SUBJECT_STUDENT", columnNames = {"subject_id", "student_id"})
                })
@Data
@NoArgsConstructor
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // TODO: REVY - 그냥 subjectId(Long)로 변경해도 될듯 하다
    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    // TODO: REVY - 그냥 studentId(Long)로 변경해도 될듯 하다
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(name = "score", nullable = false)
    private int score;

    public Score(Subject subject, Student student, int score) {
        this.subject = subject;
        this.student = student;
        this.score = score;
    }
}
