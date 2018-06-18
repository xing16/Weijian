package com.xing.weijian.http;

/**
 * Created by Administrator on 2017/9/16.
 */

public class ApiException extends RuntimeException {

    private int errorCode;

    public ApiException(int errorCode, String msg) {
        super(msg);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }


}
