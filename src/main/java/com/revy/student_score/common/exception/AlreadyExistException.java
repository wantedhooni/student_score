package com.revy.student_score.common.exception;

import com.revy.student_score.common.enums.ErrorCode;
import com.revy.student_score.common.exception.common.CommonException;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AlreadyExistException extends CommonException {

    public AlreadyExistException(ErrorCode errorCode, String message){
        super(errorCode, message);
    }
}
