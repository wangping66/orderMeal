package com.common.exception;


import com.constant.HttpResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommonException extends RuntimeException {

    private String customMsg;// 报错信息
    private String exceptionCode; // code
    private String exceptionName;
    private Object result; //用于某些特殊情况，需要返回给前端数据时使用


    public CommonException(String customMsg) {
        super();
        this.setCustomMsg(customMsg);
        this.setExceptionCode(HttpResponseCode.SC_BAD_REQUEST);
    }

    public CommonException(String customMsg, Exception e) {
        super();
        this.setCustomMsg(customMsg);
        this.setExceptionName(e.toString());
        this.setExceptionCode(HttpResponseCode.SC_BAD_REQUEST);
        this.setStackTrace(e.getStackTrace());
    }

    public CommonException(Exception exception) {
        this(null, exception);
    }


    public CommonException(String customMsg, String exceptionCode) {
        super();
        this.setCustomMsg(customMsg);
        this.setExceptionCode(exceptionCode);
    }

    public CommonException(String customMsg, String exceptionCode, Object result) {
        super();
        this.setCustomMsg(customMsg);
        this.setExceptionCode(exceptionCode);
        this.setResult(result);
    }


}
