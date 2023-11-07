package com.revy.freewheelin.usecase.dto.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResultSearchSubjectData {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
}
