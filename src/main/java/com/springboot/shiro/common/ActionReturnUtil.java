package com.springboot.shiro.common;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;

/**
 * @author xutianhong
 * @Date 2021/1/9 3:27 下午
 */
public class ActionReturnUtil extends HashMap<String, Object> {

    public static ActionReturnUtil returnSuccess(){
        ActionReturnUtil returnMap = new ActionReturnUtil();
        returnMap.put("success",true);
        return returnMap;
    }

    public static ActionReturnUtil returnSuccessWithData(Object data){
        ActionReturnUtil returnMap = new ActionReturnUtil();
        returnMap.put("data", data);
        returnMap.put("success",true);
        return returnMap;
    }
    public static ActionReturnUtil returnSuccessWithData(ErrorCodeMessage error){
        ActionReturnUtil returnMap = new ActionReturnUtil();
        returnMap.put("data", ErrorCodeMessage.getMessageWithLanguage(error,"",Boolean.FALSE));
        returnMap.put("success",true);
        return returnMap;
    }

    public static ActionReturnUtil returnSuccessWithData(ErrorCodeMessage error, String extendMessage){
        ActionReturnUtil returnMap = new ActionReturnUtil();
        returnMap.put("data", ErrorCodeMessage.getMessageWithLanguage(error, extendMessage, Boolean.FALSE));
        returnMap.put("success",true);
        return returnMap;
    }

    public static ActionReturnUtil returnSuccessWithDataAndCount(Object data, int count) {
        ActionReturnUtil returnMap = new ActionReturnUtil();
        returnMap.put("data", data);
        returnMap.put("count", count);
        returnMap.put("success", true);
        return returnMap;
    }

    public static ActionReturnUtil returnSuccessWithMap(String key,String value){
        ActionReturnUtil returnMap = new ActionReturnUtil();
        returnMap.put(key, value);
        returnMap.put("success",true);
        return returnMap;
    }

    public static ActionReturnUtil returnError(){
        ActionReturnUtil returnMap = new ActionReturnUtil();
        returnMap.put("success",false);
        return returnMap;
    }

    public static ActionReturnUtil returnErrorWithCodeAndMsg(Object data, Integer errorCode){
        ActionReturnUtil returnMap = new ActionReturnUtil();
        returnMap.put("data", data);
        returnMap.put("errorCode",errorCode);
        returnMap.put("success",false);
        return returnMap;
    }

    public static ActionReturnUtil returnErrorWithData(Object data){
        ActionReturnUtil returnMap = new ActionReturnUtil();
        returnMap.put("data", data);
        returnMap.put("success",false);
        return returnMap;
    }


    public static ActionReturnUtil returnErrorWithData(Integer errorCode){
        ErrorCodeMessage error = ErrorCodeMessage.valueOf(errorCode);
        return returnErrorWithData(error, "", false);
    }

    public static ActionReturnUtil returnErrorWithData(ErrorCodeMessage error){
        return returnErrorWithData(error, "", false);
    }

    public static ActionReturnUtil returnErrorWithData(ErrorCodeMessage error, String extendMessage){
        return returnErrorWithData(error,extendMessage,false);
    }

    public static ActionReturnUtil returnErrorWithData(String extendMessage, ErrorCodeMessage error){
        return returnErrorWithData(error,extendMessage,true);
    }

    private static ActionReturnUtil returnErrorWithData(ErrorCodeMessage error, String extendMessage, boolean prefix){
        ActionReturnUtil returnMap = new ActionReturnUtil();
        returnMap.put("success",false);
        returnMap.put("errorCode", error.value());
        returnMap.put("data", ErrorCodeMessage.getMessageWithLanguage(error, extendMessage, prefix));
        return returnMap;
    }

    public static ActionReturnUtil returnError(Integer errorCode, String errorDetailMessage){
        ErrorCodeMessage error = ErrorCodeMessage.valueOf(errorCode);
        return returnError(error, "", errorDetailMessage,false);
    }

