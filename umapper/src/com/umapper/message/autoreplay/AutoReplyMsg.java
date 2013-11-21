package com.umapper.message.autoreplay;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class AutoReplyMsg {

	protected String type = "";
	protected JSONObject replyObj = null;
	protected JSONArray replyArray = null;
	public AutoReplyMsg()
	{
		replyObj = new JSONObject();
	}
	
	protected AutoReplyMsg(String type, String content)
	{
		this.type = type;
		if (content.startsWith("["))
		{
			replyArray = JSONArray.fromObject(content);
		}
		else
		{
			replyObj = JSONObject.fromObject(content);
		}
	}
	
	/**
	 * 生成一条回复消息（用于封装从数据库取出来的内容）
	 * @param type		消息类型
	 * @param content	消息内容，为json格式的字符串
	 * @return
	 */
	public static AutoReplyMsg generateReplay(String type, String content)
	{
		return new AutoReplyMsg(type, content);
	}
	
	/**
	 * 添加回复内容
	 * @param content 回复的具体内容
	 */
	public void addContent(String content)
	{		
	}
	
	/**
	 * 添加图文回复或音乐回复的内容
	 * @param title	标题
	 * @param desp	描述
	 * @param url1	链接1
	 * @param url2	链接2
	 */
	public void addContent(String title, String desp, String url1, String url2)
	{
	}
	
	public String getType()
	{
		return this.type;
	}
	
	/**
	 * 回复内容，json格式，"content":""
	 * @return
	 */
	public String getContent()
	{
		if (replyArray != null)
		{
			return replyArray.toString();
		}
		else if (replyObj != null)
		{
			return replyObj.toString();
		}
		return "";
	}
	
}
