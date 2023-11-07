package com.revy.student_score.usecase.dto.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ResultScoreAverageSubjectData {
    @JsonProperty("averageScore")
    private BigDecimal averageScore;
    @JsonProperty("students")
    private List<ResultSearchStudentScoreData> students = new ArrayList<>();

    @Data
    @NoArgsConstructor
    public static class ResultSearchStudentScoreData {
        @JsonProperty("id")
        private Long id;
        @JsonProperty("name")
        private String name;
        @JsonProperty("score")
        private Integer score;
    }
}
