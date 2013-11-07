package com.umapper.message;

public interface WxMsgConstants {
	//请求消息类型
	public static final String MSGTYPE_IMAGE = "image"; 
	public static final String MSGTYPE_LINK = "link";
	public static final String MSGTYPE_LOCATION = "location";
	public static final String MSGTYPE_TEXT = "text";
	public static final String MSGTYPE_VIDEO = "video";
	public static final String MSGTYPE_VOICE = "voice";
	public static final String MSGTYPE_EVENT = "event";
	public static final String MSGTYPE_MUSIC = "music";
	public static final String MSGTYPE_NEWS  = "news";
	
	//公共消息属性
	public static final String ATTR_PUBLIC_TONAME = "ToUserName";
	public static final String ATTR_PUBLIC_FROMNAME = "FromUserName";
	public static final String ATTR_PUBLIC_CREATETIME = "CreateTime";
	public static final String ATTR_PUBLIC_MSGTYPE = "MsgType";
	public static final String ATTR_PUBLIC_MSGID = "MsgId";
	
	//文本消息的属性
	public static final String ATTR_TEXT_CONTENT = "Content";

	//图片消息的属性
	public static final String ATTR_IMAGE_PICURL = "PicUrl";
	public static final String ATTR_IMAGE_MEDIAID = "MediaId";

	//链接消息的属性
	public static final String ATTR_LINK_TITLE = "Title";
	public static final String ATTR_LINK_DESCRIPTION = "Description";
	public static final String ATTR_LINK_URL  = "Url";
	
	//位置消息的属性
	public static final String ATTR_LOCATION_X = "Location_X";
	public static final String ATTR_LOCATION_Y = "Location_Y";
	public static final String ATTR_LOCATION_SCALE = "Scale";
	public static final String ATTR_LOCATION_LABEL = "Label";
	
	//视频消息的属性
	public static final String ATTR_VIDEO_MEDIAID = "MediaId";
	public static final String ATTR_VIDEO_THUMB_MEDIAID = "ThumbMediaId";
	
	//声音消息的属性
	public static final String ATTR_VOICE_MEDIAID = "MediaId";
	public static final String ATTR_VOICE_FORMAT  = "Format";
	
	//如果语音消息中有此属性，说明该消息是一个语音识别结果
	public static final String ATTR_VOICE_RECOGNITION = "Recognition";	
	
	//事件消息的属性，包括订阅/取消订阅事件、自定义菜单事件等
	public static final String ATTR_EVENT_EVENT 	= "Event";
	public static final String ATTR_EVENT_EVENTKEY 	= "EventKey";
	//二维码扫描事件/用户已关注时的事件推送
	public static final String ATTR_EVENT_QRCODE_TICKET = "Ticket";
	//用户位置推送事件
	public static final String ATTR_EVENT_LOCATION_LATITUDE = "Latitude";
	public static final String ATTR_EVENT_LOCATION_LONGITUDE = "Longitude";
	public static final String ATTR_EVENT_LOCATION_PRECISION = "Precision";	
	
	//音乐消息的属性
	public static final String ATTR_MUSIC_TITLE 		= "Title";
	public static final String ATTR_MUSIC_DESP  		= "Description";
	public static final String ATTR_MUSIC_MUSIC_URL		= "MusicUrl";
	public static final String ATTR_MUSIC_HQMUSIC_URL 	= "HQMusicUrl";
	public static final String ATTR_MUSIC_THUMBMEDIAID	= "ThumbMediaId";
	
	
	//图文消息的属性
	public static final String ATTR_NEWS_ARTICLECOUNT = "ArticleCount";
	public static final String ATTR_NEWS_ARTICLES 	= "Articles";
	public static final String ATTR_NEWS_ITEM		= "item";
	public static final String ATTR_NEWS_TITLE		= "Title";
	public static final String ATTR_NEWS_DESP       = "Description";
	public static final String ATTR_NEWS_PICURL		= "PicUrl";
	public static final String ATTR_NEWS_SUBURL		= "Url";
}
