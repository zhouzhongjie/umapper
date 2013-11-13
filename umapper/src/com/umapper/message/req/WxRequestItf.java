package com.umapper.message.req;

import java.util.Map;

public interface WxRequestItf {


	//获取请求消息的类型
	public String getMsgType();
	
	//获取用户的openID
	public String getToName();
	
	//获取本公众账号
	public String getFromName();
	
	//获取消息Id
	public String getMsgId();
	
	//获取请求消息中的某个属性
	public String getAttribute(String attr);
	
	//获取所有属性
	public Map<String, String> getAttributes();

}
