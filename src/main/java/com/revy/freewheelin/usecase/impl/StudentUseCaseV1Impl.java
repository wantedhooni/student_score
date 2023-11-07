package com.revy.freewheelin.usecase.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.revy.freewheelin.common.enums.ErrorCode;
import com.revy.freewheelin.common.exception.AlreadyExistException;
import com.revy.freewheelin.model.score.ScoreService;
import com.revy.freewheelin.model.student.QStudent;
import com.revy.freewheelin.model.student.Student;
import com.revy.freewheelin.model.student.StudentService;
import com.revy.freewheelin.usecase.StudentUseCaseV1;
import com.revy.freewheelin.usecase.dto.param.CreateStudentData;
import com.revy.freewheelin.usecase.dto.result.ResultSearchStudentData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class StudentUseCaseV1Impl implements StudentUseCaseV1 {

    private final JPAQueryFactory jpaQueryFactory;
    private final StudentService studentService;
    private final ScoreService scoreService;

    private final QStudent STUDENT = QStudent.student;

    @Override
    @Transactional(readOnly = true)
    public List<ResultSearchStudentData> searchStudents() {
        return executeSelectQuery();
    }

    @Override
    public void createStudent(CreateStudentData createStudentData) {

        // 1. Data Validation
        Assert.notNull(createStudentData, "CreateStudentData must be not null");
        Assert.notNull(createStudentData.getName(), "Student's name must be not null");
        Assert.notNull(createStudentData.getAge(), "Student's age must be not null");
        Assert.notNull(createStudentData.getSchoolType(), "Student's schoolType must be not null");
        Assert.notNull(createStudentData.getPhoneNumber(), "Student's phoneNumber must be not null");
        //TODO-REVY -> phoneNumber 정규식 Check도 해주자.

        // 2. DB 기준 데이터 Validation
        Optional<Student> alreadyStudent = studentService.findOneByPhoneNumber(createStudentData.getPhoneNumber());
        if (alreadyStudent.isPresent()) {
            throw new AlreadyExistException(
                    ErrorCode.ALREADY_EXIST_STUDENT,
                    "이미 존재하는 학생입니다. [%s]".formatted(createStudentData.getPhoneNumber())
            );
        }

        // 3. 데이터 생성
        studentService.save(Student.builder()
                .name(createStudentData.getName())
                .age(createStudentData.getAge())
                .schoolType(createStudentData.getSchoolType())
                .phoneNumber(createStudentData.getPhoneNumber())
                .build());
    }

    @Override
    public void deleteStudent(Long studentId) {
        Assert.notNull(studentId, "studentId must be not null");
        // 대상 학생 검색
        Student targetStudent = studentService.getById(studentId);

        // Score 에서도 제거 해야 한다.
        scoreService.deleteByStudent(targetStudent);
        // 학생 제거
        studentService.delete(targetStudent);
    }

    /**
     * SELECT 쿼리를 실행한다.
     * 검색 조건 및 페이징 조건, 정렬조건이 나오면 param으로 조건을 붙인다.
     * 쿼리 빌더 형식으로 사용
     */
    private List<ResultSearchStudentData> executeSelectQuery() {
        return jpaQueryFactory
                .select(Projections.bean(ResultSearchStudentData.class
                        , STUDENT.id.as("id")
                        , STUDENT.name.as("name")
                        , STUDENT.age.as("age")
                        , STUDENT.schoolType.as("schoolType")
                        , STUDENT.phoneNumber.as("phoneNumber")
                ))
                .from(STUDENT)
                .orderBy(STUDENT.id.desc())
                .fetch();

        // Where 절 DTO 만들어서 동적 조건 조회 가능
        // Pageable 주입해서 페이징 가닝
    }
}
