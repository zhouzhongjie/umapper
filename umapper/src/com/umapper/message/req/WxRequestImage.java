package com.umapper.message.req;

import com.umapper.message.WxMsgConstants;

public class WxRequestImage {

	private WxRequestItf request = null;

	public WxRequestImage(WxRequestItf request) {
		this.request = request;
	}
	
	public String getPicUrl()
	{
		return request.getAttribute(WxMsgConstants.ATTR_IMAGE_PICURL);
	}
	
	public String getMediaId()
	{
		return request.getAttribute(WxMsgConstants.ATTR_IMAGE_MEDIAID);
	}
}
