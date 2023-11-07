package com.revy.freewheelin.endpoint.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.revy.freewheelin.usecase.dto.result.ResultSearchSubjectData;
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
