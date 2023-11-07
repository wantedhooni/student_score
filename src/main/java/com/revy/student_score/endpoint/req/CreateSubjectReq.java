package com.revy.student_score.endpoint.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.revy.student_score.usecase.dto.param.CreateSubjectData;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class CreateSubjectReq {

    @JsonProperty("subject")
    @NotNull
    private CreateSubjectData subject;
}
