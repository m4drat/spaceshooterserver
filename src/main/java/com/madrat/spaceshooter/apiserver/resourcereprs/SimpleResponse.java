package com.madrat.spaceshooter.apiserver.resourcereprs;

public class SimpleResponse {

    private String msg;
    private String status;

    public SimpleResponse(String msg, String status) {
        this.msg = msg;
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public String getStatus() {
        return status;
    }
}
