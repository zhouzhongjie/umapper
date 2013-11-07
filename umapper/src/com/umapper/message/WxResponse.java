package com.umapper.message;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

public class WxResponse {
	
	private String rspXml;
	public WxResponse(String rspXml)
	{
		this.rspXml = rspXml;
	}
	
	public String toString()
	{
		return this.rspXml;
	}
	
	/**
	 * 生成文本响应消息
	 * @param toName 用户OpenID
	 * @param fromName 此微信公众号
	 * @param content 文本内容
	 * @return XML消息
	 */
	public static WxResponse createTextResponse(String toName, String fromName, 
			String content){
		
		String returnStr = "";
		
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
		String times = format.format(new Date());
		
		Element rootXML = new Element("xml");

		rootXML.addContent(new Element(WxMsgConstants.ATTR_PUBLIC_TONAME).setText(fromName));
		rootXML.addContent(new Element(WxMsgConstants.ATTR_PUBLIC_FROMNAME).setText(toName));
		rootXML.addContent(new Element(WxMsgConstants.ATTR_PUBLIC_CREATETIME).setText(times));
		rootXML.addContent(new Element(WxMsgConstants.ATTR_PUBLIC_MSGTYPE).setText(WxMsgConstants.MSGTYPE_TEXT));
		
		rootXML.addContent(new Element(WxMsgConstants.ATTR_TEXT_CONTENT).setText(content));

		Document doc = new Document(rootXML); 		
		XMLOutputter XMLOut = new XMLOutputter();  
		returnStr = XMLOut.outputString(doc);

		return new WxResponse(returnStr);
	}
	
	public static WxResponse createImageResponse(String toName, String fromName, 
			String mediaId)
	{
		String returnStr = "";

		SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
		String times = format.format(new Date());
		
		Element rootXML = new Element("xml");		
		rootXML.addContent(new Element(WxMsgConstants.ATTR_PUBLIC_TONAME).setText(fromName));
		rootXML.addContent(new Element(WxMsgConstants.ATTR_PUBLIC_FROMNAME).setText(toName));
		rootXML.addContent(new Element(WxMsgConstants.ATTR_PUBLIC_CREATETIME).setText(times));
		rootXML.addContent(new Element(WxMsgConstants.ATTR_PUBLIC_MSGTYPE).setText(WxMsgConstants.MSGTYPE_IMAGE));
		
		Element imageXml = new Element("Image");
		imageXml.addContent(new Element(WxMsgConstants.ATTR_IMAGE_MEDIAID).setText(mediaId));

		rootXML.addContent(imageXml);
		
		Document doc = new Document(rootXML); 		
		XMLOutputter XMLOut = new XMLOutputter();  
		returnStr = XMLOut.outputString(doc);

		return new WxResponse(returnStr);
	}
	
	/**
	 * 生成图文响应消息
	 * @param toName 用户OpenID
	 * @param fromName 此微信公众号
	 * @param titles  每张图的标题
	 * @param descriptions  每张图的描述
	 * @param imgUrls 每张图的链接
	 * @param subUrls 每张图点击响应的链接
	 * @return XML消息 
	 */
	public static WxResponse createNewsResponse(String toName, String fromName, 
			String[] titles, String[] descriptions, String[] picUrls, 
			String[] subUrls){
		
		String returnStr = "";
		
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
		String times = format.format(new Date());
		
		Element rootXML = new Element("xml");		
		rootXML.addContent(new Element(WxMsgConstants.ATTR_PUBLIC_TONAME).setText(fromName));
		rootXML.addContent(new Element(WxMsgConstants.ATTR_PUBLIC_FROMNAME).setText(toName));
		rootXML.addContent(new Element(WxMsgConstants.ATTR_PUBLIC_CREATETIME).setText(times));
		rootXML.addContent(new Element(WxMsgConstants.ATTR_PUBLIC_MSGTYPE).setText(WxMsgConstants.MSGTYPE_NEWS));
		
		rootXML.addContent(new Element(WxMsgConstants.ATTR_NEWS_ARTICLECOUNT).setText(String.valueOf(titles.length)));
		Element articleXml = new Element(WxMsgConstants.ATTR_NEWS_ARTICLES);
		for (int i=0; i<titles.length; i++)
		{
			Element itemXml = new Element(WxMsgConstants.ATTR_NEWS_ITEM);
			itemXml.addContent(new Element(WxMsgConstants.ATTR_NEWS_TITLE).setText(titles[i]));
			itemXml.addContent(new Element(WxMsgConstants.ATTR_NEWS_DESP).setText(descriptions[i]));
			itemXml.addContent(new Element(WxMsgConstants.ATTR_NEWS_PICURL).setText(picUrls[i]));
			itemXml.addContent(new Element(WxMsgConstants.ATTR_NEWS_SUBURL).setText(subUrls[i]));
			articleXml.addContent(itemXml);
		}
		rootXML.addContent(articleXml);

		Document doc = new Document(rootXML); 		
		XMLOutputter XMLOut = new XMLOutputter();  
		returnStr = XMLOut.outputString(doc);

		return new WxResponse(returnStr);
	}
	