    public static ActionReturnUtil returnError(ErrorCodeMessage error, String errorDetailMessage){
        return returnError(error, "", errorDetailMessage,false);
    }

    public static ActionReturnUtil returnError(ErrorCodeMessage error, String extendMessage, String errorDetailMessage){
        return returnError(error,extendMessage,errorDetailMessage,false);
    }

    public static ActionReturnUtil returnError(String extendMessage, ErrorCodeMessage error, String errorDetailMessage){
        return returnError(error,extendMessage,errorDetailMessage,true);
    }

    private static ActionReturnUtil returnError(ErrorCodeMessage error, String extendMessage, String errorDetailMessage, boolean prefix){
        ActionReturnUtil returnMap = new ActionReturnUtil();
        returnMap.put("success",false);
        returnMap.put("errorCode", error.value());
        returnMap.put("data", ErrorCodeMessage.getMessageWithLanguage(error, extendMessage, prefix));
        returnMap.put("errorMessage", errorDetailMessage == null ? "" : errorDetailMessage);
        return returnMap;
    }

    public static ActionReturnUtil returnError(Object data, Integer errorCode, String errorDetailMessage){
        ActionReturnUtil returnMap = new ActionReturnUtil();
        returnMap.put("data", data);
        returnMap.put("errorCode",errorCode);
        returnMap.put("success",false);
        returnMap.put("errorMessage", errorDetailMessage == null ? "" : errorDetailMessage);
        return returnMap;
    }

    public static ActionReturnUtil returnErrorWithMap(String key,String value){
        ActionReturnUtil returnMap = new ActionReturnUtil();
        returnMap.put(key, value);
        returnMap.put("success",false);
        return returnMap;
    }

    public static ActionReturnUtil returnErrorWithMsg(ErrorCodeMessage error) throws MarsRuntimeException{
        return returnErrorWithMsg(error, "", false);
    }

    public static ActionReturnUtil returnErrorWithMsg(ErrorCodeMessage error, String extendMessage, boolean prefix) throws MarsRuntimeException{
        throw new MarsRuntimeException(ErrorCodeMessage.getMessageWithLanguage(error, extendMessage, prefix));
    }

    public static ActionReturnUtil returnErrorWithMsg(String errMsg) throws MarsRuntimeException{
        throw new MarsRuntimeException(errMsg);
    }

    public static ActionReturnUtil returnSuccessWithMsg(String msg){
        ActionReturnUtil returnMap = new ActionReturnUtil();
        returnMap.put("msg", msg);
        returnMap.put("success",true);
        return returnMap;
    }

    public static ActionReturnUtil returnSuccessWithDataAndMsg(Object data, String msg){
        ActionReturnUtil returnMap = new ActionReturnUtil();
        returnMap.put("msg", msg);
        returnMap.put("data", data);
        returnMap.put("success",true);
        return returnMap;
    }

    public static ActionReturnUtil returnSuccessWithData(Object data, int statusCode) {
        ActionReturnUtil returnMap = new ActionReturnUtil();
        returnMap.put("data", data);
        returnMap.put("success",true);
        returnMap.put("code",statusCode);
        return returnMap;
    }

    public static ActionReturnUtil returnErrorWithData(Object data, int statusCode) {
        ActionReturnUtil returnMap = new ActionReturnUtil();
        returnMap.put("data", data);
        returnMap.put("success",false);
        returnMap.put("code",statusCode);
        return returnMap;
    }

    public boolean isSuccess() {
        return (Boolean) get("success");
    }

    public Object getData() {
        return get("data");
    }

    public Integer getErrorCode() {
        Object code = get("errorCode");
        if(code != null){
            return Integer.parseInt(code.toString());
        }
        return null;
    }

    public String getMsg() {
        return get("msg") == null ? null : get("msg").toString();
    }
}
