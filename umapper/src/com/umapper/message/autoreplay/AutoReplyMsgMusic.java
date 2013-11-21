package com.umapper.message.autoreplay;

import com.umapper.message.WxMsgConstants;

public class AutoReplyMsgMusic extends AutoReplyMsg{

	public AutoReplyMsgMusic() {
		// TODO Auto-generated constructor stub
		type = WxMsgConstants.MSGTYPE_MUSIC;
	}
	
	/**
	 * 添加图文回复或音乐回复的内容
	 * @param title	标题
	 * @param desp	描述
	 * @param url1	音乐链接
	 * @param url2      高清音乐链接
	 */
	@Override
	public void addContent(String title, String desp, String url1, String url2)
	{
		replyObj.put(WxMsgConstants.ATTR_MUSIC_TITLE, title);
		replyObj.put(WxMsgConstants.ATTR_MUSIC_DESP, desp);
		replyObj.put(WxMsgConstants.ATTR_MUSIC_MUSIC_URL, url1);
		replyObj.put(WxMsgConstants.ATTR_MUSIC_HQMUSIC_URL, url2);
	}
}
