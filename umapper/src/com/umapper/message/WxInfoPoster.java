package com.umapper.message;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import net.sf.json.JSONObject;

public class WxInfoPoster {
	
	public static WxError postMenu(WxMenu menu)
	{
		String urlStr = String.format("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s", 
				(String)WxInfoGetter.updateAccessToken().getObject());
		String result = WxInfoPoster.post(urlStr, menu.getJsonObject().toString());
		return new WxError(result);
	}

	public static WxResult createTempQRTicket(int validTime, int sceneId)
	{
		String urlStr = String.format("https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=%s", 
				(String)WxInfoGetter.updateAccessToken().getObject());
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("expire_seconds", String.valueOf(validTime));
		jsonObj.put("action_name", "QR_SCENE");

		JSONObject sceneInfo = new JSONObject();
		sceneInfo.put("scene_id", String.valueOf(sceneId));
		
		JSONObject actionInfo = new JSONObject();
		actionInfo.put("scene", sceneInfo);
		
		jsonObj.put("action_info", actionInfo);
		
		String result = WxInfoPoster.post(urlStr, jsonObj.toString());
		JSONObject resultObj = JSONObject.fromObject(result);
		if (resultObj.has("ticket"))
		{
			return new WxResult(new WxError(""), resultObj.getString("ticket"));
		}
		return new WxResult(new WxError(result), "");
	}
	
	public static WxError createLimitQRTicket()
	{
		return null;
	}
	
	/**
	 * 
	 * @param urlStr URL
	 * @param msg 必须实现toString()方法
	 * @return 
	 */
	private static String post(String urlStr, Object msg)
	{
		try {
            //创建连接
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");

            connection.connect();

            //POST请求
            DataOutputStream out = new DataOutputStream(
                    connection.getOutputStream());

            out.writeBytes(msg.toString());
            out.flush();
            out.close();

            //读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String lines;
            StringBuffer sb = new StringBuffer("");
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                sb.append(lines);
            }
            reader.close();
            // 断开连接
            connection.disconnect();
            return sb.toString();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
		return "";
	}
}
