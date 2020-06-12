package com.ljj.hello.domain;

import lombok.Data;
import org.springframework.stereotype.Component;
/**
 * 用来封装传给前台的数据以及信息的(专用于返回excel请求)
 * @param <E>
 */
@Data
@Component
public class ExcelResponse<E> {

    private boolean isok;
    private int code;//200、400、500
    private String message;
    private E data;

    public  ExcelResponse success(E obj){
        ExcelResponse excelResponse = new ExcelResponse<E>();
        excelResponse.setIsok(true);
        excelResponse.setCode(200);
        excelResponse.setMessage("请求响应成功");
        excelResponse.setData(obj);
        return excelResponse;
    }
    public  ExcelResponse success(E obj,String message){
        ExcelResponse excelResponse = new ExcelResponse<E>();
        excelResponse.setIsok(true);
        excelResponse.setCode(200);
        excelResponse.setMessage(message);
        excelResponse.setData(obj);
        return excelResponse;
    }
    public  ExcelResponse success(){
        ExcelResponse excelResponse = new ExcelResponse<E>();
        excelResponse.setIsok(true);
        excelResponse.setCode(200);
        excelResponse.setMessage("请求响应成功");
        return excelResponse;
    }
}