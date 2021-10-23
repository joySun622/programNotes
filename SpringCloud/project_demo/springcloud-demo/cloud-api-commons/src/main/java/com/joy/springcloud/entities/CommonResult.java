package com.joy.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> implements Serializable {
    private Integer code;//数据库状态码
    private String message;//数据库反馈的信息或其他信息
    private T data;//数据

    public CommonResult(Integer code,String message){
        this(code,message,null);//若该行报错，安装lombok插件
    }
}
