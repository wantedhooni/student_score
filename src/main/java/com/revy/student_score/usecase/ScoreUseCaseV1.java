package com.revy.student_score.usecase;

import com.revy.student_score.usecase.dto.result.ResultScoreAverageStudentData;
import com.revy.student_score.usecase.dto.result.ResultScoreAverageSubjectData;

public interface ScoreUseCaseV1 {

    /**
     * Score를 생성한다.
     * @param studentId
     * @param subjectId
     * @param score
     */
    void createScore(Long studentId, Long subjectId, Integer score);

    /**
     * Score를 수정한다.
     * @param studentId
     * @param subjectId
     * @param score
     */
    void modifyScore(Long studentId, Long subjectId, Integer score);

    /**
     * Score를 삭제 한다.
     * @param studentId
     * @param subjectId
     */
    void deleteScore(Long studentId, Long subjectId);

    /**
     * 학생 평균 및 과목 목록을 반환한다.
     * @param studentId
     * @return
     */
    ResultScoreAverageStudentData getAverageScoreByStudentId(Long studentId);

    /**
     * 과목 평균 및 학생 목록을 반환한다.
     * @param subjectId
     * @return
     */
    ResultScoreAverageSubjectData getAverageScoreBySubjectId(Long subjectId);
}
