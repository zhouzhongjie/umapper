package com.umapper.message;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


public class WxInfoGetter {

	private static String accessToken = "";
	private static long accessToken_validTime = -1;
	private static long accessToken_startTime = System.currentTimeMillis();
	
	//查询自定义菜单
	public static WxError getMenuInfo()
	{
		String url = String.format("https://api.weixin.qq.com/cgi-bin/menu/get?access_token=%s", 
				(String)WxInfoGetter.updateAccessToken().getObject());
		return new WxError(httpGet(url));
	}
	
	//删除自定义菜单
	public static WxError deleteMenuInfo()
	{
		String url = String.format("https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=%s", 
				(String)WxInfoGetter.updateAccessToken().getObject());
		return new WxError(httpGet(url));
	}
	
	//获取ACCESS_TOKEN
	//获取服务号的ACCESS_TOKEN
	public static WxResult updateAccessToken()
	{
		WxError error = new WxError("");		
		//如果ACCESS_TOKEN还在有效期限内，不重新申请
//		if ((System.currentTimeMillis() - accessToken_startTime) < (accessToken_validTime - 30) * 1000)
//		{
//			return new WxResult(error, accessToken);
//		}
		
		String strUrl = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s", 
				"wx3cb3e635eb4d6e7a", "eae0b4968deae3d49aad523525ea2f6c");

		String result = WxInfoGetter.httpGet(strUrl);
		if (result.isEmpty())
		{
			return new WxResult(error, "");
		}
		
		JSONObject jsonObj = JSONObject.fromObject(result);
		if (jsonObj.has("access_token"))
		{
			accessToken = jsonObj.getString("access_token");
			accessToken_validTime = Long.parseLong(jsonObj.getString("expires_in"));
			accessToken_startTime = System.currentTimeMillis();
			return new WxResult(error, accessToken);
		}

		error = new WxError(result);
		return new WxResult(error, accessToken);
	}
	
	public static WxResult updateAccessToken(String appId, String secretId)
	{
		WxError error = new WxError("");		
		//如果ACCESS_TOKEN还在有效期限内，不重新申请
//		if ((System.currentTimeMillis() - accessToken_startTime) < (accessToken_validTime - 30) * 1000)
//		{
//			return new WxResult(error, accessToken);
//		}
		
		String strUrl = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s", 
				appId, secretId);

		String result = WxInfoGetter.httpGet(strUrl);
		if (result.isEmpty())
		{
			return new WxResult(error, "");
		}
		
		JSONObject jsonObj = JSONObject.fromObject(result);
		if (jsonObj.has("access_token"))
		{
			accessToken = jsonObj.getString("access_token");
			accessToken_validTime = Long.parseLong(jsonObj.getString("expires_in"));
			accessToken_startTime = System.currentTimeMillis();
			return new WxResult(error, accessToken);
		}

		error = new WxError(result);
		return new WxResult(error, accessToken);
	}
	
	public static WxError getQRCodeUrl(String ticket)
	{
		String strUrl = String.format("https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=%s", 
				(String)WxInfoGetter.updateAccessToken().getObject());
		String result = WxInfoGetter.httpGet(strUrl);
		return new WxError("");
	}

	private static String httpGet(String strUrl)
	{
		HttpClient httpclient = new DefaultHttpClient();
	    try {
			HttpGet httpGet = new HttpGet(strUrl);
			HttpResponse response = httpclient.execute(httpGet);
			
			HttpEntity entity = response.getEntity();
			String strResult  = EntityUtils.toString(entity);
			return strResult;					
       } 
       catch (IOException e) 
       {
    	   return "";
       }
	   finally{
		   httpclient.getConnectionManager().shutdown();
	   }
	}
}
