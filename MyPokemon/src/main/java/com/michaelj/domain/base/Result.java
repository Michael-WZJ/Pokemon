package com.michaelj.domain.base;

public class Result {
    private Integer code;
    private Object data;
    private String msg;

    public Result() {}

    public Result(Integer code, Object data) {
        this.data = data;
        this.code = code;
    }

    public Result(Integer code, Object data, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Result{" +
                "data=" + data +
                ", code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    /*                      Accessor and Mutator Methods                        */
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
