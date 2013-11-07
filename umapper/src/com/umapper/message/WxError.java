package com.umapper.message;

import net.sf.json.JSONObject;

public class WxError {
	private static final String ERROR_CODE = "errcode";
	private static final String ERROR_MSG  = "errmsg";

	private JSONObject jObj = null;
	
	/**
	 * 构造函数
	 * @param result 微信服务器的返回字符串
	 */
	public WxError(String result)
	{
		if (result.equals(""))
		{
			result = "{}";
		}
		jObj = JSONObject.fromObject(result);
	}
	
	/**
	 * 构造函数
	 * @param code 自定义的错误码
	 * @param desp 自定义的错误描述
	 */
	public WxError(int code, String desp)
	{
		jObj = new JSONObject();
		jObj.put(ERROR_CODE, String.valueOf(code));
		jObj.put(ERROR_MSG, desp);
	}
	
	public int getCode()
	{
		if (jObj.has(ERROR_CODE))
		{
			return Integer.parseInt(jObj.getString(ERROR_CODE));
		}
		return 0;
	}
	
	public String getDesp()
	{
		if (jObj.has(ERROR_MSG))
		{
			return jObj.getString(ERROR_MSG);
		}
		return "";
	}
}
