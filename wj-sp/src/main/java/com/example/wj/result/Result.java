package com.example.wj.result;

import lombok.Data;

@Data
public class Result {
    private int code;   //状态码
    private String message; //返回状态信息
    private Object data;  //返回内容

    Result(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
