package com.revy.freewheelin.usecase.dto.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ResultScoreAverageStudentData {
    @JsonProperty("averageScore")
    private BigDecimal averageScore;
    @JsonProperty("subjects")
    private List<ResultSearchSubjectsScoreData> subjects = new ArrayList<>();

    @Data
    @NoArgsConstructor
    public static class ResultSearchSubjectsScoreData {
        @JsonProperty("id")
        private Long id;
        @JsonProperty("name")
        private String name;
        @JsonProperty("score")
        private Integer score;
    }
}
