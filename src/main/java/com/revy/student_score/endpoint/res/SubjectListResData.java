package com.revy.student_score.endpoint.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.revy.student_score.usecase.dto.result.ResultSearchSubjectData;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class SubjectListResData {

    @JsonProperty("subject")
    private List<ResultSearchSubjectData> subject = new ArrayList<>();

    public SubjectListResData(List<ResultSearchSubjectData> subject) {
        this.subject = subject;
    }
}
