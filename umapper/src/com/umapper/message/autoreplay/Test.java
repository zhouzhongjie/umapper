package com.umapper.message.autoreplay;

import com.umapper.service.AutoReplayServie;

public class Test {
	
	public static void main(String[] args)
	{
		//添加一条文字回复
		String wxurl = "http://wxurl.com";
		String keywords = "?,？，help，帮助";
		AutoReplyMsg msg = new AutoReplyMsgText();
		msg.addContent("帮助信息，请见...");
		AutoReplayServie.addReplyRule(wxurl, keywords, 
				AutoReplyKey.MATCH_APPROXIMATE, msg);
		//添加一条图文回复
		keywords = "首页，home";
		msg = new AutoReplyMsgNews();
		msg.addContent("图片1", "图片1的描述", "http://img1.html", "http://imsg1_subImg.html");
		msg.addContent("图片2", "图片2的描述", "http://img2.html", "http://imsg2_subImg.html");
		msg.addContent("图片3", "图片3的描述", "http://img3.html", "http://imsg3_subImg.html");
		AutoReplayServie.addReplyRule(wxurl, keywords, 
				AutoReplyKey.MATCH_APPROXIMATE, msg);
		
		//添加一条音乐回复
		keywords = "音乐";
		msg = new AutoReplyMsgMusic();
		msg.addContent("这是一首好听的歌", "听歌使人放松", "http://music.html", "http://hqMusic.html");
		AutoReplayServie.addReplyRule(wxurl, keywords, AutoReplyKey.MATCH_ACCURATE, msg);
		
		String content = "请求帮助";
		AutoReplyMsg reply = MsgMatcher.match(content, wxurl);
		if (reply == null)
		{
			System.out.println("not match");
		}
		else
		{
			System.out.println("match: msgtype=" + reply.getType() + ", content=" + reply.getContent());
		}
		
		content = "首页";
		reply = MsgMatcher.match(content, wxurl);
		if (reply == null)
		{
			System.out.println("not match");
		}
		else
		{
			System.out.println("match: msgtype=" + reply.getType() + ", content=" + reply.getContent());
		}
		
		content = "音乐";
		reply = MsgMatcher.match(content, wxurl);
		if (reply == null)
		{
			System.out.println("not match");
		}
		else
		{
			System.out.println("match: msgtype=" + reply.getType() + ", content=" + reply.getContent());
		}
		
		content = "听音乐";
		reply = MsgMatcher.match(content, wxurl);
		if (reply == null)
		{
			System.out.println("not match");
		}
		else
		{
			System.out.println("match: msgtype=" + reply.getType() + ", content=" + reply.getContent());
		}
		
		
	}

}
