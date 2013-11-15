package com.umapper.po;

public class Material {
	private String url;
	private String bucket;
	private String filename;
	private int time;
	
	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}
	
	public String getBucket()
	{
		return this.bucket;
	}
	
	public void setBucket(String bucket)
	{
		this.bucket = bucket;
	}
	
	public String getFileName()
	{
		return this.filename;
	}
	
	public void setFileName(String filename)
	{
		this.filename = filename;
	}
	
	public int getTime()
	{
		return this.time;
	}
	
	public void setTime(int time)
	{
		this.time = time;
	}
	
	public Material()
	{
		super();
	}
}
