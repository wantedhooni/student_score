package com.revy.freewheelin.endpoint.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ScoreReq {

    @JsonProperty("score")
    @NotNull
    @Max(100)
    @Min(0)
    private Integer score;
}
