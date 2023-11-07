package com.revy.student_score.common.enums;

import lombok.Getter;

@Getter
public enum ErrorCode {
    ALREADY_EXIST_STUDENT("ALREADY_EXIST_STUDENT", "이미 존재하는 학생입니다."),
    ALREADY_EXIST_SUBJECT("ALREADY_EXIST_SUBJECT", "이미 존재하는 과목입니다."),
    ALREADY_EXIST_SCORE("ALREADY_EXIST_SCORE", "해당과목의 학생 점수는 이미 존재합니다."),
    STUDENT_NOT_FOUND("STUDENT_NOT_FOUND", "학생을 찾을 수 없습니다."),
    SUBJECT_NOT_FOUND("SUBJECT_NOT_FOUND", "과목을 찾을 수 없습니다."),
    SCORE_NOT_FOUND("SUBJECT_NOT_FOUND", "해당과목의 학생 점수를 찾을 수 없습니다."),
    BAD_REQUEST("BAD_REQUEST", "잘못된 리퀘스트 요청입니다."),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "알수없는 에러가 발생하였습니다."),

    ;

    private final String code;
    private final String defaultMessage;

    ErrorCode(String code, String defaultMessage) {
        this.code = code;
        this.defaultMessage = defaultMessage;
    }
}
