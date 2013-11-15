package com.umapper.util;

public class Common {

	//获取WebRoot目录
	public static String getWebrootPath()
	{
		String path = Common.class.getResource("/").getFile();
		return path.replaceAll("WEB-INF.*", "");
	}
	
	
	//获取WebInfo目录
	public static String getWebinfoPath()
	{
		String path = Common.class.getResource("/").getFile();
		return path.replaceAll("(WEB-INF).*", "$1");
	}
	
}
