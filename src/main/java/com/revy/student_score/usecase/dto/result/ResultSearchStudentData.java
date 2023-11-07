package com.revy.student_score.usecase.dto.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.revy.student_score.common.enums.SchoolType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResultSearchStudentData {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("age")
    private Integer age;
    @JsonProperty("schoolType")
    private SchoolType schoolType;
    @JsonProperty("phoneNumber")
    private String phoneNumber;
}
