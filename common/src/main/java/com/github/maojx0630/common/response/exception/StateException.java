package com.github.maojx0630.common.response.exception;

import com.github.maojx0630.common.response.result.ResponseResultState;

public class StateException extends RuntimeException implements ResponseResultState {

    private String state;

    private String msg;

    private StateException(ResponseResultState bodyState){
        super(null,null,false,false);
        this.state=bodyState.getState();
        this.msg=bodyState.getMsg();
    }

    private StateException(ResponseResultState bodyState, String message){
        super(null,null,false,false);
        this.state=bodyState.getState();
        this.msg=bodyState.getMsg()+message;
    }

    public static StateException of(ResponseResultState bodyState){
        return new StateException(bodyState);
    }

    public static StateException of(ResponseResultState bodyState, String msg){
        return new StateException(bodyState,msg);
    }

    @Override
    public String getState() {
        return state;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
