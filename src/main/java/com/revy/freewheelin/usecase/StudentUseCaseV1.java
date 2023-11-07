package com.revy.freewheelin.usecase;

import com.revy.freewheelin.usecase.dto.param.CreateStudentData;
import com.revy.freewheelin.usecase.dto.result.ResultSearchStudentData;

import java.util.List;

public interface StudentUseCaseV1 {
    /**
     * 학생 목록을 반한환다.
     *
     * @return
     */
    List<ResultSearchStudentData> searchStudents();


    /**
     * Student를 생성한다.
     *
     * @param createStudentData
     */
    void createStudent(CreateStudentData createStudentData);

    /**
     * 요청 ID의 학생과 Score를 삭제한다.
     *
     * @param studentId
     */
    void deleteStudent(Long studentId);
}