	/**
	 * 生成图文响应消息
	 * @param toName 用户OpenID
	 * @param fromName 此微信公众号
	 * @param array 描述所需信息的JSONArray对象
	 * 				每个元素的格式为：{"Title":"title","Description":"des","PicUrl":"picurl","Url":"url"}
	 * @return
	 */
	public static WxResponse createNewsResponse(String toName, String fromName, JSONArray array){
				
		String returnStr = "";
		
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
		String times = format.format(new Date());
		
		Element rootXML = new Element("xml");		
		rootXML.addContent(new Element(WxMsgConstants.ATTR_PUBLIC_TONAME).setText(fromName));
		rootXML.addContent(new Element(WxMsgConstants.ATTR_PUBLIC_FROMNAME).setText(toName));
		rootXML.addContent(new Element(WxMsgConstants.ATTR_PUBLIC_CREATETIME).setText(times));
		rootXML.addContent(new Element(WxMsgConstants.ATTR_PUBLIC_MSGTYPE).setText(WxMsgConstants.MSGTYPE_NEWS));
		
		rootXML.addContent(new Element(WxMsgConstants.ATTR_NEWS_ARTICLECOUNT).
				setText(String.valueOf(array.size())));
		Element articleXml = new Element(WxMsgConstants.ATTR_NEWS_ARTICLES);
		JSONObject obj = null;
		for (int i=0; i<array.size(); i++)
		{
			obj = (JSONObject)array.get(i);
			Element itemXml = new Element(WxMsgConstants.ATTR_NEWS_ITEM);
			itemXml.addContent(new Element(WxMsgConstants.ATTR_NEWS_TITLE).
					setText(obj.getString(WxMsgConstants.ATTR_NEWS_TITLE)));
			itemXml.addContent(new Element(WxMsgConstants.ATTR_NEWS_DESP).
					setText(obj.getString(WxMsgConstants.ATTR_NEWS_DESP)));
			itemXml.addContent(new Element(WxMsgConstants.ATTR_NEWS_PICURL).
					setText(obj.getString(WxMsgConstants.ATTR_NEWS_PICURL)));
			itemXml.addContent(new Element(WxMsgConstants.ATTR_NEWS_SUBURL).
					setText(obj.getString(WxMsgConstants.ATTR_NEWS_SUBURL)));
			articleXml.addContent(itemXml);
		}
		rootXML.addContent(articleXml);

		Document doc = new Document(rootXML); 		
		XMLOutputter XMLOut = new XMLOutputter();  
		returnStr = XMLOut.outputString(doc);

		return new WxResponse(returnStr);
	}
	
