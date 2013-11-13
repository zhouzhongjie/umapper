package com.umapper.message.req;

import com.umapper.message.WxMsgConstants;

public class WxRequestText{
	private WxRequestItf request = null;

	public WxRequestText(WxRequestItf request) {
		this.request = request;
	}

	public String getText()
	{
		return this.request.getAttribute(WxMsgConstants.ATTR_TEXT_CONTENT);
	}
}
