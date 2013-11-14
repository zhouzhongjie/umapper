package com.umapper.baidu.bcs;

public class MaterialObject {
	private String bucket;
	private String name;
	private long time;
	private String url;
	
	public MaterialObject(String bucket, String name)
	{
		this.bucket = bucket;
		this.name = name;
	}
	
	public String getBucket()
	{
		return this.bucket;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setTime(long time)
	{
		this.time = time;
	}
	
	public long getTime()
	{
		return this.time;
	}
	
	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getUrl()
	{
		return this.url;
	}
}
