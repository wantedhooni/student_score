package com.revy.freewheelin.common.exception.common;


import com.revy.freewheelin.common.enums.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommonException extends RuntimeException {
    protected ErrorCode errorCode;

    public CommonException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
