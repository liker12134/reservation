package edu.zju.reservation.model;

public class ResponseCode {
	private String code;
	private String msg;

	public ResponseCode() {

	}

	public ResponseCode(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "ResponseCode [code=" + code + ", msg=" + msg + "]";
	}

}
