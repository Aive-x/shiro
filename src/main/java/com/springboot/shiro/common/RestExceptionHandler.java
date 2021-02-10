package com.springboot.shiro.common;

import com.springboot.shiro.util.ActionReturnUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author xutianhong
 * @Date 2021/1/9 4:15 下午
 * 对异常信息的返回进行自定义处理
 */
@ControllerAdvice
public class RestExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final int CODE_LENGTH = 6;
    private static final int ERROR_MESSAGE_MAX_LENGTH = 2000;

    /**
     * 平台运行时非严重异常
     * @param e
     * @return
     */
    @ExceptionHandler(MarsRuntimeException.class)
    @ResponseBody
    @ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
    public ActionReturnUtil marsRuntimeExceptionHandler(Exception e) {
        MarsRuntimeException exception =(MarsRuntimeException)e;
        String errorMessage = exception.getErrorMessage() == null?(e.getMessage()):exception.getErrorMessage();
        logger.warn(errorMessage,e);
        return ActionReturnUtil.returnError(errorMessage,exception.getErrorCode(), exception.getErrorDetailMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
    public ActionReturnUtil exceptionHandler(Exception e) {
        logger.error(e.getMessage(),e);
        String errorMessage = e.getMessage();
        if (errorMessage != null && errorMessage.length() > ERROR_MESSAGE_MAX_LENGTH) {
            errorMessage = errorMessage.substring(0, ERROR_MESSAGE_MAX_LENGTH) + "......";
        }
        return ActionReturnUtil.returnError(ErrorCodeMessage.UNKNOWN, errorMessage);
    }
}
