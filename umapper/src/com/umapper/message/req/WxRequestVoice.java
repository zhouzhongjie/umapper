package com.umapper.message.req;

import com.umapper.message.WxMsgConstants;

public class WxRequestVoice {

	private WxRequestItf request = null;

	public WxRequestVoice(WxRequestItf request) {
		this.request = request;
	}
	
	public String mediaId()
	{
		return this.request.getAttribute(WxMsgConstants.ATTR_VOICE_MEDIAID);
	}
	
	public String format()
	{
		return this.request.getAttribute(WxMsgConstants.ATTR_VOICE_FORMAT);
	}
	
	public String recognition()
	{
		return this.request.getAttribute(WxMsgConstants.ATTR_VOICE_RECOGNITION);
	}
}
