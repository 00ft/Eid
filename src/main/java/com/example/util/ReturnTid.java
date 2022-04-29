package com.example.util;

import java.util.List;
import java.util.Map;

public class ReturnTid {
    private int resCode;
    private String msg;
    private Object epcList;



    @Override
    public String toString() {
        return "ReturnTid{" +
                "resCode=" + resCode +
                ", msg='" + msg + '\'' +
                ", epcList=" + epcList +
                '}';
    }


    public int getResCode() {
        return resCode;
    }

    public void setResCode(int resCode) {
        this.resCode = resCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getEpcList() {
        return epcList;
    }

    public void setEpcList(Object epcList) {
        this.epcList = epcList;
    }

    public ReturnTid(int resCode, String msg, Object epcList) {
        this.resCode = resCode;
        this.msg = msg;
        this.epcList = epcList;
    }
}