	/**
	 * 生成音乐响应消息
	 * @param toName 用户OpenID
	 * @param fromName 此微信公众号
	 * @title 音乐标题
	 * @description 音乐描述
	 * @musicURL 音乐链接
	 * @hQMusicUrl 高质量音乐链接，WIFI环境优先使用该链接播放音乐
	 * @thumbMediaId 缩略图的媒体id，通过上传多媒体文件，得到的id
	 * @return XML消息
	 */
	public static WxResponse createMusicResponse(String toName, String fromName, 
			String title, String description, String musicURL,
			String hQMusicUrl, String thumbMediaId){
		
		String returnStr = "";

		SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
		String times = format.format(new Date());
		
		Element rootXML = new Element("xml");		
		rootXML.addContent(new Element(WxMsgConstants.ATTR_PUBLIC_TONAME).setText(fromName));
		rootXML.addContent(new Element(WxMsgConstants.ATTR_PUBLIC_FROMNAME).setText(toName));
		rootXML.addContent(new Element(WxMsgConstants.ATTR_PUBLIC_CREATETIME).setText(times));
		rootXML.addContent(new Element(WxMsgConstants.ATTR_PUBLIC_MSGTYPE).setText(WxMsgConstants.MSGTYPE_MUSIC));

		Element mXML = new Element("Music");		
		
		mXML.addContent(new Element(WxMsgConstants.ATTR_MUSIC_TITLE).setText(title));
		mXML.addContent(new Element(WxMsgConstants.ATTR_MUSIC_DESP).setText(description));
		mXML.addContent(new Element(WxMsgConstants.ATTR_MUSIC_MUSIC_URL).setText(musicURL));
		mXML.addContent(new Element(WxMsgConstants.ATTR_MUSIC_HQMUSIC_URL).setText(hQMusicUrl));
		mXML.addContent(new Element(WxMsgConstants.ATTR_MUSIC_THUMBMEDIAID).setText(thumbMediaId));
		
		rootXML.addContent(mXML);

		Document doc = new Document(rootXML); 		
		XMLOutputter XMLOut = new XMLOutputter();  
		returnStr = XMLOut.outputString(doc);

		return new WxResponse(returnStr);
	}
	
	/**
	 * 生成语音响应消息
	 * @param toName 用户OpenID
	 * @param fromName 此微信公众号
	 * @param mediaId 多媒体资源id
	 * @return
	 */
	public static WxResponse createVoiceResponse(String toName, String fromName, String mediaId)
	{

		String returnStr = "";

		SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
		String times = format.format(new Date());
		
		Element rootXML = new Element("xml");		
		rootXML.addContent(new Element(WxMsgConstants.ATTR_PUBLIC_TONAME).setText(fromName));
		rootXML.addContent(new Element(WxMsgConstants.ATTR_PUBLIC_FROMNAME).setText(toName));
		rootXML.addContent(new Element(WxMsgConstants.ATTR_PUBLIC_CREATETIME).setText(times));
		rootXML.addContent(new Element(WxMsgConstants.ATTR_PUBLIC_MSGTYPE).setText(WxMsgConstants.MSGTYPE_VOICE));
		Element voiceXml = new Element("Voice");
		voiceXml.addContent(new Element(WxMsgConstants.ATTR_VOICE_MEDIAID).setText(mediaId));
		rootXML.addContent(voiceXml);
		Document doc = new Document(rootXML); 		
		XMLOutputter XMLOut = new XMLOutputter();  
		returnStr = XMLOut.outputString(doc);

		return new WxResponse(returnStr);
	}
	
	/**
	 * 生成视频响应消息
	 * @param toName 用户OpenID
	 * @param fromName 此微信公众号
	 * @param mediaId 多媒体资源id
	 * @thumbMediaId 缩略图的多媒体id
	 * @return
	 */
	public static WxResponse createVideoResponse(String toName, String fromName, 
			String mediaId, String thumbMediaId)
	{

		String returnStr = "";

		SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
		String times = format.format(new Date());
		
		Element rootXML = new Element("xml");		
		rootXML.addContent(new Element(WxMsgConstants.ATTR_PUBLIC_TONAME).setText(fromName));
		rootXML.addContent(new Element(WxMsgConstants.ATTR_PUBLIC_FROMNAME).setText(toName));
		rootXML.addContent(new Element(WxMsgConstants.ATTR_PUBLIC_CREATETIME).setText(times));
		rootXML.addContent(new Element(WxMsgConstants.ATTR_PUBLIC_MSGTYPE).setText(WxMsgConstants.MSGTYPE_VIDEO));
		
		Element videoXml = new Element("Video");
		videoXml.addContent(new Element(WxMsgConstants.ATTR_VIDEO_MEDIAID).setText(mediaId));
		videoXml.addContent(new Element(WxMsgConstants.ATTR_VIDEO_THUMB_MEDIAID).setText(thumbMediaId));

		rootXML.addContent(videoXml);
		
		Document doc = new Document(rootXML); 		
		XMLOutputter XMLOut = new XMLOutputter();  
		returnStr = XMLOut.outputString(doc);

		return new WxResponse(returnStr);
	}
}
