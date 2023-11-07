package com.revy.freewheelin.endpoint.common;

import com.revy.freewheelin.common.enums.ErrorCode;
import com.revy.freewheelin.common.exception.AlreadyExistException;
import com.revy.freewheelin.common.exception.NotFoundException;
import com.revy.freewheelin.common.exception.common.CommonException;
import com.revy.freewheelin.endpoint.res.common.CommonRes;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonRes handleException(HttpServletRequest request, Exception exception) {
        log.warn("method: {}, url: {}", request.getMethod(), request.getRequestURI(), exception);
        return new CommonRes(new CommonRes.Error(ErrorCode.INTERNAL_SERVER_ERROR));
    }

    /**
     * Entity가 이미 존재 할 경우
     *
     * @param request
     * @param alreadyExistException
     * @return
     */
    @ExceptionHandler(AlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonRes handleNotFoundException(HttpServletRequest request, AlreadyExistException alreadyExistException) {
        log.warn("method: {}, url: {}", request.getMethod(), request.getRequestURI(), alreadyExistException);
        return new CommonRes(new CommonRes.Error(alreadyExistException.getErrorCode().getCode(), alreadyExistException.getMessage()));
    }

    /**
     * Entity가 존재 하지 않을경우
     *
     * @param request
     * @param notFoundException
     * @return
     */
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CommonRes handleNotFoundException(HttpServletRequest request, NotFoundException notFoundException) {
        log.warn("method: {}, url: {}", request.getMethod(), request.getRequestURI(), notFoundException);
        return new CommonRes(new CommonRes.Error(notFoundException.getErrorCode().getCode(), notFoundException.getMessage()));
    }

    @ExceptionHandler(CommonException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonRes handleCommonException(HttpServletRequest request, CommonException commonException) {
        log.warn("method: {}, url: {}", request.getMethod(), request.getRequestURI(), commonException);
        return new CommonRes(new CommonRes.Error(commonException.getErrorCode().getCode(), commonException.getMessage()));
    }


    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonRes handleIllegalArgumentException(HttpServletRequest request, IllegalArgumentException illegalArgumentException) {
        log.warn("method: {}, url: {}", request.getMethod(), request.getRequestURI(), illegalArgumentException);
        return new CommonRes(new CommonRes.Error(ErrorCode.BAD_REQUEST, illegalArgumentException.getMessage()));
    }

}
