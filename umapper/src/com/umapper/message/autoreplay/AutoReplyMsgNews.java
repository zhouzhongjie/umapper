package com.umapper.message.autoreplay;

import com.umapper.message.WxMsgConstants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class AutoReplyMsgNews extends AutoReplyMsg{
	

	public AutoReplyMsgNews() {
		// TODO Auto-generated constructor stub
		replyArray = new JSONArray();
		type = WxMsgConstants.MSGTYPE_NEWS;
	}
		
	
	/**
	 * 添加图文回复的内容，可多次调用添加多个图文，第一次填添加的为主图文
	 * @param title	标题
	 * @param desp	描述
	 * @param url1	图片的链接
	 * @param url2	点击图片的链接
	 */
	@Override
	public void addContent(String title, String desp, String url1, String url2)
	{
		JSONObject json = new JSONObject();
		json.put(WxMsgConstants.ATTR_NEWS_TITLE, title);
		json.put(WxMsgConstants.ATTR_NEWS_DESP, desp);
		json.put(WxMsgConstants.ATTR_NEWS_PICURL, url1);
		json.put(WxMsgConstants.ATTR_NEWS_SUBURL, url2);
		
		replyArray.add(json);
	}
		
}
