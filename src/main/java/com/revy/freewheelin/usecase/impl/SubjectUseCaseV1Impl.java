package com.revy.freewheelin.usecase.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.revy.freewheelin.common.enums.ErrorCode;
import com.revy.freewheelin.common.exception.AlreadyExistException;
import com.revy.freewheelin.model.score.ScoreService;
import com.revy.freewheelin.model.subject.QSubject;
import com.revy.freewheelin.model.subject.Subject;
import com.revy.freewheelin.model.subject.SubjectService;
import com.revy.freewheelin.usecase.SubjectUseCaseV1;
import com.revy.freewheelin.usecase.dto.param.CreateSubjectData;
import com.revy.freewheelin.usecase.dto.result.ResultSearchSubjectData;
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
public class SubjectUseCaseV1Impl implements SubjectUseCaseV1 {

    private final JPAQueryFactory jpaQueryFactory;
    private final SubjectService subjectService;
    private final ScoreService scoreService;

    private final QSubject SUBJECT = QSubject.subject;

    @Override
    @Transactional(readOnly = true)
    public List<ResultSearchSubjectData> searchSubjects() {
        return executeSelectQuery();
    }


    @Override
    public void createSubject(CreateSubjectData createSubjectData) {
        Assert.notNull(createSubjectData, "CreateSubjectData must not be null");
        Assert.notNull(createSubjectData.getName(), "CreateSubjectData.name must not be null");

        Optional<Subject> alreadySubject = subjectService.findOneByName(createSubjectData.getName());

        if (alreadySubject.isPresent()) {
            throw new AlreadyExistException(
                    ErrorCode.ALREADY_EXIST_SUBJECT,
                    "이미 존재하는 과목입니다. [%s]".formatted(createSubjectData.getName()));
        }

        subjectService.save(new Subject(createSubjectData.getName()));
    }

    @Override
    public void deleteSubject(Long subjectId) {
        Assert.notNull(subjectId, "subjectId must be not null.");
        Subject targetSubject = subjectService.getById(subjectId);
        scoreService.deleteBySubject(targetSubject);

        subjectService.delete(targetSubject);
    }


    /**
     * SELECT 쿼리를 실행한다.
     * 검색 조건 및 페이징 조건, 정렬조건이 나오면 param으로 조건을 붙인다.
     * 쿼리 빌더 형식으로 사용
     */
    private List<ResultSearchSubjectData> executeSelectQuery() {
        return jpaQueryFactory
                .select(Projections.bean(ResultSearchSubjectData.class
                        , SUBJECT.id.as("id")
                        , SUBJECT.name.as("name")
                ))
                .from(SUBJECT)
                .orderBy(SUBJECT.id.desc())
                .fetch();

        // Where 절 DTO 만들어서 동적 조건 조회 가능
        // Pageable 주입해서 페이징 가닝

    }
}
