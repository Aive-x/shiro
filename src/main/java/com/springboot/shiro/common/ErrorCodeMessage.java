package com.springboot.shiro.common;

/**
 * @author xutianhong
 * @Date 2021/1/9 3:29 下午
 */
public enum ErrorCodeMessage {

    LOGIN_FAIL(10001, "登录失败,账号密码错误"),
    UNKNOWN(100002,  "系统错误，请联系系统管理员"),
    JWC_LOGIN_FAILED(100003, "教务处登录失败"),
    WRONG_PASSWORD(100004, "密码错误"),
    PASSWORD_NOT_SAME(100005, "新密码与确认密码不同"),
    STUDENT_NUMBER_EMPTY(100006,"学号不能为空"),
    TAG_EMPTY(100006,"tag不能为空"),
    INVALID_TOKEN(100007, "token错误或已过期"),
    FILE_NOT_EXIST(100008,"文件不存在"),
    FILE_NOT_CORRECT(100009,"文件格式不正确");

    private final int value;
    private final String reasonChPhrase;

    ErrorCodeMessage(int value, String reasonChPhrase) {
        this.value = value;
        this.reasonChPhrase = reasonChPhrase;
    }

    public int value() {
        return this.value;
    }

    public String getReasonChPhrase() {
        return reasonChPhrase;
    }

    public static ErrorCodeMessage valueOf(int statusCode) {
        ErrorCodeMessage[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            ErrorCodeMessage status = var1[var3];
            if (status.value == statusCode) {
                return status;
            }
        }

        throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
    }

    public static String getMessageWithLanguage(ErrorCodeMessage error, String extendMessage, boolean prefix){
        String message;
        if (prefix) {
            message = extendMessage + error.getReasonChPhrase();
        } else {
            message = error.getReasonChPhrase() + extendMessage;
        }
        return message;
    }
}
