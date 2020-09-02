package com.want.error;
// 包装器 业务实现异常逻辑实现
public class BusinessException extends Exception implements CommonError{
	private static final long serialVersionUID = -6807636309382972211L;
	private CommonError commonError;
	public BusinessException(CommonError commonError) {
		super();
		this.commonError = commonError;
	}
	public BusinessException(CommonError commonError,String errMsg) {
		super();
		this.commonError = commonError;
		this.commonError.setErrmsg(errMsg);
	}
	@Override
	public int getErrCode() {
		return this.commonError.getErrCode();
	}
	@Override
	public String getErrMsg() {
		return this.commonError.getErrMsg();
	}
	@Override
	public CommonError setErrmsg(String errMsg) {
		this.commonError.setErrmsg(errMsg);
		return this;
	}

}
