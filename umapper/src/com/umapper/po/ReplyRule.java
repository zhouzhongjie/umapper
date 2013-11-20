package com.umapper.po;

public class ReplyRule {
	private String user;
	private String key;
	private String matchrule;
	private String type;
	private String content;
	
	public String getUser()
	{
		return this.user;
	}
	
	public void setUser(String user)
	{
		this.user = user;
	}
	
	public String getKey()
	{
		return this.key;
	}
	
	public void setKey(String key)
	{
		this.key = key;
	}
	
	public String getMatchRule()
	{
		return this.matchrule;
	}
	
	public void setMatchRule(String rule)
	{
		this.matchrule = rule;
	}
	
	public String getType()
	{
		return this.type;		
	}
	
	public void setType(String type)
	{
		this.type = type;
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
