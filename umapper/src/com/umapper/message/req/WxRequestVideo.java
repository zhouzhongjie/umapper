package com.umapper.message.req;

import com.umapper.message.WxMsgConstants;

public class WxRequestVideo {
	
	private WxRequestItf request;
	
	public WxRequestVideo(WxRequestItf req)
	{
		this.request = req;
	}
	
	/**
	 * 视频消息媒体id，可以调用多媒体文件下载接口拉取数据
	 * @return
	 */
	public String getMediaId()
	{
		return request.getAttribute(WxMsgConstants.ATTR_VIDEO_MEDIAID);
	}
	
	/**
	 * 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据
	 * @return
	 */
	public String getThumbMediaId()
	{
		return request.getAttribute(WxMsgConstants.ATTR_VIDEO_THUMB_MEDIAID);
	}

}
