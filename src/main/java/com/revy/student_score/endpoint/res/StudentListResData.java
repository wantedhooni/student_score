package com.revy.student_score.endpoint.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.revy.student_score.usecase.dto.result.ResultSearchStudentData;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class StudentListResData {

    @JsonProperty("student")
    private List<ResultSearchStudentData> student = new ArrayList<>();

    public StudentListResData(List<ResultSearchStudentData> student) {
        this.student = student;
    }
}
