package com.umapper.po;

public class ReplyRule {
	private String wxurl;
	private String keyword;
	private String matchrule;
	private String msgtype;
	private String content;
	
	public String getWxurl()
	{
		return this.wxurl;
	}
	
	public void setWxurl(String wxurl)
	{
		this.wxurl = wxurl;
	}
	
	public String getKeyword()
	{
		return this.keyword;
	}
	
	public void setKeyword(String keyword)
	{
		this.keyword = keyword;
	}
	
	public String getMatchRule()
	{
		return this.matchrule;
	}
	
	public void setMatchRule(String rule)
	{
		this.matchrule = rule;
	}
	
	public String getMsgype()
	{
		return this.msgtype;		
	}
	
	public void setMsgype(String msgtype)
	{
		this.msgtype = msgtype;
	}
	
	public String getContent()
	{
		return this.content;
	}
	
	public void setContent(String content)
	{
		this.content = content;
	}
	
	public ReplyRule()
	{
		super();
	}

}
