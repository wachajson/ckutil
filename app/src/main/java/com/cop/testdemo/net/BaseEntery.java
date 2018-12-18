package com.cop.testdemo.net;

import java.io.Serializable;

public class BaseEntery<T> implements Serializable {
    public int code;
    public String msg;
    public T obj;

    @Override
    public String toString() {
        return "BaseEntery{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", obj=" + obj +
                '}';
    }
}
