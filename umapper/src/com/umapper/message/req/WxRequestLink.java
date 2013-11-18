package com.umapper.message.req;

import com.umapper.message.WxMsgConstants;

public class WxRequestLink {

	private WxRequestItf request;
	
	public WxRequestLink(WxRequestItf req)
	{
		this.request = req;
	}
	
	/**
	 * 消息标题
	 * @return
	 */
	public String getTitle()
	{
		return request.getAttribute(WxMsgConstants.ATTR_LINK_TITLE);
	}
	
	/**
	 * 消息描述
	 * @return
	 */
	public String getDescription()
	{
		return request.getAttribute(WxMsgConstants.ATTR_LINK_DESCRIPTION);
	}
	
	/**
	 * 消息链接
	 * @return
	 */
	public String getUrl()
	{
		return request.getAttribute(WxMsgConstants.ATTR_LINK_URL);
	}
}
