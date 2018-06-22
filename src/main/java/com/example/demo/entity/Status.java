package com.example.demo.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author: 李纹纹
 * @date : 2018/6/6 20:10
 */
public class Status implements Serializable{

    private int code;

    private String msg;

    private List<?> list;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List <?> getList() {
        return list;
    }

    public void setList(List <?> list) {
        this.list = list;
    }
}
