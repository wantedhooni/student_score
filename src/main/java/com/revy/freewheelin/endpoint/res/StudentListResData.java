package com.revy.freewheelin.endpoint.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.revy.freewheelin.usecase.dto.result.ResultSearchStudentData;
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
