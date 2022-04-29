package com.example.util;

import java.io.Serializable;

public class ReturnObject implements Serializable {
    private boolean flag;
    private Object msg;

    @Override
    public String toString() {
        return "ReturnObject{" +
                "flag=" + flag +
                ", msg=" + msg +
                '}';
    }

    public ReturnObject() {
    }

    public ReturnObject(boolean flag, Object msg) {
        this.flag = flag;
        this.msg = msg;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

}
