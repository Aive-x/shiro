package com.springboot.shiro.common;

/**
 * @author xutianhong
 * @Date 2021/1/9 3:35 下午
 */
public class MarsRuntimeException extends RuntimeException {

    private Integer errorCode;
    private String errorName;
    private String errorMessage;
    private String errorDetailMessage;

    private static final long serialVersionUID = 1L;

    public MarsRuntimeException() {
    }

    public MarsRuntimeException(String message) {
        super(message);
    }

    public MarsRuntimeException(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public MarsRuntimeException(Throwable cause) {
        super(cause);
    }

    public MarsRuntimeException(ErrorCodeMessage error){
        this(error,"",false);
    }

    public MarsRuntimeException(String extendMessage, ErrorCodeMessage error) {
        super(error.getReasonChPhrase());
        this.errorCode = error.value();
        this.errorName = error.name();
        this.errorMessage = ErrorCodeMessage.getMessageWithLanguage(error,extendMessage,true);
    }

    public MarsRuntimeException(ErrorCodeMessage error, String extendMessage) {
        super(error.getReasonChPhrase());
        this.errorCode = error.value();
        this.errorName = error.name();
        this.errorMessage = ErrorCodeMessage.getMessageWithLanguage(error,extendMessage,false);
    }

    public MarsRuntimeException(ErrorCodeMessage error, String extendMessage, boolean prefix) {
        super(error.getReasonChPhrase());
        this.errorCode = error.value();
        this.errorName = error.name();
        this.errorMessage = ErrorCodeMessage.getMessageWithLanguage(error,extendMessage,prefix);
    }

    public MarsRuntimeException(String extendMessage, ErrorCodeMessage error, String errorDetailMessage) {
        super(error.getReasonChPhrase());
        this.errorCode = error.value();
        this.errorName = error.name();
        this.errorMessage = ErrorCodeMessage.getMessageWithLanguage(error,extendMessage,true);
        this.errorDetailMessage = errorDetailMessage;
    }

    public MarsRuntimeException(ErrorCodeMessage error, String extendMessage, String errorDetailMessage) {
        super(error.getReasonChPhrase());
        this.errorCode = error.value();
        this.errorName = error.name();
        this.errorMessage = ErrorCodeMessage.getMessageWithLanguage(error,extendMessage,false);
        this.errorDetailMessage = errorDetailMessage;
    }

    public MarsRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getErrorName() {
        return errorName;
    }

    public String getErrorDetailMessage() {
        return errorDetailMessage;
    }


}
