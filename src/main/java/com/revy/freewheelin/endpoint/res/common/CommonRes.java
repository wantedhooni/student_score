package com.revy.freewheelin.endpoint.res.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.revy.freewheelin.common.enums.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CommonRes<T> {

    @JsonProperty("data")
    private T data;
    @JsonProperty("error")
    private Error error;

    public CommonRes(Error error) {
        this.error = error;
    }

    public CommonRes(T data) {
        this.data = data;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @ToString
    public static class Error {

        @JsonProperty("code")
        private String code;

        @JsonProperty("message")
        private String message;

        public Error(ErrorCode errorCode) {
            this.code = errorCode.getCode();
            this.message = errorCode.getDefaultMessage();
        }
        public Error(ErrorCode errorCode, String message) {
            this.code = errorCode.getCode();
            this.message = message;
        }

        public Error(String code, String message) {
            this.code = code;
            this.message = message;
        }
    }
}


