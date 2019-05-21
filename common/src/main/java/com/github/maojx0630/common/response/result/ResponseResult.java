package com.github.maojx0630.common.response.result;


/**
 * @author MaoJiaXing
 */
public class ResponseResult<T> implements ResponseResultState {

	private String state;

	private String msg;

	private T data;

	public ResponseResult() {
	}

	public ResponseResult(ResponseResultState bodyState) {
		this.state = bodyState.getState();
		this.msg = bodyState.getMsg();
	}

	public ResponseResult(ResponseResultState bodyState, T data) {
		this.state = bodyState.getState();
		this.msg = bodyState.getMsg();
		this.data = data;
	}

	public ResponseResult appendMsg(String msg){
		this.msg=this.msg+msg;
		return this;
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

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
