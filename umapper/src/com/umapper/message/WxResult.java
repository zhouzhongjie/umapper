package com.umapper.message;


public class WxResult {
	
	private WxError error = null;
	private Object obj = null;
	
	/**
	 * 构造函数
	 * @param error 微信服务器的返回值
	 * @param obj	自定义的返回类型
	 */
	public WxResult(WxError error, Object obj)
	{
		this.error = error;
		this.obj = obj;
	}
	
	public WxError getError()
	{
		return error;
	}
	
	public Object getObject()
	{
		return obj;
	}

}
