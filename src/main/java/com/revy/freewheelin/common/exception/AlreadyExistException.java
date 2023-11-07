package com.revy.freewheelin.common.exception;

import com.revy.freewheelin.common.enums.ErrorCode;
import com.revy.freewheelin.common.exception.common.CommonException;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AlreadyExistException extends CommonException {

    public AlreadyExistException(ErrorCode errorCode, String message){
        super(errorCode, message);
    }
}
