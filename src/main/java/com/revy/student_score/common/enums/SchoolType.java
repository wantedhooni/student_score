package com.revy.student_score.common.enums;

import lombok.Getter;

@Getter
public enum SchoolType {
    ELEMENTARY("초듷학고"),
    MIDDLE("중학교"),
    HIGH("고등학교");

    private final String description;

    SchoolType(String description) {
        this.description = description;
    }
}
