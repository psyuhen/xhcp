package com.huateng.xhcp.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by sam.pan on 2017/3/27.
 */
@ToString
public class ParamType {
    private @Setter @Getter Class<?> paramType;
    private @Setter @Getter HttpServletRequest request;
    private @Setter @Getter HttpServletResponse response;
    public ParamType(){
    }

    public ParamType(Class<?> paramType){
        this(paramType, null);
    }

    public ParamType(Class<?> paramType, HttpServletRequest request){
        this(paramType, request, null);
    }

    public ParamType(Class<?> paramType, HttpServletRequest request, HttpServletResponse response){
        this.paramType = paramType;
        this.request = request;
        this.response = response;
    }
}
