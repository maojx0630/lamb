package com.github.maojx0630.common.response.exception;

import com.github.maojx0630.common.response.result.ResponseResultState;

public enum  StateEnum implements ResponseResultState {

    /**
     * 成功
     */
    success("0", "success"),
    /**
     * 无数据返回
     */
    no_data("1", "no data"),
    /**
     * 发生了意料之外的异常
     */
    error("500", "发生了未知异常，请您稍后再试！"),
    /**
     * 访问的接口不存在
     */
    no_found("404", "访问的接口不存在 "),
    /**
     * 用户未登录
     */
    user_not_login("600","用户未登录"),
    /**
     * token禁止重复使用
     */
    token_repeat("601","token不能重复使用"),
    /**
     * token校验失败
     */
    token_check_failure("602","token校验失败"),
    /**
     * token超时
     */
    token_timeout("603","token超时")
    ;

    private String state;

    private String msg;

    StateEnum(String state, String msg) {
        this.state = state;
        this.msg = msg;
    }

    @Override
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
