package com.revy.student_score.usecase.dto.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CreateSubjectData {

    @JsonProperty("name")
    @NotEmpty
    private String name;
}
