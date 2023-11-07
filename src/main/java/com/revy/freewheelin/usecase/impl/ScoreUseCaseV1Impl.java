package com.revy.freewheelin.usecase.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.revy.freewheelin.common.enums.ErrorCode;
import com.revy.freewheelin.common.exception.AlreadyExistException;
import com.revy.freewheelin.common.exception.NotFoundException;
import com.revy.freewheelin.model.score.QScore;
import com.revy.freewheelin.model.score.Score;
import com.revy.freewheelin.model.score.ScoreService;
import com.revy.freewheelin.model.student.QStudent;
import com.revy.freewheelin.model.student.Student;
import com.revy.freewheelin.model.student.StudentService;
import com.revy.freewheelin.model.subject.QSubject;
import com.revy.freewheelin.model.subject.Subject;
import com.revy.freewheelin.model.subject.SubjectService;
import com.revy.freewheelin.usecase.ScoreUseCaseV1;
import com.revy.freewheelin.usecase.dto.result.ResultScoreAverageStudentData;
import com.revy.freewheelin.usecase.dto.result.ResultScoreAverageSubjectData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ScoreUseCaseV1Impl implements ScoreUseCaseV1 {

    private final JPAQueryFactory jpaQueryFactory;
    private final ScoreService scoreService;
    private final StudentService studentService;
    private final SubjectService subjectService;


    private final QScore SCORE = QScore.score1;
    private final QSubject SUBJECT = QSubject.subject;
    private final QStudent STUDENT = QStudent.student;


    @Override
    public void createScore(Long studentId, Long subjectId, Integer score) {
        Assert.notNull(studentId, "studentId must be not null.");
        Assert.notNull(subjectId, "subjectId must be not null.");
        Assert.notNull(score, "score must be not null.");

        // 대상 학생 검색
        Student targetStudent = studentService.getById(studentId);

        // 대상 과목 검색
        Subject targetSubject = subjectService.getById(subjectId);

        Score scoreEntity = findOne(targetSubject, targetStudent);
        if (scoreEntity != null) {
            throw new AlreadyExistException(ErrorCode.ALREADY_EXIST_SCORE,
                    "%s[ID:%s]의 ID:%s 학생의 점수는 이미 존재합니다.".formatted(targetSubject.getName(), targetSubject.getId(), targetStudent.getId()));
        }

        scoreService.save(new Score(targetSubject, targetStudent, score));
    }

    // TODO:REVY - createScore와 modifyScore 로직 합칠수 있을거 같지만.. 찢어놓는게 요구사항 관리할때 좋을거 같다.
    @Override
    public void modifyScore(Long studentId, Long subjectId, Integer score) {
        Assert.notNull(studentId, "studentId must be not null.");
        Assert.notNull(subjectId, "subjectId must be not null.");
        Assert.notNull(score, "score must be not null.");

        // 대상 학생 검색
        Student targetStudent = studentService.getById(studentId);

        // 대상 과목 검색
        Subject targetSubject = subjectService.getById(subjectId);

        Score scoreEntity = getOne(targetSubject, targetStudent);

        // modify Score
        scoreEntity.setScore(score);
        scoreService.save(scoreEntity);
    }

    @Override
    public void deleteScore(Long studentId, Long subjectId) {
        Assert.notNull(studentId, "studentId must be not null.");
        Assert.notNull(subjectId, "subjectId must be not null.");

        // 대상 학생 검색
        Student targetStudent = studentService.getById(studentId);

        // 대상 과목 검색
        Subject targetSubject = subjectService.getById(subjectId);

        Score scoreEntity = getOne(targetSubject, targetStudent);
        scoreService.delete(scoreEntity);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ)
    public ResultScoreAverageStudentData getAverageScoreByStudentId(Long studentId) {
        // 대상 학생 검색
        Student targetStudent = studentService.getById(studentId);

        ResultScoreAverageStudentData result = new ResultScoreAverageStudentData();
        result.setAverageScore(getAverageScore(studentId, null));
        result.setSubjects(searchSubjectScoreByStudentId(studentId));
        return result;
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ)
    public ResultScoreAverageSubjectData getAverageScoreBySubjectId(Long subjectId) {
        // 대상 과목 검색
        Subject targetSubject = subjectService.getById(subjectId);

        ResultScoreAverageSubjectData result = new ResultScoreAverageSubjectData();
        result.setAverageScore(getAverageScore(null, subjectId));
        result.setStudents(searchStudentScoreBySubjectId(subjectId));
        return result;
    }

    private List<ResultScoreAverageStudentData.ResultSearchSubjectsScoreData> searchSubjectScoreByStudentId(Long studentId) {
        Assert.notNull(studentId, "subjectId must be not null.");
        return jpaQueryFactory
                .select(Projections.bean(ResultScoreAverageStudentData.ResultSearchSubjectsScoreData.class,
                        SUBJECT.id.as("id"),
                        SUBJECT.name.as("name"),
                        SCORE.score.as("score").nullif(-1)
                ))
                .from(SUBJECT)
                .leftJoin(SCORE)
                .where(SCORE.student.id.eq(studentId))
                .orderBy(SUBJECT.id.asc())
                .fetch();
    }

    private List<ResultScoreAverageSubjectData.ResultSearchStudentScoreData> searchStudentScoreBySubjectId(Long subjectId) {
        Assert.notNull(subjectId, "subjectId must be not null.");
        return jpaQueryFactory
                .select(Projections.bean(ResultScoreAverageSubjectData.ResultSearchStudentScoreData.class,
                        STUDENT.id.as("id"),
                        STUDENT.name.as("name"),
                        SCORE.score.as("score")
                ))
                .from(SCORE)
                .innerJoin(STUDENT).on(SCORE.student.id.eq(STUDENT.id))
                .where(SCORE.subject.id.eq(subjectId))
                .orderBy(STUDENT.id.asc())
                .fetch();
    }


    /**
     * 주어진 조건의 평균 Score를 반환한다.
     *
     * @param studentId
     * @param subjectId
     * @return
     */
    private BigDecimal getAverageScore(Long studentId, Long subjectId) {
        BooleanBuilder where = new BooleanBuilder();
        where.and(studentId == null ? null : SCORE.student.id.eq(studentId));
        where.and(subjectId == null ? null : SCORE.subject.id.eq(subjectId));

        Double average = jpaQueryFactory
                .select(SCORE.score.avg())
                .from(SCORE)
                .where(where).fetchFirst();

        if (average == null) {
            return BigDecimal.valueOf(-1);
        }
        return BigDecimal.valueOf(average);
    }


    private Score findOne(Subject subject, Student student) {
        return jpaQueryFactory
                .selectFrom(SCORE)
                .join(SUBJECT).fetchJoin()
                .join(STUDENT).fetchJoin()
                .where(
                        SCORE.subject.eq(subject)
                                .and(SCORE.student.eq(student))
                ).fetchFirst();
    }

    private Score getOne(Subject subject, Student student) {
        Score scoreEntity = findOne(subject, student);
        if (scoreEntity == null) {
            throw new NotFoundException(ErrorCode.SCORE_NOT_FOUND,
                    "%s[ID:%s]의 ID:%s 학생의 점수를 찾을수 없습니다.".formatted(subject.getName(), subject.getId(), student.getId()));
        }

        return scoreEntity;
    }

}
