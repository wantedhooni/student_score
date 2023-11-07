package com.revy.student_score.usecase;

import com.revy.student_score.usecase.dto.param.CreateSubjectData;
import com.revy.student_score.usecase.dto.result.ResultSearchSubjectData;

import java.util.List;

public interface SubjectUseCaseV1 {

    /**
     * 과목 목록을 반환한다.
     *
     * @return
     */
    List<ResultSearchSubjectData> searchSubjects();

    /**
     * 과목을 생성한다.
     *
     * @param createSubjectData
     */
    void createSubject(CreateSubjectData createSubjectData);

    /**
     * 요청 과목을 삭제한다.
     *
     * @param subjectId
     */
    void deleteSubject(Long subjectId);
}
