package com.revy.freewheelin.usecase.dto.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.revy.freewheelin.common.enums.SchoolType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Data
public class CreateStudentData {

    @JsonProperty("name")
    @NotEmpty
    private String name;

    @JsonProperty("age")
    @NotEmpty
    @Max(value = 16)
    @Min(value = 8)
    private Integer age;

    @JsonProperty("schoolType")
    @NotEmpty
    private SchoolType schoolType;

    @JsonProperty("phoneNumber")
    @Pattern(regexp = "^01(?:0|1|[6-9])-\\d{3,4}-\\d{4}$", message = "올바른 핸드폰 번호 형식이 아닙니다.")
    @NotEmpty
    private String phoneNumber;

}
