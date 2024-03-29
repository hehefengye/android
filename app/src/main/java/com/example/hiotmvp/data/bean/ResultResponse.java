package com.example.hiotmvp.data.bean;

import java.io.Serializable;

public class ResultResponse<T> implements Serializable {

    private T data;
    private String msg;
    private int status;

    @Override
    public String toString() {
        return "ResultResponse{" +
                "data=" + data +
                ", msg='" + msg + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public ResultResponse(T data, String msg, int status) {
        this.data = data;
        this.msg = msg;
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
