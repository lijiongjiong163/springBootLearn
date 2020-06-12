package com.ljj.hello.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 用来封装传给前台的数据以及信息的
 * @param <E>
 */
@Data
@Component
public class AjaxResponse<E> {
    private boolean isok;
    private int code;//200、400、500
    private String message;
    private E data;

    public  AjaxResponse success(E obj){
        AjaxResponse ajaxResponse = new AjaxResponse<E>();
        ajaxResponse.setIsok(true);
        ajaxResponse.setCode(200);
        ajaxResponse.setMessage("请求响应成功");
        ajaxResponse.setData(obj);
        return ajaxResponse;
    }
    public  AjaxResponse success(E obj,String message){
        AjaxResponse ajaxResponse = new AjaxResponse<E>();
        ajaxResponse.setIsok(true);
        ajaxResponse.setCode(200);
        ajaxResponse.setMessage(message);
        ajaxResponse.setData(obj);
        return ajaxResponse;
    }
    public  AjaxResponse success(){
        AjaxResponse ajaxResponse = new AjaxResponse<E>();
        ajaxResponse.setIsok(true);
        ajaxResponse.setCode(200);
        ajaxResponse.setMessage("请求响应成功");
        return ajaxResponse;
    }
}
