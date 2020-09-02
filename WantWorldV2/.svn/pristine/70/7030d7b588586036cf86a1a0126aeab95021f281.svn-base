package com.want.wantworld.vo;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

/**
 * 
 * @ClassName: WResult
 * @Description: 统一返回类
 * @author 00418053
 * @date 2020年8月10日
 *
 * @param <T>
 */
public class WResult<T> implements Serializable {

	private static final long serialVersionUID = -8351654891924384734L;
	
	public static final HttpStatus OK;
	public static final HttpStatus NON_AUTHORITATIVE_INFORMATION;
    public static final HttpStatus FORBIDDEN;
    public static final HttpStatus NOT_FOUND;
    public static final HttpStatus INTERNAL_SERVER_ERROR;
    public static final int E6_ERROR_PARAM_ERROR_CODE = 4001;
    public static final String E6_ERROR_PARAM_ERROR_MSG = "参数校验失败";
    private int code;
    private String message;
    private T result;
    
	
	public WResult() {
		this(OK);
	}

	public WResult(HttpStatus httpStatus) {
		this(httpStatus.value(), httpStatus.getReasonPhrase());
	}


	public WResult(int code, String message) {
		this(code, message, null);
	}



	public WResult(int code, String message, T result) {
		this.code(code);
		this.message(message);
		this.result(result);
	}
	
	private WResult<T> code(int code) {
        this.setCode(code);
        return this;
    }

    private WResult<T> message(String message) {
        this.setMessage(message);
        return this;
    }

    public WResult<T> result(T result) {
        this.setResult(result);
        return this;
    }

    public static WResult paramError(String message) {
        return new WResult(4001, message);
    }

    public boolean success() {
        return OK.value() == this.code;
    }

    public boolean error() {
        return !this.success();
    }


	public int getCode() {
		return code;
	}



	public void setCode(int code) {
		this.code = code;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	public T getResult() {
		return result;
	}



	public void setResult(T result) {
		this.result = result;
	}
	
	

	@Override
	public String toString() {
		return "WResult [code=" + code + ", message=" + message + ", result=" + result + "]";
	}



	static{
		OK = HttpStatus.OK;
		NON_AUTHORITATIVE_INFORMATION = HttpStatus.NON_AUTHORITATIVE_INFORMATION;
		FORBIDDEN = HttpStatus.FORBIDDEN;
		NOT_FOUND = HttpStatus.NOT_FOUND;
		INTERNAL_SERVER_ERROR = HttpStatus.INTERNAL_SERVER_ERROR;
	}

}
