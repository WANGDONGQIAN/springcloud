package com.bagevent.common;


import java.io.Serializable;

//保证序列化后不显示值为空的情况
public class ServerResponse<T> implements Serializable {
    private int status;
    private String mes;
    private T data;

    private ServerResponse(int status){
        this.status=status;
    }

    private ServerResponse(int status,T data){
        this.data=data;
        this.status=status;
    }

    private ServerResponse(int status,T data,String mes){
        this.status=status;
        this.data=data;
        this.mes=mes;
    }

    private ServerResponse(int status,String mes){
        this.mes=mes;
        this.status=status;
    }

    public boolean isSuccess(){
        return this.status==ResponseCode.SUCCESS.getCode();
    }

    public int getStatus() {
        return status;
    }

    public String getMes() {
        return mes;
    }

    public T getData() {
        return data;
    }

    public static <T> ServerResponse<T> createBySuccess(){
        return new ServerResponse(ResponseCode.SUCCESS.getCode());
    }

    public static <T> ServerResponse<T> createBySuccess(String mes,T data ){
        return new ServerResponse(ResponseCode.SUCCESS.getCode(),data,mes);
    }

    public static <T> ServerResponse<T> createBySuccess(T data ){
        return new ServerResponse(ResponseCode.SUCCESS.getCode(),data);
    }

    public static <T> ServerResponse<T> createBySuccessMessage(String mes){
        return new ServerResponse(ResponseCode.SUCCESS.getCode(),mes);
    }

    public static <T> ServerResponse<T> createByError(){
        return new ServerResponse(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }

    public static <T> ServerResponse<T> createByErrorMes(String mes){
        return new ServerResponse(ResponseCode.ERROR.getCode(),mes);
    }

    public static <T> ServerResponse<T> createByErrorCodeMes(String mes,int errorCode){
        return new ServerResponse(errorCode,mes);
    }
}
