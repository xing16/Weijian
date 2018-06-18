package com.xing.weijian.http;

import java.util.List;

/**
 * Created by Administrator on 2017/9/2.
 */

public class BaseResponse<T> {

    private boolean error;

    private int errorCode;

    private String errmsg;

    private List<T> results;

    public boolean isError() {
        return error;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setData(List<T> data) {
        this.results = data;
    }

    public List<T> getData() {
        return results;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "error=" + error +
                ", errorCode=" + errorCode +
                ", errmsg='" + errmsg + '\'' +
                ", data=" + results +
                '}';
    }
}
